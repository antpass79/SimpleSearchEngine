package com.searchengine.statistics;

public interface IRankAlgorithm {
    int calculate(int wordOccurrences, int wordCount);
}
