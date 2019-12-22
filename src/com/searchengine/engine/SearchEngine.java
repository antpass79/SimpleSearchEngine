package com.searchengine.engine;

import com.searchengine.datastructures.ISearchDataStructure;

import java.util.List;
import java.util.stream.Stream;

public abstract class SearchEngine<TInput, TOutput, TSearchingData> implements ISearchEngine<TInput, TOutput> {
    final ISearchDataStructure<TSearchingData> searchDataStructure;
    protected ISearchDataStructure<TSearchingData> getSearchDataStructure() {
        return this.searchDataStructure;
    }
    int takeFirstResults = 10;
    protected int getTakeFirstResults() {
        return this.takeFirstResults;
    }

    protected SearchEngine(ISearchDataStructure<TSearchingData> searchDataStructure) {
        this.searchDataStructure = searchDataStructure;
    }

    public final TOutput[] search(TInput input) {
        List<TOutput> unfilteredOutput = this.onSearch(input);
        Stream<TOutput> filteredOutputStream = this.onFilter(unfilteredOutput.stream());
        return this.onConvert(filteredOutputStream);
    }

    public final ISearchEngine<TInput, TOutput> takeFirstResults(int count) {
        this.takeFirstResults = count;
        return this;
    }

    protected abstract List<TOutput> onSearch(TInput input);

    protected Stream<TOutput> onFilter(Stream<TOutput> unfilteredOutputStream) {
        return unfilteredOutputStream
                .limit(this.getTakeFirstResults());
    }

    protected abstract TOutput[] onConvert(Stream<TOutput> filteredOutputStream);
}