package com.example;

// Rule: collections-import-00010
import java.util.HashMap;
import java.util.HashSet;
// Rule: collections-import-00020
import java.util.concurrent.ConcurrentHashMap;
import java.util.Collections;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {
        // Rule: collections-import-00010
        HashMap<String, Integer> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        map.put("a", 1);
        set.add("x");

        // Rule: collections-import-00020
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("key", 42);

        // Rule: collections-method-00010
        List<String> list = new ArrayList<>();
        list.add("b");
        list.add("a");
        Collections.sort(list);

        String[] arr = {"b", "a"};
        Arrays.sort(arr);
    }
}
