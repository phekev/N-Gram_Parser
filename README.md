# OOSD
OOSD project nGram Parser

Run using 'java ie.atu.sw.Runner'

Program can be run by adding agruments as follows:
a) 	-help will display helpful info for using the CLI

b)
ie.atu.sw.Runner [inputFileDirectory] [nGramSize] [outputFilename] <Optional - Use a sliding window for producing nGrams>[Default False]
  eg. java ie.atu.sw.Runner textfiles 5 ngrams false
This will result in the program...
	1. "loading" any files present in the 'textfiles' directory
	2. processing the files in the folder using a ngram size of 5
	3. since sliding window is set to false, then block ngrams will be produced
		eg. "happydays" will produce "happy" with block ngrams
			but would produce "happy", "appyd", "ppyda", "pyday", "ydays" with sliding window set to true
	4. then 2 files will be output on sucessful parsing of the files
		i) ngrams_sorted_by_ngram.csv
		ii) nrams_sorted_by_counter.csv

c)
Alternatively, if no agruments are passed at the commandline then the menu will display
The menu will give options as follows
   (1) Specify Text File Directory
   (2) Specify n-Gram Size 1-6
   (3) Specify Output File
   (4) Use a sliding window? -- Optional ( block increment by default )
   (5) Build n-Grams
   (6) Print out current parameters
   (7) Quit

Option 5 will execute the ngram parser - if the correct parameters are not entered you will be prompted to enter them. 

Ngrams are stored as an object of type NGram in an array
These objects have two fields:
	String ngram
	long counter
which are accessed using getter and setter methods.

The program is setup to operate with a ngram size in the range 1-6. If an input larger that 6 is entered, 6 will be used as the ngram
The nearest larger prime number to 26^ngramSize is used for the array size. A prime number is used for better hashing.
If hash collisons occur, this is handled using linear probing.


The output files are sorted using comparators for the NGram.ngram & NGram.counter fields
There will be 2 files created - [yourFileName]_sorted_by_ngram.csv & [yourFileName]_sorted_by_counter.csv
