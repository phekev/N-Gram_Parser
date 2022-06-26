package ie.atu.sw;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {



    public Menu() {

    }

    public void showMenu(UserInputtedData userData) throws Exception {
        Menu.menu();
        Scanner s = new Scanner(System.in);
        int choice = s.nextInt();
        while(true){
            switch (choice) {
                case 1 -> getInputDirectory(s, userData);
                case 2 -> getSize(s, userData);
                case 3 -> getOutputFileName(s, userData);
                case 4 -> getIsWindowSliding(s, userData);
                case 5 -> build(userData);
                case 6 -> printCurrentParameters(userData);
                case 7 -> exit();
            }
        }
    }

    private void printCurrentParameters(UserInputtedData userData) {
        if (userData.getDirectory() != null)
            System.out.println("Input directory -> " + userData.getDirectory());
        else
            System.out.println("Input directory -> **********");

        if (userData.getNGramSize() != 0)
           System.out.println("nGram size -> " + userData.getNGramSize());
        else
           System.out.println( ("nGram Size -> **********") );
        if (userData.getOutputFilename() != null)
            System.out.println("Output filename -> " + userData.getOutputFilename());
        else
            System.out.println("Output filename -> **********");
        if (userData.getIsWindowSliding() != false)
            System.out.println("Is nGram window sliding -> " + userData.getIsWindowSliding());
        else
            System.out.println("Is nGram window sliding? -> " + userData.getIsWindowSliding());
        try {
            showMenu(userData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void getInputDirectory(Scanner s, UserInputtedData userData) throws Exception {

        System.out.println("Enter the directory location ");
        System.out.println("where the text files to process are located...");
        String directory = s.next();
        userData.setDirectory(directory);
        showMenu(userData);
    }

    private void getSize(Scanner s, UserInputtedData userData) {
        System.out.println("Enter the n Gram size for analysis...");
        System.out.println("The n Gram should be in the range 1-6");
        int nGramSize = s.nextInt();
        userData.setNGramSize(nGramSize);
        try {
            showMenu(userData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void getIsWindowSliding(Scanner s, UserInputtedData userData) {
        userData.setIsWindowSliding(true);
        System.out.println("nGram window sliding has been set to True");
        try {
            showMenu(userData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void getOutputFileName(Scanner s, UserInputtedData userData) {
        System.out.println("Enter the OUTPUT filename");
        System.out.println("Example: 'file' will save as 'file.csv'");
        String filename = s.next();
        userData.setOutputFilename(filename + ".csv");
        try {
            showMenu(userData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void build(UserInputtedData userData) {
        if(checkIfAllParametersAreSet(userData)) {
            Parser parser = new Parser(userData);
            parser.parseDirectory(userData);
            System.out.println("Files parsed");
            try {
                System.out.println("Saving file...");
                NGramOutputter.save(parser.getTable(), userData.getOutputFilename());
                System.out.println("Done");
                exit();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                showMenu(userData);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    private boolean checkIfAllParametersAreSet(UserInputtedData userData) {
        if ( userData.getDirectory() == null ) {
            System.out.println("Directory of INPUT files is not set");
            System.out.println("Please set the directory now and select Build again");
            return false;
        }
        if ( userData.getNGramSize() == 0 ) {
            System.out.println("nGram size has not been not set");
            System.out.println("Please set the nGram size now and select Build again");
            return false;
        }
        if ( userData.getOutputFilename() == null ) {
            System.out.println("Filename of OUTPUT file has not been set");
            System.out.println("Please set the filename now and select Build again");
            return false;
        }
        return true;
    }

    private void exit() {
        System.exit(0);
    }

    private static void menu() {
        System.out.println(ConsoleColour.WHITE);
        System.out.println("************************************************************");
        System.out.println("*      ATU - Dept. Computer Science & Applied Physics      *");
        System.out.println("*                                                          *");
        System.out.println("*                  N-Gram Frequency Builder                *");
        System.out.println("*                                                          *");
        System.out.println("************************************************************");
        System.out.println("(1) Specify Text File Directory");
        System.out.println("(2) Specify n-Gram Size 1-6");
        System.out.println("(3) Specify Output File");
        System.out.println("(4) Use a sliding window? -- Optional ( block increment by default )");
        System.out.println("(5) Build n-Grams ");
        System.out.println("(6) Print out current parameters ");
        System.out.println("(7) Quit");

        //Output a menu of options and solicit text from the user
        System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
        System.out.print("Select Option [1-6]>");
        System.out.println();
    }

    private void progressMeter() {
        System.out.print(ConsoleColour.YELLOW);	//Change the colour of the console text
        int size = 100;							//The size of the meter. 100 equates to 100%
        for (int i =0 ; i < size ; i++) {		//The loop equates to a sequence of processing steps
            printProgress(i + 1, size); 		//After each (some) steps, update the progress meter
            try {
                Thread.sleep(10); 				//Slows things down so the animation is visible
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     *  Terminal Progress Meter
     *  -----------------------
     *  You might find the progress meter below useful. The progress effect
     *  works best if you call this method from inside a loop and do not call
     *  System.out.println(....) until the progress meter is finished.
     *
     *  Please note the following carefully:
     *
     *  1) The progress meter will NOT work in the Eclipse console, but will
     *     work on Windows (DOS), Mac and Linux terminals.
     *
     *  2) The meter works by using the line feed character "\r" to return to
     *     the start of the current line and writes out the updated progress
     *     over the existing information. If you output any text between
     *     calling this method, i.e. System.out.println(....), then the next
     *     call to the progress meter will output the status to the next line.
     *
     *  3) If the variable size is greater than the terminal width, a new line
     *     escape character "\n" will be automatically added and the meter won't
     *     work properly.
     *
     *
     */
    public static void printProgress(int index, int total) {
        if (index > total) return;	//Out of range
        int size = 50; 				//Must be less than console width
        char done = '█';			//Change to whatever you like.
        char todo = '░';			//Change to whatever you like.

        //Compute basic metrics for the meter
        int complete = (100 * index) / total;
        int completeLen = size * complete / 100;

        /*
         * A StringBuilder should be used for string concatenation inside a
         * loop. However, as the number of loop iterations is small, using
         * the "+" operator may be more efficient as the instructions can
         * be optimized by the compiler. Either way, the performance overhead
         * will be marginal.
         */
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append((i < completeLen) ? done : todo);
        }

        /*
         * The line feed escape character "\r" returns the cursor to the
         * start of the current line. Calling print(...) overwrites the
         * existing line and creates the illusion of an animation.
         */
        System.out.print("\r" + sb + "] " + complete + "%");

        //Once the meter reaches its max, move to a new line.
        if (done == total) System.out.println("\n");
    }


}