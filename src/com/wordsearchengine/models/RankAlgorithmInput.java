package com.wordsearchengine.models;

public class RankAlgorithmInput {
    int wordOccurrences;
    public int getWordOccurrences() {
        return this.wordOccurrences;
    }
    int wordCount;
    public int getWordCount() {
        return this.wordCount;
    }

    public RankAlgorithmInput(int wordOccurrences, int wordCount) {
        this.wordOccurrences = wordOccurrences;
        this.wordCount = wordCount;
    }
}
