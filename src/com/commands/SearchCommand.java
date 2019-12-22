package com.commands;

import com.wordsearchengine.WordsSearchEngine;
import com.wordsearchengine.models.SearchInput;
import com.wordsearchengine.models.RankStatistics;

public class SearchCommand extends Command {
    final WordsSearchEngine searchEngine;
    int takeFirstResults = 10;

    SearchCommand(Object[] constructorParameters) {
        super(constructorParameters);
        this.searchEngine = (WordsSearchEngine)constructorParameters[0];
        this.takeFirstResults = (int)constructorParameters[1];
    }

    @Override
    protected void onExecute(String[] commandParameters) {
        RankStatistics[] rankStatistics = this.searchEngine.search(new SearchInput(commandParameters, this.takeFirstResults));

        if (rankStatistics.length == 0) {
            System.out.println("no matches found");
        }
        else {
            for (RankStatistics statistics :
                    rankStatistics) {
                System.out.println(statistics.getFile() + ": " + statistics.getRank() + "%");
            }
        }
    }
}
