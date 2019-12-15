package searchengine;

import com.searchengine.*;
import com.searchengine.datastructures.ISearchDataStructure;
import com.searchengine.engine.SearchEngine;
import com.searchengine.indexers.ISearchIndexer;
import com.searchengine.indexers.TextIndexer;
import com.searchengine.statistics.SearchStatistics;
import com.searchengine.statistics.SearchStatisticsBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleSearchEngineTest {

    @Test
    public void searchDuplicateWords() {
        ISearchIndexer searchIndexer = new TextIndexer(new String[] {"word1 word2 word1 word4 word5"});
        ISearchDataStructure searchDataStructure = searchIndexer.index();
        SearchEngine searchEngine = new SimpleSearchEngine(searchDataStructure, new SearchStatisticsBuilder());

        SearchStatistics[] statistics = searchEngine.search(new String[] {"word1"}, 10);

        assertEquals(1, statistics.length, "statistics must be zero");
        assertEquals(100, statistics[0].getRank(), "statistics rank must be 100");
    }

    @Test
    public void searchNotPresentWord() {
        ISearchIndexer searchIndexer = new TextIndexer(new String[] {"word1 word2 word3 word4 word5", "word1 word2 word3", "word3"});
        ISearchDataStructure searchDataStructure = searchIndexer.index();
        SearchEngine searchEngine = new SimpleSearchEngine(searchDataStructure, new SearchStatisticsBuilder());

        SearchStatistics[] statistics = searchEngine.search(new String[] {"word"}, 10);

        assertEquals(0, statistics.length, "statistics must be zero");
    }

    @Test
    public void searchPresentWord() {
        ISearchIndexer searchIndexer = new TextIndexer(new String[] {"word1 word2 word3 word4 word5", "word3", "word1 word2 word3"});
        ISearchDataStructure searchDataStructure = searchIndexer.index();
        SearchEngine searchEngine = new SimpleSearchEngine(searchDataStructure, new SearchStatisticsBuilder());

        SearchStatistics[] statistics = searchEngine.search(new String[] {"word1", "word2", "word3", "word4", "word5"}, 10);

        assertEquals(3, statistics.length, "statistics must be 3");
    }
}