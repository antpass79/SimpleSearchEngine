package searchengine;
import com.searchengine.datastructures.Trie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {
    @Test
    public void emptyAfterCreation() {
        Trie<String> trie = new Trie<>();

        assertTrue(trie.isEmpty(), "trie, once created, must be empty");
    }

    @Test
    public void notEmptyAfterInsert() {
        Trie<String> trie = new Trie<>();
        trie.insert("test", "data");

        assertFalse(trie.isEmpty(), "trie, once a word is inserted, must be not empty");
    }

    @Test
    public void findAfterCreation() {
        Trie<String> trie = new Trie<>();

        ArrayIndexOutOfBoundsException thrown = assertThrows(ArrayIndexOutOfBoundsException.class, () -> trie.find("test"), "Expected find to throw");
    }

    @Test
    public void findNotPresentWord() {
        Trie<String> trie = new Trie<>();
        trie.insert("word", "data");

        ArrayIndexOutOfBoundsException thrown = assertThrows(ArrayIndexOutOfBoundsException.class, () -> trie.find("test"), "Expected find to throw");
        assertTrue(thrown.getMessage().contains("key"));
    }

    @Test
    public void findPresentWordWithOneEntry() {
        Trie<String> trie = new Trie<>();
        trie.insert("test", "data");
        final String data = trie.find("test");

        assertEquals("data", data, "find method, if the finding word is inserted, must return the stored value ('data')");
    }

    @Test
    public void findPresentWordWithMoreEntries() {
        Trie<String> trie = new Trie<>();
        trie.insert("test", "data");
        trie.insert("tes3", "data");
        trie.insert("test1", "data1");
        final String data = trie.find("test");

        assertEquals("data", data, "find method, if the finding word is inserted, must return the stored value ('data')");
    }

    @Test
    public void containsKeyFalse() {
        Trie<String> trie = new Trie<>();
        trie.insert("word1", "file1");
        trie.insert("word2", "file1");
        trie.insert("word3", "file1");
        trie.insert("word4", "file1");
        trie.insert("word5", "file1");
        trie.insert("word1", "file2");
        trie.insert("word3", "file2");
        trie.insert("word4", "file2");
        trie.insert("word5", "file2");
        trie.insert("word3", "file3");

        assertFalse(trie.containsKey("test"));
    }

    @Test
    public void containsKeyTrue() {
        Trie<String> trie = new Trie<>();
        trie.insert("word1", "file1");
        trie.insert("word2", "file1");
        trie.insert("word3", "file1");
        trie.insert("word4", "file1");
        trie.insert("word5", "file1");
        trie.insert("word1", "file2");
        trie.insert("word3", "file2");
        trie.insert("word4", "file2");
        trie.insert("word5", "file2");
        trie.insert("word3", "file3");

        assertTrue(trie.containsKey("word3"));
    }

    @Test
    public void containsKeyThatIsSubTextOfAWord() {
        Trie<String> trie = new Trie<>();
        trie.insert("word1", "file1");
        trie.insert("word2", "file1");
        trie.insert("word3", "file1");
        trie.insert("word4", "file1");
        trie.insert("word5", "file1");
        trie.insert("word1", "file2");
        trie.insert("word3", "file2");
        trie.insert("word4", "file2");
        trie.insert("word5", "file2");
        trie.insert("word3", "file3");

        assertFalse(trie.containsKey("word"));
    }

    @Test
    public void containsKeyThatIsSuperTextOfAWord() {
        Trie<String> trie = new Trie<>();
        trie.insert("word1", "file1");
        trie.insert("word2", "file1");
        trie.insert("word3", "file1");
        trie.insert("word4", "file1");
        trie.insert("word5", "file1");
        trie.insert("word1", "file2");
        trie.insert("word3", "file2");
        trie.insert("word4", "file2");
        trie.insert("word5", "file2");
        trie.insert("word3", "file3");

        assertFalse(trie.containsKey("word11"));
    }

    @Test
    public void findCaseSensitive() {
        Trie<String> trie = new Trie<>();
        trie.insert("Test", "data1");
        trie.insert("tEst", "data2");
        trie.insert("tesT", "data3");
        boolean notFound1 = trie.containsKey("test");
        boolean notFound2 = trie.containsKey("TEST");
        boolean found1 = trie.containsKey("Test");
        boolean found2 = trie.containsKey("tesT");

        assertFalse(notFound1, "containsKey method must return false checking for test");
        assertFalse(notFound2, "containsKey method must return false checking for TEST");
        assertTrue(found1, "containsKey method must return true checking for Test");
        assertTrue(found2, "containsKey method must return true checking for tesT");
    }
}