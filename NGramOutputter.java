package ie.atu.sw;

import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;

public class NGramOutputter {

    public static void save(Object[][] table, String file) throws Exception {
        PrintWriter writer = new PrintWriter(new File(file));
        Sort sort = new Sort();
        sort.sort(table);
        for (int row=0; row < table.length; row++) {
            if (table[row][0] != null)
                writer.write("%s, %s\n".formatted(table[row][0], table[row][1]));
        }
        writer.close();
    }

}