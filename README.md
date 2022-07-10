# OOSD
OOSD project nGram Parser

Run using 'java ie.atu.sw.Runner'

Program can be run by adding agruments as follows:
a) 	-help will display helpful info for using the CLI

b)
ie.atu.sw.Runner [inputFileDirectory] [nGramSize] [outputFilename] <Optional - Use a sliding window for producing nGrams>[Default False]
  eg. java ie.atu.sw.Runner textfiles 5 ngrams false
  Alternatively, it can be run from the jarfile java -jar ngrammer.jar textfiles 5 ngrams false
  ![ngram_run_from_cmd](https://user-images.githubusercontent.com/81191184/178158992-ee4f651a-dad6-4019-b574-875a4eef6587.jpg)
  
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
   
   ![ngram_run_from_menu](https://user-images.githubusercontent.com/81191184/178159068-7ab44b58-5f69-4752-8052-eecab0ed7843.jpg)


Option 5 will execute the ngram parser - if the correct parameters are not entered you will be prompted to enter them. 

![ngram_run_from_menu](https://user-images.githubusercontent.com/81191184/178159095-bad678e2-8474-4afa-af2e-774a665e3341.jpg)

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

![ngram_sorted_alphabetically](https://user-images.githubusercontent.com/81191184/178159086-3834f11c-cea0-480a-8376-3d0ef87e9c33.jpg)
![ngram_sorted_numerically](https://user-images.githubusercontent.com/81191184/178159205-47102a48-3c91-45a9-9905-0f655178f0f1.jpg)


