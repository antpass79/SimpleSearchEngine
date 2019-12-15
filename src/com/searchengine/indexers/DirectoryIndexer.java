package com.searchengine.indexers;

import com.searchengine.datastructures.ISearchDataStructure;
import com.searchengine.datastructures.Trie;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

public class DirectoryIndexer implements ISearchIndexer<ArrayList<String>> {
    File directory;
    IFileFilter fileFilter;

    public DirectoryIndexer(File directory, IFileFilter fileFilter) {
        this.directory = directory;
        this.fileFilter = fileFilter;
    }

    @Override
    public ISearchDataStructure<ArrayList<String>> index() {
        Trie<ArrayList<String>> searchDataStructure = new Trie<ArrayList<String>>();
        final String[] list = this.fileFilter.filter(directory);

        Arrays.asList(list).forEach(fileName -> {
            final File file = new File(directory, fileName);
            if (file.isFile()){
                try {
                    final String contentFile = Files.readString(file.toPath());
                    this.fillSearchDataStructure(searchDataStructure, file.getName(), contentFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return searchDataStructure;
    }
    @Override
    public String[] getSourceNames() {
        return this.fileFilter.filter(this.directory);
    }

    private void fillSearchDataStructure(Trie<ArrayList<String>> trie, String fileName, String contentFile) {
        final String[] words = contentFile.split(" ");
        for (String word: words) {
            if (trie.containsKey(word) && !trie.find(word).contains(fileName)) {
                trie.find(word).add(fileName);
            }
            else {
                ArrayList<String> data = new ArrayList<String>();
                data.add(fileName);
                trie.insert(word, data);
            }
        }
    }
}
