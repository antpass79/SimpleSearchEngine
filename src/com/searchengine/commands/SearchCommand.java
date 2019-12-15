package com.searchengine.commands;

import com.searchengine.engine.SearchEngine;
import com.searchengine.statistics.SearchStatistics;

public class SearchCommand extends Command {
    SearchEngine searchEngine;
    int takeFirst = 10;

    SearchCommand(Object[] constructorParameters) {
        super(constructorParameters);
        this.searchEngine = (SearchEngine)constructorParameters[0];
        this.takeFirst = (int)constructorParameters[1];
    }

    @Override
    protected void onExecute(String[] commandParameters) {
        SearchStatistics[] searchStatistics = this.searchEngine.search(commandParameters, this.takeFirst);

        if (searchStatistics.length == 0) {
            System.out.println("no matches found");
        }
        else {
            for (SearchStatistics statistics :
                    searchStatistics) {
                System.out.println(statistics.getData() + ": " + statistics.getRank() + "%");
            }
        }
    }
}
