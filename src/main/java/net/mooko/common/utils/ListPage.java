package net.mooko.common.utils;

import java.util.List;

/**
 * @author puras <he@puras.me>
 * @since 15/6/14  下午7:21
 */
public class ListPage<T> {
    private Pagination pagination;
    private List<T> list;
    public ListPage(Pagination pagination, List<T> list) {
        this.pagination = pagination;
        this.list = list;
    }

    public ListPage(Pagination pagination, ListSlice<T> slice) {
        this.pagination = pagination;
        this.list = slice.getList();
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
