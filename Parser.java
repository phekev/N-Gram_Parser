package ie.atu.sw;

import java.io.*;
import java.nio.file.FileSystems;
import java.util.Arrays;
import java.util.Objects;


public class Parser {

    public Object[][] getTable() {
        return table;
    }

    private Object[][] table;

    // List of the 1st prime number larger than 26^nGramSize
    private final int[] PRIMES = { 29, 677, 17579, 456979, 11881379, 308915797 };

    public Parser(UserInputtedData userData) {
        // If nGram size is in range 1-6 use the nGram size to set the table size
        if (userData.getNGramSize() <= 6)
            table = new Object[PRIMES[userData.getNGramSize()-1]][2];
        // If not, then use the largest PRIME in the array to set the table size - this may result in lost data
        else
            table = new Object[PRIMES[PRIMES.length-1]][2];
    }

    public void addNGram (String ngram) {
        int index = ngram.hashCode() % this.table.length;
        long counter = 1;
        if ( this.table[index][0] == null ) {
            this.table[index][0] = ngram;
            this.table[index][1] = counter;
        } else {
            getNextAvailableIndex(index, ngram, counter);
        }
    }

    // There has been a collision while hashing. Use linear probing to resolve this
    private void getNextAvailableIndex(int index, String ngram, long counter) {
        // Current index contains the ngram that is to be added - increment with the counter
        if (Objects.equals(ngram, this.table[index][0])) {
            this.table[index][1] = (long) this.table[index][1] + counter;
            return;
        }
        // Current index is empty - ngram can be entered here
        else if (this.table[index][0] == null) {
            this.table[index][0] = ngram;
            this.table[index][1] = counter;
            return;
        }
        // Increment index and call getNextAvailableIndex
        else {
            index++;
            // At end of table - loop back to 0th index and continue searching for a free index
            if (index == this.table.length) {
                index = 0;
                System.out.println("********** Looped to start of table ***********");
            }
            getNextAvailableIndex(index, ngram, counter);
        }
    }


    public void processNGram (String words, UserInputtedData userData) {
        // If nGram window is sliding - increment value is 1
        int incrementSize = userData.getIsWindowSliding() ? 1 : userData.getNGramSize();
        for (int i=0; i<words.length()-userData.getNGramSize(); i+=incrementSize){
            addNGram(words.substring(i, i + userData.getNGramSize()));}
    }

    public void parse (String file, UserInputtedData userData) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file) ))) {
                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    String[] words = line.split( "\\s+" );
                    for (int i=0; i< words.length; i++) {
                        sb.append(words[i].trim().toLowerCase().replaceAll("[^a-zA-Z]", ""));
                    }
                }
                processNGram(sb.toString(), userData);
            } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }

    //  Parser#parseDirectory - creates an array of files to be parsed & 

    public void parseDirectory (UserInputtedData userData ){
        File f = new File(userData.getDirectory());
        System.out.println("f...."+f.getAbsolutePath());
        String[] files = f.list();
        System.out.println(Arrays.deepToString(files));
        if(files != null) {
            String fileSeparator = FileSystems.getDefault().getSeparator();     //Get platform appropriate file separator / on Unix based systems or \ on Windows
            for (String file : files) {
                parse(f.getAbsolutePath() + fileSeparator + file, userData);         //Creating full path for each file folderName/fileName
            }
        } else {
            System.out.println("The directory name entered does not exist or is referenced incorrectly");
            System.exit(0);
        }
    }


}