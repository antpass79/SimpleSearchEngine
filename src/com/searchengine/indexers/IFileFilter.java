package com.searchengine.indexers;

import java.io.File;

interface IFileFilter {
    String[] filter(File file);
}
