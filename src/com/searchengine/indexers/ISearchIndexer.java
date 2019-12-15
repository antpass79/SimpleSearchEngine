package com.searchengine.indexers;

import com.searchengine.datastructures.ISearchDataStructure;

public interface ISearchIndexer<T> {
    ISearchDataStructure<T> index();
    String[] getSourceNames();
}
