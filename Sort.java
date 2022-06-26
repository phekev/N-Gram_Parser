package ie.atu.sw;



public class Sort {

    public void sort ( Object[][] table ) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (table[i][0] != null && table[j][0] != null) {
                    int value = compare((String) table[i][0], (String) table[j][0]);
                    if (value > 0) {
                        Object[] temp = new Object[2];

                        temp[0] = table[i][0];
                        temp[1] = table[i][1];

                        table[i][0] = table[j][0];
                        table[i][1] = table[j][1];

                        table[j][0] = temp[0];
                        table[j][1] = temp[1];
                    }
                }
            }
        }
    }


    public int compare(String a, String b) {
        return b.compareTo(a);
    }
}
