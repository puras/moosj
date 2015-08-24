package net.mooko.common.utils;

/**
 * @author puras <he@puras.me>
 * @since 15/6/14  下午7:21
 */
public class ListBounds {
    public static final String NAME = "bounds";
    public final static int NO_ROW_OFFSET = 0;
    public final static int NO_ROW_LIMIT = Integer.MAX_VALUE;
    public final static ListBounds DEFAULT = new ListBounds();

    private int offset;
    private int limit;

    public ListBounds() {
        this.offset = NO_ROW_OFFSET;
        this.limit = NO_ROW_LIMIT;
    }

    public ListBounds(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }
}
