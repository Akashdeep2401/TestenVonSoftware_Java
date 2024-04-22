package de.fhkiel.tsw;

public class Beutel {
    public Beutel(int frösche) {
        this.frösche = frösche;
    }
    public Beutel() {
        this(40);
    }
    private int frösche;

    public int getFrösche() {
        return frösche;
    }

    public void FroschNehmen() {
        if(frösche > 0) {
            frösche -= 1;
        }
    }
}
