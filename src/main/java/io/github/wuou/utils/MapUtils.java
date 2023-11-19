package io.github.wuou.utils;

import java.util.Map;

public class MapUtils {
    
    /**
     * Null-safe check if the specified Dictionary is empty.
     *
     * <p>Null returns true.
     *
     * @param map the collection to check, may be null
     * @return true if empty or null
     */
    public static boolean isEmpty(Map map) {
        return (map == null || map.isEmpty());
    }
}