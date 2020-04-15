package vn.mrlongg71.vnfood.src.model;

import android.graphics.Point;
import android.util.Size;

public  class StoreKey {
    private static String token;
    private static Point size;

    public static Point getSize() {
        return size;
    }

    public static void setSize(Point size) {
        StoreKey.size = size;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String tokenn) {
        token = tokenn;
    }
}
