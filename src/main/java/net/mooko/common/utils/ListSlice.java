package net.mooko.common.utils;

import java.util.List;

/**
 * @author puras <he@puras.me>
 * @since 15/6/14  下午7:21
 */
public class ListSlice<T> {

    private long total;
    private List<T> list;

    public ListSlice(long total, List<T> list) {
        this.total = total;
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public List<T> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "ListSlice [getTotal()=" + getTotal() + ", getList()="
                + getList() +  "]";
    }




}
