package com.searchengine.datastructures;

import java.util.HashMap;

public class TrieNode<T> {
    private HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    public HashMap<Character, TrieNode> getChildren() {
        return this.children;
    }
    private T data = null;
    public T getData() {
        return this.data;
    }
    protected void setData(T data) {
        this.data = data;
    }

    protected TrieNode() {
    }
}