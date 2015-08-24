package net.mooko.common.utils;

import org.apache.axis.encoding.Base64;

/**
 * @author puras <he@puras.me>
 * @since 15/6/14  下午7:21
 */
public class Base64Utils {
    public static String encode(String s)
    {
        String encodStr = Base64.encode(s.getBytes());
        encodStr = encodStr.replaceAll("\\+", "-");
        encodStr = encodStr.replaceAll("/", "_");
        return encodStr;
    }
    public static String decode(String s)
    {
        s = s.replaceAll("-", "+");
        s = s.replaceAll("_", "/");
        return new String(Base64.decode(s));
    }
}
