package net.mooko.common.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author puras <he@puras.me>
 * @since 15/6/14  下午7:21
 */
public class ThreadPoolUtils {
    private static ExecutorService cachedPool = Executors.newCachedThreadPool(); //可变数目的线程池

    private ThreadPoolUtils(){}

    public static void execute(Runnable runnable){
        cachedPool.execute(runnable);
    }
}
