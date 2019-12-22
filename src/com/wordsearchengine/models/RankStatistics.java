package com.wordsearchengine.models;

public class RankStatistics {

    public RankStatistics(String file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    private final String file;
    public String getFile() {
        return this.file;
    }

    private final int rank;
    public int getRank() {
        return rank;
    }
}
