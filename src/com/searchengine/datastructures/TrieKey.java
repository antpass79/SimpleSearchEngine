package com.searchengine.datastructures;

public class TrieKey {

    public static char getFirstChar(String key) {
        return key.charAt(0);
    }
    public static String getSubKey(String key) {
        return key.subSequence(1, key.length()).toString();
    }
}
