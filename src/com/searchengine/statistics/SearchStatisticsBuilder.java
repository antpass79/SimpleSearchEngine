package com.searchengine.statistics;

import java.util.*;
import java.util.stream.Collectors;

public class SearchStatisticsBuilder<T> implements ISearchStatisticsBuilder<ArrayList<T>> {
    IRankAlgorithm rankAlgorithm;
    int wordCount;
    HashMap<Object, Integer> map;
    int takeFirstElements;

    public SearchStatisticsBuilder() {
        this.rankAlgorithm = new BaseRankAlgorithm();
        this.wordCount = 0;
        this.takeFirstElements = 10;
    }
    public SearchStatisticsBuilder(IRankAlgorithm rankAlgorithm) {
        super();
        this.rankAlgorithm = rankAlgorithm;
    }

    @Override
    public ISearchStatisticsBuilder<ArrayList<T>> map(HashMap<Object, Integer> map) {
        this.map = map;
        return this;
    }
    @Override
    public ISearchStatisticsBuilder<ArrayList<T>> wordCount(int wordCount) {
        this.wordCount = wordCount;
        return this;
    }
    @Override
    public ISearchStatisticsBuilder<ArrayList<T>> takeFirstElements(int takeFirstElements) {
        this.takeFirstElements = takeFirstElements;
        return this;
    }
    @Override
    public SearchStatistics<ArrayList<T>>[] build() {
        final Map<Object, Integer> orderedMap = this.orderByRank(this.map);
        final SearchStatistics<ArrayList<T>>[] searchStatistics = this.buildStatistics(orderedMap);

        return searchStatistics;
    }

    protected SearchStatistics<ArrayList<T>>[] buildStatistics(Map<Object, Integer> orderedMap) {
        List<SearchStatistics<ArrayList<T>>> searchStatisticsList = new ArrayList<SearchStatistics<ArrayList<T>>>();

        for (Map.Entry<Object, Integer> entry : orderedMap.entrySet()) {
            SearchStatistics searchStatistics = new SearchStatistics(entry.getKey(), this.rankAlgorithm.calculate(entry.getValue(), this.wordCount));
            searchStatisticsList.add(searchStatistics);
        }

        return searchStatisticsList.stream().collect(Collectors.toList()).toArray(new SearchStatistics[searchStatisticsList.size()]);
    }

    private Map<Object, Integer> orderByRank(HashMap<Object, Integer> indexes) {
        final Map<Object, Integer> orderedByRank = indexes.entrySet()
                .stream()
                .limit(this.takeFirstElements)
                .sorted((Map.Entry.<Object, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        return orderedByRank;
    }
}
