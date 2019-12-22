package com.searchengine.engine;

import com.searchengine.datastructures.ISearchDataStructure;

import java.util.List;
import java.util.stream.Stream;

public abstract class SearchEngine<TSearchInput, TSearchOutput, TSearchingData> implements ISearchEngine<TSearchInput, TSearchOutput> {
    final ISearchDataStructure<TSearchingData> searchDataStructure;
    protected ISearchDataStructure<TSearchingData> getSearchDataStructure() {
        return this.searchDataStructure;
    }
    int takeFirstResults = 10;
    protected int getTakeFirstResults() {
        return this.takeFirstResults;
    }

    public SearchEngine(ISearchDataStructure<TSearchingData> searchDataStructure) {
        this.searchDataStructure = searchDataStructure;
    }

    public final TSearchOutput[] search(TSearchInput input) {
        List<TSearchOutput> unfilteredOutput = this.onSearch(input);
        Stream<TSearchOutput> filteredOutputStream = this.onFilter(unfilteredOutput.stream());
        return this.onConvert(filteredOutputStream);
    }

    public final ISearchEngine<TSearchInput, TSearchOutput> takeFirstResults(int count) {
        this.takeFirstResults = count;
        return this;
    }

    protected abstract List<TSearchOutput> onSearch(TSearchInput input);

    protected Stream<TSearchOutput> onFilter(Stream<TSearchOutput> unfilteredOutputStream) {
        return unfilteredOutputStream
                .limit(this.getTakeFirstResults());
    }

    protected abstract TSearchOutput[] onConvert(Stream<TSearchOutput> filteredOutputStream);
}