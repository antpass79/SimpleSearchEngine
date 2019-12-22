package com.searchengine.indexers.fakes;

public class FakeDirectory {
    String name;
    public String getName() {
        return this.name;
    }
    FakeFile[] files;
    public FakeFile[] getFiles() {
        return this.files;
    }
    public FakeDirectory(String name, FakeFile[] files) {
        this.name = name;
        this.files = files;
    }
}
