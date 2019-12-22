package com.wordsearchengine.models;

import java.util.HashMap;

public class RankBuilderInput {
    int wordCount;
    public int getWordCount() {
        return this.wordCount;
    }
    HashMap<String, Integer> map;
    public HashMap<String, Integer> getMap() {
        return this.map;
    }

    public RankBuilderInput(int wordCount, HashMap<String, Integer> map) {
        this.wordCount = wordCount;
        this.map = map;
    }
}
