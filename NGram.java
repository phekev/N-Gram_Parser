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

}
