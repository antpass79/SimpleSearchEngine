package com.searchengine.statistics;

public class SearchStatistics<T> {

    SearchStatistics(T data, int rank) {
        this.data = data;
        this.rank = rank;
    }

    private final T data;
    public T getData() {
        return this.data;
    }

    private final int rank;
    public int getRank() {
        return rank;
    }
}
