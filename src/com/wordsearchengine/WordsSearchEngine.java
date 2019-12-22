package com.wordsearchengine;

import com.searchengine.datastructures.ISearchDataStructure;
import com.searchengine.engine.SearchEngine;
import com.searchengine.statistics.ISearchStatisticsAlgorithm;
import com.wordsearchengine.models.RankAlgorithmInput;
import com.wordsearchengine.models.SearchInput;
import com.wordsearchengine.models.RankStatistics;
import com.wordsearchengine.statistics.RankStatisticsAlgorithm;

import java.util.*;
import java.util.stream.Stream;

public class WordsSearchEngine extends SearchEngine<SearchInput, RankStatistics, ArrayList<String>> {
    final ISearchStatisticsAlgorithm<RankAlgorithmInput, Integer> rankAlgorithm;

    public WordsSearchEngine(ISearchDataStructure<ArrayList<String>> searchDataStructure, ISearchStatisticsAlgorithm<RankAlgorithmInput, Integer> rankAlgorithm) {
        super(searchDataStructure);
        this.rankAlgorithm = rankAlgorithm;
    }

    @Override
    protected List<RankStatistics> onSearch(SearchInput input) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (String word : input.getWords()) {
            if (this.getSearchDataStructure().containsKey(word)) {
                ArrayList<String> searchedData = this.getSearchDataStructure().find(word);
                searchedData.forEach(data -> {
                    if (!map.containsKey(data)) {
                        map.put(data, 1);
                    } else {
                        map.put(data, map.get(data) + 1);
                    }
                });
            }
        }

        return this.mapToList(map, input.getWords().length);
    }

    @Override
    protected Stream<RankStatistics> onFilter(Stream<RankStatistics> outputStream) {
        outputStream = outputStream
                .sorted(Comparator.comparing(RankStatistics::getRank).reversed())
                .sorted(Comparator.comparing(RankStatistics::getFile));
        return super.onFilter(outputStream);
    }

    @Override
    protected RankStatistics[] onConvert(Stream<RankStatistics> filteredOutputStream) {
        return filteredOutputStream.toArray(RankStatistics[]::new);
    }

    private List<RankStatistics> mapToList(HashMap<String, Integer> map, int wordCount) {
        List<RankStatistics> rankStatisticsList = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            RankStatistics rankStatistics = new RankStatistics(entry.getKey(), this.rankAlgorithm.calculate(new RankAlgorithmInput(entry.getValue(), wordCount)));
            rankStatisticsList.add(rankStatistics);
        }

        return rankStatisticsList;
    }
}