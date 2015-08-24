package net.mooko.common.utils;

import java.io.Closeable;

/**
 * @author puras <he@puras.me>
 * @since 15/6/14  下午7:21
 */
public class IOUtils {
    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable ignore){}
        }
        
    } 
       
}
