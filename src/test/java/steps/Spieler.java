package de.fhkiel.tsw;

import de.fhkiel.tsw.armyoffrogs.Color;

public class Spieler {
    private Color spielerFarbe = Color.None;
    private boolean startspieler = false;
    private int age;

    public Spieler(Color neueFarbe, int age) {
        this.spielerFarbe = neueFarbe;
        this.age = age;
    }

    public Color getSpielerFarbe() {
        return spielerFarbe;
    }

    public void setSpielerFarbe(Color spielerFarbe) {
        this.spielerFarbe = spielerFarbe;
    }

    public boolean isStartspieler() {
        return startspieler;
    }

    public void setStartspieler(boolean startspieler) {
        this.startspieler = startspieler;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
