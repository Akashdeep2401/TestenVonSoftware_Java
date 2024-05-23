package de.fhkiel.tsw;

import de.fhkiel.tsw.armyoffrogs.Color;

public class Spieler {
    Color SpielerFarbe = Color.None;

    public Spieler(Color NeueFarbe) {
        this.SpielerFarbe = NeueFarbe;
    }

    public Color getSpielerFarbe() {
        return SpielerFarbe;
    }

    public void setSpielerFarbe(Color spielerFarbe) {
        SpielerFarbe = spielerFarbe;
    }

    public boolean isStartspieler() {
        return true;
    }
}
