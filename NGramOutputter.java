package ie.atu.sw;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;


/*
    Class NGramOutputter primarily saves the array of ngrams to a file

    NGramOutputter#save(NGram[] table, String file)
        takes in the array of NGram objects and the filename for output file
        Using comparators the array is sorted by ngram (alphabetical order) and
        counter (decreasing numerical order).

    NGramOutputter#fileWriter(PrintWriter writer, NGram[] arr)
        writes each sorted version of the array to a file

    NGramOutputter#makeArrayWithNoNulls(NGram[] table)
        Comparator was giving NPE when sorting the array passed in from Parser.class. To solve this all non-null
        values are copied to a new array. It is this array that then gets sorted

 */
public class NGramOutputter {

    public static void save(NGram[] table, String file) throws Exception {
        // Comparator to compare by ngram eg, 'aa' will come before 'ab'
        // Comparator of type NGram. Using method reference declaration with <Classname>::<Methodname>
        Comparator<NGram> byNgram = Comparator.comparing(NGram::getNgram);
        // Comparator to compare by counter
        Comparator<NGram> byCounter = Comparator.comparing(NGram::getCounter);
        // Make a copy of the array with no NULLs
        NGram[] arr = makeArrayWithNoNulls(table);

        // Sort the array by NGram
        Arrays.sort(arr, byNgram);
        PrintWriter sortedByNgramOutput = new PrintWriter(new File(file+"_sorted_by_ngram.csv"));
            fileWriter(sortedByNgramOutput, arr);

       // Sort array by counter in  reversed order (largest -> smallest)
        Arrays.sort(arr, byCounter.reversed());
        PrintWriter sortedByCounterOutput = new PrintWriter(new File(file+"_sorted_by_counter.csv"));
            fileWriter(sortedByCounterOutput, arr);

    }

    // Write the contents of an array to the instance of PrintWriter, then close the PrintWriter
    private static void fileWriter (PrintWriter writer, NGram[] arr) {
        for (int row=0; row < arr.length; row++) {
            writer.write("%s, %s\n".formatted(arr[row].getNgram(), arr[row].getCounter()));
        }
        writer.close();
    }
    
    // Was getting NPE when sorting using Arrays.sort(), so to get around this issue
    // copy the non-null indexes to a new array (of length = original array).
    // Then copy the contents to a final array of correct size
    private static NGram[] makeArrayWithNoNulls (NGram[] table) {
        // Array to store non-null indexes
        NGram[] tableWithNoNulls = new NGram[table.length];
        // counter to enumerate the actual size needed for the final array
        int counter = -1;
        // Loop over original array, copy any non-null index to tableWithNoNulls
        for (int i=0; i<table.length; i++) {
            if (table[i] != null) {
                counter++;  // Add one to the counter - https://www.lysator.liu.se/c/pikestyle.html
                tableWithNoNulls[counter] = table[i];
            }
        }
        // Create a final correctly sized array using the counter
        NGram[] correctlySizedTable = new NGram[counter];
        System.arraycopy(tableWithNoNulls, 0, correctlySizedTable, 0, counter);
        return correctlySizedTable;
    }

}