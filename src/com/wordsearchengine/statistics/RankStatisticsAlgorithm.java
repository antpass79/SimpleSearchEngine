package com.wordsearchengine.statistics;

import com.searchengine.statistics.ISearchStatisticsAlgorithm;
import com.wordsearchengine.models.RankAlgorithmInput;

public class RankStatisticsAlgorithm implements ISearchStatisticsAlgorithm<RankAlgorithmInput, Integer> {
    @Override
    public Integer calculate(RankAlgorithmInput input) {
        return Math.min(100, 100 * input.getWordOccurrences() / input.getWordCount());
    }
}
