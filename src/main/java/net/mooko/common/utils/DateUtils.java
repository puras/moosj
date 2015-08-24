package net.mooko.common.utils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author puras <he@puras.me>
 * @since 15/6/14  下午7:21
 */
public class DateUtils {
    public static Date currentDate() {
        return new Timestamp(System.currentTimeMillis());
    }
}
