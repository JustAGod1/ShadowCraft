package com.justagod.shadowcraft.util;


import java.util.Map;

/**
 * Драсьте, сделано Yuri
 * В 15:11
 */
public class MapUtility {



    public static <K, V>  K getKeyByValue(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) return entry.getKey();
        }
        return null;
    }

    public static <T, V, K> T getSomeFromValue(Map<K, V> map, Tester<V, T> predicate) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            T obj = predicate.test(entry.getValue());
            if (obj != null) return obj;
        }
        return null;
    }

    public static <T, V> T getSomeFromKey(Map<V, Object> map, Tester<V, T> predicate) {
        for (Map.Entry<V, Object> entry : map.entrySet()) {
            T obj = predicate.test(entry.getKey());
            if (obj != null) return obj;
        }
        return null;
    }

    public static <K, V> boolean anyMatch(Map<K, V> map, Matcher<Map.Entry<K, V>> matcher) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (matcher.match(entry)) return true;
        }
        return false;
    }

    public interface Matcher<T> {
        boolean match(T arg);
    }

    public interface Tester<T, R> {
        R test(T arg);
    }
}
