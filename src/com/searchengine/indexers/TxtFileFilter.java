package com.searchengine.indexers;

import java.io.File;
import java.io.FilenameFilter;

public class TxtFileFilter implements IFileFilter {
    final FilenameFilter filenameFilter = new FilenameFilter() {
        public boolean accept(File file, String name) {
            return name.endsWith(".txt");
        }
    };

    @Override
    public String[] filter(File file) {
        return file.list(this.filenameFilter);
    }
}
