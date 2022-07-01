package ie.atu.sw;

import java.util.Collections;
import java.util.Comparator;

public class NGram{
    private String ngram;
    private long counter;

    public NGram (String ngram, long counter) {
        this.ngram = ngram;
        this.counter = counter;
    }

    public String getNgram() {
        return ngram;
    }

    public void setNgram(String ngram) {
        this.ngram = ngram;
    }

    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }


/*
    Firstly, I implemented comparable to sort the ngram array. That was throwing NPE, so I added some
    null checking.
    Then, I
    @Override
    public int compareTo(NGram otherNgram) {
        if (this.getNgram() == null && otherNgram.getNgram() == null)
            return 0;
        else if (this.getNgram() == null && otherNgram.getNgram() != null)
            return -1;
        else if (this.getNgram() != null && otherNgram.getNgram() == null)
            return 1;
        else
            return this.getNgram().compareTo(otherNgram.getNgram());

    }
*/


}
