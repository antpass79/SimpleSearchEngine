package com.wordsearchengine.models;

public class SearchInput {
    String[] words;
    public String[] getWords() {
        return this.words;
    }
    int takeFirstResults;
    public int getTakeFirstResults() {
        return this.takeFirstResults;
    }

    public SearchInput(String[] words, int takeFirstResults) {
        this.words = words;
        this.takeFirstResults = takeFirstResults;
    }
}
