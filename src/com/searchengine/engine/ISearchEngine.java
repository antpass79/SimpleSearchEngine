package com.searchengine.engine;

public interface ISearchEngine<TOutput> {
    TOutput search(String[] words, int takeFirstResults);
}
