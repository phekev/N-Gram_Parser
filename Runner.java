package ie.atu.sw;

public class Runner {

    public static void main(String[] args) throws Exception {
        // userData object will hold the user supplied info (directory name, ngram size, output filename etc)
        UserInputtedData userData = new UserInputtedData();
        // Check if the user has entered command line arguments
        // If the agrument typed is '-help', call help()
        if (args.length > 0 && args[0].equals("-help")){
            help();
            return;
        }
        // There are arguments but the first is not '-help'
        // call runWithArgs()
        else if ( args.length > 0) {
            runWithArgs(args, userData);
            return;
        }
        // There are no command line arguments run as normal
        Menu m = new Menu();
        m.showMenu(userData);

    }

        // Tries to run the program with the supplied arguments
    private static void runWithArgs(String[] args, UserInputtedData userData) {
        // If there are fewer than 3 arguments the program can't run.
        // Throw an exception and tell user why
        if (args.length < 2) {
            throw new IllegalArgumentException(
                    "Not enough arguments supplied \n type -help for more info");
        }
        // There should be enough arguments, try to run
        try {
            // Set input directory name from index 0
            userData.setDirectory(args[0]);
            // Set ngram size from index 1. ParseInt() as argrs[] is of type String
            userData.setNGramSize(Integer.parseInt(args[1]));
            // Set output filename
            userData.setOutputFilename(args[2]);
            // Optional - if there is a 4th index and it equals either true or false
            if ( args.length>3 && (args[3].toLowerCase().equals("true") || args[3].toLowerCase().equals("false") )) {
                userData.setIsWindowSliding(Boolean.parseBoolean(args[3]));
                    System.out.println(userData.getIsWindowSliding());
            }
            // If there was not a 4th index, setIsWindowSliding = false
            else userData.setIsWindowSliding(false);
            // Catching the ngram size not being of type Number
        } catch (NumberFormatException e) {
            System.out.println("Please input an number in the range 1-6");
            throw new RuntimeException(e);
        }
        Menu menu = new Menu();
        // Run build() which executes the ngram parser
        menu.build(userData);
    }

    // Prints out helpful info for commandline usage
    private static void help() {
        System.out.println("*   N-Gram Frequency Builder     *");
        System.out.println("*   This program can run using command line arguments" +
                            "\n" +
                            "\n");
        System.out.println("*Usage: Runner [directory] [nGramSize] [outputFilename] optional:[useSlidingWindowForNGrams] ");
        System.out.println("*      [directory]      -  name directory containing files to be parsed");
        System.out.println("*      [nGramSize]      -  size of nGram to be used e.g. 5");
        System.out.println("*      [outputFilename] -  filename to output results");
        System.out.println("*      optional: [useSlidingWindowForNGrams]");
        System.out.println("                        - True: this will use a sliding window to generate nGrams");
        System.out.println("                        - False: default - will use a block increment of [nGramSize]");
    }
}