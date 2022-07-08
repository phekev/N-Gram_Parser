package ie.atu.sw;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {

    public Menu() {

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
        System.out.print("<Select Option [1-6]>");
        System.out.println();
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
                case 4 -> getIsWindowSliding(userData); // Optional - false by default
                case 5 -> build(userData);
                case 6 -> printCurrentParameters(userData);
                case 7 -> exit();
            }
        }
    }

    /* Ask user for location of text files that are to be parsed*/
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
    /* This is optional. If selected it will set userData.setIsWindowSliding to TRUE*/
    private void getIsWindowSliding(UserInputtedData userData) {
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
        String filename = s.next();
        userData.setOutputFilename(filename + ".csv");
        try {
            showMenu(userData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /*
     * build()
     * Checks if all the parameters are set
     *  If yes, creates a parser object and then parses the directory given by the user
     *  After, it tries to save the data by calling NGramOutputter.save()
     *  Then the program exits
     */
    public void build(UserInputtedData userData) {
        if(checkIfAllParametersAreSet(userData)) {
            Parser parser = new Parser(userData);
            parser.parseDirectory(userData); //Call the parser
            System.out.println("Files parsed");
            try {
                System.out.println("Saving file...");
                NGramOutputter.save(parser.getTable(), userData.getOutputFilename()); //Call the Outputter with the table of ngrams and the output filename
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


    /*
    * checkIfAllParametersAreSet()
    * Check if all the necessary parameters of the UserInputtedData object are set to run the parser.
    * If not, the user is advised of the parameter which is not set and returned to the main menu
    * Otherwise, it returns true and the parser can run*/
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
    /*
     * printCurrentParameters()
     * Checks if the program parameters are set
     * Then prints the current parameter to the console
     */
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
    private void exit() {
        System.exit(0);
    }






}