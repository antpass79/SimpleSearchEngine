package searchengine;
import com.searchengine.datastructures.Trie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {
    @Test
    public void emptyAfterCreation() {
        Trie<String> trie = new Trie<String>();

        assertEquals(true, trie.isEmpty(), "trie, once created, must be empty");
    }

    @Test
    public void notEmptyAfterInsert() {
        Trie<String> trie = new Trie<String>();
        trie.insert("test", "data");

        assertEquals(false, trie.isEmpty(), "trie, once a word is inserted, must be not empty");
    }

    @Test
    public void findAfterCreation() {
        Trie<String> trie = new Trie<String>();

        ArrayIndexOutOfBoundsException thrown = assertThrows(ArrayIndexOutOfBoundsException.class, () -> trie.find("test"), "Expected find to throw");
    }

    @Test
    public void findNotPresentWord() {
        Trie<String> trie = new Trie<String>();
        trie.insert("word", "data");

        ArrayIndexOutOfBoundsException thrown = assertThrows(ArrayIndexOutOfBoundsException.class, () -> trie.find("test"), "Expected find to throw");
        assertTrue(thrown.getMessage().contains("key"));
    }

    @Test
    public void findPresentWordWithOneEntry() {
        Trie<String> trie = new Trie<String>();
        trie.insert("test", "data");
        final String data = trie.find("test");

        assertEquals("data", data, "find method, if the finding word is inserted, must return the stored value ('data')");
    }

    @Test
    public void findPresentWordWithMoreEntries() {
        Trie<String> trie = new Trie<String>();
        trie.insert("test", "data");
        trie.insert("tes3", "data");
        trie.insert("test1", "data1");
        final String data = trie.find("test");

        assertEquals("data", data, "find method, if the finding word is inserted, must return the stored value ('data')");
    }

    @Test
    public void containsKeyFalse() {
        Trie<String> trie = new Trie<String>();
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
        Trie<String> trie = new Trie<String>();
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
        Trie<String> trie = new Trie<String>();
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
        Trie<String> trie = new Trie<String>();
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
}