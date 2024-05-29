package com.test.test;

import com.google.common.collect.Lists;

import java.util.*;

public class Jihe {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "小明");
        map.putIfAbsent(1, "化");
        System.out.println(map.get(1));
    }
}
