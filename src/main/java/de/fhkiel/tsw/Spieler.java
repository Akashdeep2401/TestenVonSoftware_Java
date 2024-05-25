package de.fhkiel.tsw;

import de.fhkiel.tsw.armyoffrogs.Color;

public class Spieler {
    Color SpielerFarbe = Color.None;

    public Spieler(Color NeueFarbe) {
        this.SpielerFarbe = NeueFarbe;
    }

    public Spieler(Color neueFarbe, int age) {
        this.SpielerFarbe = neueFarbe;
    }

    public Color getSpielerFarbe() {
        return SpielerFarbe;
    }

    public void setSpielerFarbe(Color spielerFarbe) {
        SpielerFarbe = spielerFarbe;
    }

    private boolean startspieler = false;

    private int ZugPosition;

    public boolean isStartspieler() {
        return startspieler;
    }

    public void setStartspieler(boolean startspieler) {
        this.startspieler = startspieler;
    }


    public void setZugPosition(int zugPosition) {
        ZugPosition = zugPosition;
    }

    public int getZugPosition() {
        return ZugPosition;
    }
}
