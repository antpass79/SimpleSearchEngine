package com.searchengine.engine;

import com.searchengine.datastructures.ISearchDataStructure;
import com.searchengine.statistics.ISearchStatisticsBuilder;
import com.searchengine.statistics.SearchStatistics;

import java.util.HashMap;

public abstract class SearchEngine<T> implements ISearchEngine<SearchStatistics[]> {
    final ISearchStatisticsBuilder<T> searchStatisticsBuilder;

    final ISearchDataStructure<T> searchDataStructure;
    protected ISearchDataStructure<T> getSearchDataStructure() {
        return this.searchDataStructure;
    }

    public SearchEngine(ISearchDataStructure<T> searchDataStructure, ISearchStatisticsBuilder<T> searchStatisticsBuilder) {
        this.searchDataStructure = searchDataStructure;
        this.searchStatisticsBuilder = searchStatisticsBuilder;
    }

    public final SearchStatistics[] search(String[] words, int takeFirstResults) {
        HashMap<Object, Integer> map = this.onSearch(words);

        return this.searchStatisticsBuilder
                .wordCount(words.length)
                .map(map)
                .takeFirstResults(takeFirstResults)
                .build();
    }

    protected abstract HashMap<Object, Integer> onSearch(String[] words);
}