package com.searchengine.engine;

public interface ISearchEngine<TResult> {
    TResult[] search(String[] words, int takeFirstResults);
}
