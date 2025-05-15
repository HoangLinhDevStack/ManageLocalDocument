package com.manager.doc.service.helper;

public class Helper {
    public static boolean safeEquals(String a, String b) {
        return (a == null && b == null) || (a != null && a.equals(b));
    }
}
