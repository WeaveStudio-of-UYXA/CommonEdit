package io.weavestudio.commoneditlib.utils;

import org.jetbrains.annotations.Nullable;

public class StringUtils {

    public static boolean isBlank(@Nullable String string) {
        return string == null || string.equals("");
    }

}
