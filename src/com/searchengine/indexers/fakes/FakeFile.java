package com.searchengine.indexers.fakes;

public class FakeFile {
    String name;
    public String getName() {
        return this.name;
    }
    String[] words;
    public String[] getWords() {
        return this.words;
    }
    public FakeFile(String name, String[] words) {
        this.name = name;
        this.words = words;
    }
}
