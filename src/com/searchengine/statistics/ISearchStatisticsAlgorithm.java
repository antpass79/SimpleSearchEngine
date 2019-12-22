package com.searchengine.statistics;

public interface ISearchStatisticsAlgorithm<TInput, TOutput> {
    TOutput calculate(TInput input);
}
