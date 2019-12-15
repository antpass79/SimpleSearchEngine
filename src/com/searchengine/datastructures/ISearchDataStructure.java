package com.searchengine.datastructures;

public interface ISearchDataStructure<T> {
    boolean containsKey(String key);
    T find(String key);
}
