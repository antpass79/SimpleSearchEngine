package com.searchengine.indexers;

import com.searchengine.datastructures.ISearchDataStructure;
import com.searchengine.datastructures.Trie;

import java.util.ArrayList;

public class TextIndexer implements ISearchIndexer<ArrayList<String>> {

    final String[] text;

    public TextIndexer(String[] text) {
        this.text = text;
    }

    @Override
    public ISearchDataStructure<ArrayList<String>> index() {
        Trie<ArrayList<String>> searchDataStructure = new Trie<ArrayList<String>>();
        this.fillSearchDataStructure(searchDataStructure);

        return searchDataStructure;
    }
    @Override
    public String[] getSourceNames() {
        return this.text;
    }

    private void fillSearchDataStructure(Trie<ArrayList<String>> trie) {
        for (String text: this.text) {
            final String[] words = text.split(" ");
            for (String word: words) {
                if (trie.containsKey(word)) {
                    trie.find(word).add(text);
                }
                else {
                    ArrayList<String> data = new ArrayList<String>();
                    data.add(text);
                    trie.insert(word, data);
                }
            }
        }
    }
}
