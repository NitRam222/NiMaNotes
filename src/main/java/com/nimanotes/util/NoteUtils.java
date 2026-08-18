package com.nimanotes.util;

import java.util.regex.Pattern;

public final class NoteUtils {

    private NoteUtils() {}

    public static boolean isTitleLengthValid(String title) {
        if (title == null) return false;
        int len = title.length();
        return len >= 3 && len <= 100;
    }

    public static double computeRatio(double a, double b) {
        if (b == 0) throw new IllegalArgumentException("Division durch Null");
        return a / b;
    }

    public static boolean matchesPattern(String input, String regex) {
        if (input == null) return false;
        return Pattern.matches(regex, input);
    }

    public static String truncate(String s, int maxLength) {
        if (s == null) return null;
        if (maxLength < 0) throw new IllegalArgumentException("maxLength must be >= 0");
        if (s.length() <= maxLength) return s;
        return s.substring(0, maxLength);
    }
}
