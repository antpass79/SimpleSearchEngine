package searchengine;

import com.searchengine.statistics.ISearchStatisticsBuilder;
import com.searchengine.statistics.SearchStatistics;
import com.searchengine.statistics.SearchStatisticsBuilder;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchStatisticsBuilderTest {
    @Test
    public void statisticsLength() {
        var builder = new SearchStatisticsBuilder();

        HashMap<Object, Integer> map = new HashMap<>();
        map.put("File1.txt", 3);
        map.put("File2.txt", 5);
        map.put("File3.txt", 7);

        SearchStatistics[] statistics = builder
                .wordCount(10)
                .map(map)
                .takeFirstResults(3)
                .build();

        assertEquals(3, statistics.length, "statistics count must be 3");
    }

    @Test
    public void statistics_Percentage() {
        var builder = new SearchStatisticsBuilder();

        HashMap<Object, Integer> map = new HashMap<>();
        map.put("File1.txt", 3);
        map.put("File2.txt", 0);
        map.put("File3.txt", 10);

        SearchStatistics[] statistics = builder
                .wordCount(10)
                .map(map)
                .takeFirstResults(3)
                .build();

        assertEquals(100, statistics[0].getRank(), "statistics rank for the element 1 must be 100");
        assertEquals("File3.txt", statistics[0].getData(), "statistics data for the element 1 must be File3.txt");
        assertEquals(30, statistics[1].getRank(), "statistics rank for the element 2 must be 30");
        assertEquals("File3.txt", statistics[0].getData(), "statistics data for the element 2 must be File1.txt");
        assertEquals(0, statistics[2].getRank(), "statistics rank for the element 3 must be 0");
        assertEquals("File3.txt", statistics[0].getData(), "statistics data for the element 3 must be File2.txt");
    }

    @Test
    public void statisticsTakeFirstElements_5() {
        var builder = new SearchStatisticsBuilder();

        HashMap<Object, Integer> map = new HashMap<>();
        map.put("File1.txt", 3);
        map.put("File2.txt", 5);
        map.put("File3.txt", 7);
        map.put("File4.txt", 11);
        map.put("File5.txt", 12);
        map.put("File6.txt", 3);
        map.put("File7.txt", 4);
        map.put("File8.txt", 56);
        map.put("File9.txt", 1);

        SearchStatistics[] statistics = builder
                .wordCount(10)
                .map(map)
                .takeFirstResults(5)
                .build();

        assertEquals(5, statistics.length, "statistics count must be 5");
    }
}