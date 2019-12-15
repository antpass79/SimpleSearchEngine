package com.searchengine;

import com.searchengine.datastructures.ISearchDataStructure;
import com.searchengine.engine.SearchEngine;
import com.searchengine.statistics.ISearchStatisticsBuilder;

import java.util.ArrayList;
import java.util.HashMap;

public class SimpleSearchEngine extends SearchEngine<ArrayList<String>> {
    public SimpleSearchEngine(ISearchDataStructure<ArrayList<String>> searchDataStructure, ISearchStatisticsBuilder<ArrayList<String>> searchStatisticsBuilder) {
        super(searchDataStructure, searchStatisticsBuilder);
    }

    @Override
    protected HashMap<Object, Integer> onSearch(String[] words) {
        HashMap<Object, Integer> map = new HashMap<Object, Integer>();
        for (String word : words) {
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

        return map;
    }
}