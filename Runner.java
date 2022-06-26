package ie.atu.sw;

public class Runner {

    public static void main(String[] args) throws Exception {
        UserInputtedData userData = new UserInputtedData();
        if (args.length > 0 && args[0].equals("-help")){
            help();
            return;
        }
        else if ( args.length > 1) {
            runWithArgs(args, userData);
            return;
        }
        Menu m = new Menu();
        m.showMenu(userData);

    }

    private static void runWithArgs(String[] args, UserInputtedData userData) {
        if (args.length < 2) {
            throw new IllegalArgumentException(
                    "Not enough arguments supplied \n type -help for more info");
        }
        try {
            userData.setDirectory(args[0]);
                System.out.println(userData.getDirectory());
            userData.setNGramSize(Integer.parseInt(args[1]));
                System.out.println(userData.getNGramSize());
            userData.setOutputFilename(args[2]);
            System.out.println(userData.getOutputFilename());
            if ( args.length>3 && (args[3].toLowerCase().equals("true") || args[3].toLowerCase().equals("false") )) {
                userData.setIsWindowSliding(Boolean.parseBoolean(args[3]));
                    System.out.println(userData.getIsWindowSliding());
            }
            else userData.setIsWindowSliding(false);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        Menu menu = new Menu();
        menu.build(userData);
    }

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