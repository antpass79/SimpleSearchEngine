# SimpleSearchEngine
search engine for finding words in file or text, written in java

**The following document explains the file reading option.**

### Assumptions
This simple search engine is based on some assumptions in order to make easier the base implementation.
There is always time to complicate one's life. All components involved in the library are expandable and replaceable.

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

- Open a command prompt

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

 ## Run tests
 
In the test folder there are a set of tests runnable from the development environment.
The tests check:

- the data structure
- the statistics builder
- the search engine