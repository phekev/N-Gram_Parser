package ie.atu.sw;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.*;

public class NGramOutputter {

    public static void save(NGram[] table, String file) throws Exception {
        PrintWriter writer = new PrintWriter(new File(file));
        NGram[] arr = makeArrayWithNoNulls(table);
        Arrays.sort(arr);
        for (int row=0; row < arr.length; row++) {
            if (arr[row] != null)
                writer.write("%s, %s\n".formatted(arr[row].getNgram(), arr[row].getCounter()));
        }
        writer.close();
    }

    private static NGram[] makeArrayWithNoNulls (NGram[] table) {
        NGram[] tableWithNoNulls = new NGram[table.length];
        int counter = -1;
        for (int i=0; i<table.length; i++) {
            if (table[i] != null) {
                counter++;
                tableWithNoNulls[counter] = table[i];
            }
        }
        NGram[] correctlySizedTable = new NGram[counter];
        System.arraycopy(tableWithNoNulls, 0, correctlySizedTable, 0, counter);
        return correctlySizedTable;
    }

}