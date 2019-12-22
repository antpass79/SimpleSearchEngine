package searchengine;

import com.searchengine.datastructures.ISearchDataStructure;
import com.searchengine.indexers.fakes.FakeDirectory;
import com.searchengine.indexers.fakes.FakeDirectoryIndexer;
import com.searchengine.indexers.fakes.FakeFile;
import com.wordsearchengine.WordsSearchEngine;
import com.searchengine.indexers.ISearchIndexer;
import com.searchengine.indexers.TextIndexer;
import com.wordsearchengine.models.RankStatistics;
import com.wordsearchengine.statistics.RankStatisticsAlgorithm;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordsSearchEngineTest {

    @Test
    public void searchDuplicateWords() {
        ISearchIndexer searchIndexer = new TextIndexer(new String[] {"word1 word2 word1 word4 word5"});
        ISearchDataStructure searchDataStructure = searchIndexer.index();
        WordsSearchEngine searchEngine = new WordsSearchEngine(searchDataStructure, new RankStatisticsAlgorithm());

        RankStatistics[] statistics = searchEngine.search(new String[] {"word1"});

        assertEquals(1, statistics.length, "statistics must be zero");
        assertEquals(100, statistics[0].getRank(), "statistics rank must be 100");
    }

    @Test
    public void searchNotPresentWord() {
        ISearchIndexer searchIndexer = new TextIndexer(new String[] {"word1 word2 word3 word4 word5", "word1 word2 word3", "word3"});
        ISearchDataStructure searchDataStructure = searchIndexer.index();
        WordsSearchEngine searchEngine = new WordsSearchEngine(searchDataStructure, new RankStatisticsAlgorithm());

        RankStatistics[] statistics = searchEngine.search(new String[] {"word"});

        assertEquals(0, statistics.length, "statistics must be zero");
    }

    @Test
    public void searchPresentWord() {
        ISearchIndexer searchIndexer = new TextIndexer(new String[] {"word1 word2 word3 word4 word5", "word3", "word1 word2 word3"});
        ISearchDataStructure searchDataStructure = searchIndexer.index();
        WordsSearchEngine searchEngine = new WordsSearchEngine(searchDataStructure, new RankStatisticsAlgorithm());

        RankStatistics[] statistics = searchEngine.search(new String[] {"word1", "word2", "word3", "word4", "word5"});

        assertEquals(3, statistics.length, "statistics must be 3");
    }

    @Test
    public void statisticsLength() {
        FakeFile file1 = new FakeFile("file1.txt", new String[] {"word1", "word2", "word3", "word4", "word5"});
        FakeFile file2 = new FakeFile("file2.txt", new String[] {"word3"});
        FakeFile file3 = new FakeFile("file3.txt", new String[] {"test1", "test2", "test3"});
        FakeDirectory directory = new FakeDirectory("search dir", new FakeFile[] {file1, file2, file3});;
        ISearchIndexer searchIndexer = new FakeDirectoryIndexer(directory);

        ISearchDataStructure searchDataStructure = searchIndexer.index();
        WordsSearchEngine searchEngine = new WordsSearchEngine(searchDataStructure, new RankStatisticsAlgorithm());

        RankStatistics[] statistics = searchEngine.search(new String[] {"word1", "word2", "word3", "word4", "word5", "test3"});

        assertEquals(3, statistics.length, "statistics must be 3");
    }

    @Test
    public void statistics_Percentage() {
        FakeFile file1 = new FakeFile("file1.txt", new String[] {"word1", "word2", "word3", "word4", "word5"});
        FakeFile file2 = new FakeFile("file2.txt", new String[] {"word3"});
        FakeFile file3 = new FakeFile("file3.txt", new String[] {"test1", "test2", "test3", "word3"});
        FakeDirectory directory = new FakeDirectory("search dir", new FakeFile[] {file1, file2, file3});;
        ISearchIndexer searchIndexer = new FakeDirectoryIndexer(directory);

        ISearchDataStructure searchDataStructure = searchIndexer.index();
        WordsSearchEngine searchEngine = new WordsSearchEngine(searchDataStructure, new RankStatisticsAlgorithm());

        RankStatistics[] statistics = searchEngine.search(new String[] {"word5", "word3"});

        assertEquals(100, statistics[0].getRank(), "statistics rank for the element 1 must be 100");
        assertEquals("file1.txt", statistics[0].getFile(), "statistics data for the element 1 must be file1.txt");
        assertEquals(50, statistics[1].getRank(), "statistics rank for the element 2 must be 50");
        assertEquals("file2.txt", statistics[1].getFile(), "statistics data for the element 2 must be file2.txt");
        assertEquals(50, statistics[2].getRank(), "statistics rank for the element 3 must be 50");
        assertEquals("file3.txt", statistics[2].getFile(), "statistics data for the element 3 must be file3.txt");
    }

    @Test
    public void statisticsTakeFirstElements_5() {
        FakeFile file1 = new FakeFile("file1.txt", new String[] {"word1", "word2", "word3", "word4", "word5"});
        FakeFile file2 = new FakeFile("file2.txt", new String[] {"word3"});
        FakeFile file3 = new FakeFile("file3.txt", new String[] {"test1", "test2", "test3", "word3"});
        FakeFile file4 = new FakeFile("file4.txt", new String[] {"We", "are", "the", "champions"});
        FakeFile file5 = new FakeFile("file5.txt", new String[] {"sun"});
        FakeFile file6 = new FakeFile("file6.txt", new String[] {"the", "cat", "is", "on", "the", "table"});
        FakeFile file7 = new FakeFile("file7.txt", new String[] {"the", "blue", "sea"});
        FakeFile file8 = new FakeFile("file8.txt", new String[] {"gift"});
        FakeFile file9 = new FakeFile("file9.txt", new String[] {"the", "testing", "machine"});

        FakeDirectory directory = new FakeDirectory("search dir", new FakeFile[] {
                file1, file2, file3, file4, file5, file6, file7, file8, file9
        });;
        ISearchIndexer searchIndexer = new FakeDirectoryIndexer(directory);

        ISearchDataStructure searchDataStructure = searchIndexer.index();
        WordsSearchEngine searchEngine = new WordsSearchEngine(searchDataStructure, new RankStatisticsAlgorithm());
        RankStatistics[] statistics = searchEngine
                .takeFirstResults(5)
                .search(new String[] {"word1", "test1", "We", "the", "machine", "sun"});

        assertEquals(5, statistics.length, "statistics count must be 5");
    }}