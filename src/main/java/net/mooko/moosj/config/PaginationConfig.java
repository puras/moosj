package net.mooko.moosj.config;

import net.mooko.common.utils.Pagination;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jason
 * @since 15/5/31  上午9:38
 */
@Configuration
public class PaginationConfig {
    private int defaultPageSize;
    private int navigationBarSize;

    public int getDefaultPageSize() {
        return defaultPageSize;
    }

    @Value("${pagination.default.page.size}")
    public void setDefaultPageSize(int defaultPageSize) {
        this.defaultPageSize = defaultPageSize;
        Pagination.CONFIG.setDefaultPageSize(defaultPageSize);
    }

    public int getNavigationBarSize() {
        return navigationBarSize;
    }

    @Value("${pagination.navigation.bar.size}")
    public void setNavigationBarSize(int navigationBarSize) {
        this.navigationBarSize = navigationBarSize;
        Pagination.CONFIG.setNavigationBarSize(navigationBarSize);
    }
}
