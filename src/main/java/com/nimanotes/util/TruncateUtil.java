package com.nimanotes.util;

public final class TruncateUtil {
    private TruncateUtil() {}

    public static String truncate(String s, int max) {
        if (s == null) return null;
        if (max < 0) throw new IllegalArgumentException("max must be >=0");
        return s.length() <= max ? s : s.substring(0, max);
    }
}
