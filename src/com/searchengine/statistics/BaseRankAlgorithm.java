package com.searchengine.statistics;

public class BaseRankAlgorithm implements IRankAlgorithm {
    @Override
    public int calculate(int wordOccurrences, int wordCount) {
        return Math.min(100, 100 * wordOccurrences / wordCount);
    }
}
