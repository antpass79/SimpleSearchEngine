# SimpleSearchEngine
search engine for finding words in file or text, written in java

**The following document explains the file reading option.**

### Assumptions
This simple search engine is based on some assumptions in order to make easier the base implementation.
There is always time to complicate one's life.

Below the assumptions:

- a *word* to find is a consecutive list of any characters
- the finding process is case sensitive
- the *whitespace* is used to separate words
- only *.txt* file can be processed
- only the first level inside the specified folder is processed (not sub folder)

## Run the program

### Pre-requirements
The console application is written with Java 11 and tested with JUnit 5.4.

### Steps to run
Once the package is downloaded [or unzipped], follow the below steps to run the program:

- Open a command prompt with administrative privileges

- Navigate to the *dist* folder

- Type:
    
        java -jar SimpleSearchEngine.jar <<directory_path>>

    where <<directory_path>> is the full or relative path of the directory containing all files in which the search will run.
    
    **Try "search dir" as <<directory_path>>**
    
- The command prompts will show:

        <<file_count>> files read in directory <<directory_path>>
        search>
        
    where <<file_count>> is the number of files processed.

- Type words separated from a whitespace, for example:

        search> word1 test1
        
- The answer will be:

        <<file_name1>>: <<percentage1>>%
        <<file_name2>>: <<percentage2>>%
        ....
        <<file_namen>>: <<percentagen>>%
        
    the percentage can be:
    
    - 0% if the file doesn't contain any input word
    - 100% if the file contains all input words
    
    the order will be from the higher percentage to the lower one. 
        
- To close the program, type:

        search> :quit
 
 ### Commands supported
 
 - **:quit** to exit the program
 - once the program starts, the command **search** is automatically ready to listen for a list of words, separated by a whitespace 

###Note on the tests

In order to fill the data structure, the ISearchIndexer can be used to implement a custom indexer. In the project the are two indexer:

* TextIndexer: it reads a list of string and organizes the words inside in the data structure.
* DirectoryIndexer: it reads all *.txt* files in a specified directory (not sub directory) and creates the data structure based on the found words.
* FakeDirectoryIndexer: it holds a fake directory structure for testing purpose.

For testing the engine, flow the TextIndexer and the FakeDirectoryIndexer are used, in order to avoid the directory reading.

## Architecture

The below class diagrams show the interactions among the components

![architecture](<assets/SearchEngine Class Diagrams.jpg>)

## How to implement a custom search engine

The below sections explain a words search engine to find words in files in a specific directory.

### SearchEngine Core

The *ISearchEngine<TInput, TOutput>* interface exposes 2 methods:

    ISearchEngine<TInput, TOutput> takeFirstResults(int count);
    TOutput[] search(TInput input);

The base abstract class that implements the above interface and provides the base methods to override and write the custom logic is *SearchEngine*.

The *SearchEngine<TInput, TOutput, TSearchingData>* has 3 overridable methods in which is possible to customize the flow of the searching process:

    List<TSearchOutput> onSearch(TInput input);
    // to find data in the data structure

    Stream<TOutput> onFilter(Stream<TOutput> unfilteredOutputStream);
    // to filter/prepare data with custom logic
    
    TOutput[] onConvert(Stream<TOutput> filteredOutputStream);
    // to convert to the output data

In addition the constructor takes the interface ISearchDataStructure<T>, that represents the data structure on which the searching process runs, with the below functions:

    boolean containsKey(String key);
    T find(String key);

### Indexer

For indexing the words in all files in a specific directory, the *DirectoryIndexer* can be used. It is possible to pass the relative or absolute path to the instance of the class and call the *index* function, it returns the data structure with all data.

The data structure is a trie: for each found word, the corresponding node is a list of file names in which the word is found.

### WordsSearchEngine

Imaging to implement a search engine to find words in files and gets the percentage of words found in each files, the class can be called *WordsSearchEngine*.

The *WordsSearchEngine* inherits from *SearchEngine*. The generic types for *SearchEngine* are:

- *TInput*: the list of words to find -> *String[]*
- *TOutput*: the results that we want to get -> *RankStatistics[]*
- *TSearchingData*: the data used by the data structure to hold information -> *ArrayList<String>* 


Through the constructor, the data structure is injected in the WordsSearchEngine.

WordsSearchEngine must override the 3 abstract methods of *SearchEngine*:

- *onSearch*: to find all results in the data structure (the implementation is a trie)
- *onFilter*: to filter/prepare the results, taking only a portion of the data and ordering the data by rank.
- *onConvert*: to convert the manipulated data to the output data (*RankStatistics*).

## Run tests
 
In the test folder there are a set of tests runnable from the development environment (IntelliJ IDEA).
The tests check:

- the data structure
- the search engine