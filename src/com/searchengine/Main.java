package com.searchengine;

import com.searchengine.commands.*;
import com.searchengine.datastructures.ISearchDataStructure;
import com.searchengine.engine.ISearchEngine;
import com.searchengine.indexers.DirectoryIndexer;
import com.searchengine.indexers.ISearchIndexer;
import com.searchengine.indexers.TxtFileFilter;
import com.searchengine.statistics.SearchStatisticsBuilder;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No directory given to index.");
        }

        final File indexableDirectory = new File(args[0]);

        ISearchIndexer indexer = createIndexer(indexableDirectory);
        System.out.println(indexer.getSourceNames().length + " files read in directory " + indexableDirectory.getName());

        ISearchEngine searchEngine = createSearchEngine(indexer);
        int takeFirstElements = 10;

        initializeCommands(searchEngine, takeFirstElements);

        try (Scanner keyboard = new Scanner(System.in)) {
            while (true) {
                System.out.print("search> ");
                final String line = keyboard.nextLine();

                ICommand command = CommandResolver.resolve(InputLineHelper.extractCommandName(line));
                command.execute(InputLineHelper.extractCommandParameters(line));
            }
        }
    }

    private static ISearchIndexer createIndexer(File indexableDirectory) {
        DirectoryIndexer directoryIndexer = new DirectoryIndexer(indexableDirectory, new TxtFileFilter());
        directoryIndexer.index();

        return directoryIndexer;
    }

    private static ISearchEngine createSearchEngine(ISearchIndexer indexer) {
        ISearchDataStructure searchDataStructure = indexer.index();
        ISearchEngine searchEngine = new SimpleSearchEngine(searchDataStructure, new SearchStatisticsBuilder());

        return searchEngine;
    }

    private static void initializeCommands(ISearchEngine searchEngine, int takeFirstElements) {
        CommandResolver.register(":quit", new CommandBuilder()
                .commandName(Commands.COMMAND_QUIT)
                .build());

        CommandResolver.register("search", new CommandBuilder()
                .commandName("search")
                .constructorParameters(new Object[] { searchEngine, takeFirstElements })
                .build());
    }
}
