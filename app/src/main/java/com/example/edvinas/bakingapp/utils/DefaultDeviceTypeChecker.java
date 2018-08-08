package com.example.edvinas.bakingapp.utils;

import android.content.res.Configuration;

public final class DefaultDeviceTypeChecker {
    private DefaultDeviceTypeChecker() {
        // empty
    }

    public static boolean isTablet(Configuration configuration) {
        return configuration.smallestScreenWidthDp >= 600;
    }
}
