package ie.atu.sw;

public class NGram implements Comparable<NGram>{
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



}
