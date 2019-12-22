package com.searchengine.engine;

public interface ISearchEngine<TInput, TOutput> {
    ISearchEngine<TInput, TOutput> takeFirstResults(int count);
    TOutput[] search(TInput input);
}
