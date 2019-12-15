package com.searchengine.statistics;

import java.util.HashMap;

public interface ISearchStatisticsBuilder<T> {
    ISearchStatisticsBuilder<T> wordCount(int wordCount);
    ISearchStatisticsBuilder<T> map(HashMap<Object, Integer> map);
    ISearchStatisticsBuilder<T> takeFirstElements(int elementCount);
    SearchStatistics<T>[] build();
}
