package com.afkl.cases.df.config;

public class UniqueIdThreadLocal {

    private static ThreadLocal<String> threadLocal;

    public static void set(final String uuid) {
        if (threadLocal == null) {
            threadLocal = new ThreadLocal<>();
        }
        threadLocal.set(uuid);
    }

    public static String get() {
        return threadLocal.get();
    }

}
