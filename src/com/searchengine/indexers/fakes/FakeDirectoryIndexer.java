package com.searchengine.indexers.fakes;

import com.searchengine.datastructures.ISearchDataStructure;
import com.searchengine.datastructures.Trie;
import com.searchengine.indexers.ISearchIndexer;

import java.util.ArrayList;
import java.util.Arrays;

public class FakeDirectoryIndexer implements ISearchIndexer<FakeDirectory> {
    FakeDirectory directory;
    public FakeDirectoryIndexer(FakeDirectory directory) {
        this.directory = directory;
    }

    @Override
    public ISearchDataStructure<FakeDirectory> index() {
        Trie<ArrayList<String>> searchDataStructure = new Trie<ArrayList<String>>();

        Arrays.asList(this.directory.getFiles()).forEach(file -> {
            try {
                this.fillSearchDataStructure(searchDataStructure, file.getName(), file.getWords());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return searchDataStructure;
    }

    @Override
    public String[] getSourceNames() {
        return Arrays.stream(this.directory.getFiles()).map(file -> file.getName()).toArray(String[]::new);
    }

    protected void fillSearchDataStructure(Trie<ArrayList<String>> trie, String fileName, String[] words) {
        for (String word: words) {
            if (trie.containsKey(word)) {
                if (!trie.find(word).contains(fileName)) {
                    ArrayList<String> files = trie.find(word);
                    files.add(fileName);
                }
            }
            else if (!word.equals("")) {
                ArrayList<String> data = new ArrayList<String>();
                data.add(fileName);
                trie.insert(word, data);
            }
        }
    }
}
