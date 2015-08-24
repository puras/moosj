package net.mooko.common.utils;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * 生成分页信息，包括首页、尾页、当前页、上一页、下一页以及当前页一定范围的前后页面
 * @author puras <he@puras.me>
 * @since 15/6/14  下午7:21
 */
public class Pagination {
    public static final String PARAM_NAME_CURRENT_PAGE = "currentPage";
    public static final String PARAM_NAME_PAGE_SIZE = "pageSize";
    public static final String PARAM_NAME_TOTAL_COUNT = "totalCount";
    public static final Config CONFIG = new Config();

    private long totalCount = -1;//数据总数
    private int pageSize = CONFIG.getDefaultPageSize();//每页显示的数据数
    private int currentPage = 0;//当前页的索引值，起始值为0
    private long pageCount;//总页数
    private String url;//不包含分页参数的url
    private List<Link> links;//分页导航条的数据信息

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        assert totalCount >= 0;
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        assert pageSize > 0;
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        assert currentPage >= 0;
        this.currentPage = currentPage;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStartIndex() {
        return currentPage * pageSize;
    }

    public List<Link> getLinks() {
        if (totalCount < 0) {
            throw new RuntimeException("please set a valid value of totalCount!");
        }
        if (this.links == null) {
            buildLinks();
        }
        return this.links;
    }

    private void buildLinks() {
        links = new ArrayList<Link>();
        pageCount = computePageCount(totalCount, pageSize);
        if (pageCount == 0) {
            return;
        }
        if (currentPage < 0 || currentPage >= pageCount) {
            throw new InvalidParameterException("currentPage is invalid, must be in [0," + (pageCount - 1) + "]");
        }
        long firstIndex = 0;//包含
        long lastIndex = pageCount;//不包含
        int navigationBarSize = CONFIG.getNavigationBarSize();
        if (pageCount > navigationBarSize) {
            firstIndex = currentPage - navigationBarSize / 2;
            lastIndex = firstIndex + navigationBarSize;
            if (firstIndex < 0) {
                firstIndex = 0;
                lastIndex = navigationBarSize;
            } else if (lastIndex > pageCount) {
                lastIndex = pageCount;
                firstIndex = lastIndex - navigationBarSize;
            }
        }
        if (firstIndex > 0) {
            links.add(new Link("首页", buildHref(0)));
        }
        if (currentPage > 0) {
            links.add(new Link("上一页", buildHref(currentPage - 1)));
        }
        for (long i = firstIndex; i < lastIndex; i++) {
            if (i == currentPage) {
                links.add(new Link(String.valueOf(i + 1)));
            } else {
                links.add(new Link(String.valueOf(i + 1), buildHref(i)));
            }
        }
        if (currentPage < pageCount - 1) {
            links.add(new Link("下一页", buildHref(currentPage + 1)));
        }
        if (lastIndex < pageCount) {
            links.add(new Link("尾页", buildHref(pageCount - 1)));
        }
    }

    private String buildHref(long pageIndex) {
        return url + (url.indexOf('?') >= 0 ? "&" : "?") + PARAM_NAME_CURRENT_PAGE + "=" + pageIndex
                + "&" + PARAM_NAME_PAGE_SIZE + "=" + pageSize + "&" + PARAM_NAME_TOTAL_COUNT + "=" + totalCount;
    }

    public static class Link {
        private String title;
        private String href;
        private boolean isActive = false;

        public Link(String title) {
            this.href = "#";
            this.title = title;
            this.isActive = true;
        }

        public Link(String title, String href) {
            this.href = href;
            this.title = title;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setIsActive(boolean isActive) {
            this.isActive = isActive;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class Config {
        private int defaultPageSize = 5;
        private int navigationBarSize = 7;

        public int getDefaultPageSize() {
            return defaultPageSize;
        }

        public void setDefaultPageSize(int defaultPageSize) {
            this.defaultPageSize = defaultPageSize;
        }

        public int getNavigationBarSize() {
            return navigationBarSize;
        }

        public void setNavigationBarSize(int navigationBarSize) {
            this.navigationBarSize = navigationBarSize;
        }

    }

    public static long computePageCount(long totalCount, int pageSize) {
        return totalCount / pageSize + (totalCount % pageSize > 0 ? 1 : 0);
    }

    public static int computeStartIndex(int currentPage, int pageSize) {
        return pageSize * currentPage;
    }

    public static void main(String[] args) {
        Pagination pagination = new Pagination();
        pagination.setTotalCount(11);
        pagination.setPageSize(2);
        pagination.setCurrentPage(1);
        pagination.setUrl("test");
        print(pagination);
    }

    public static void print(Pagination pagination) {
        for (Link link : pagination.getLinks()) {
            System.out.println("  [" + link.getTitle() + "," + link.getHref() + "]  ");
        }
        System.out.println();
    }
}
