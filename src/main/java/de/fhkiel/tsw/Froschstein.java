package de.fhkiel.tsw;

import de.fhkiel.tsw.armyoffrogs.Color;

public class Froschstein {

    private Color FroschsteinFarbe;

    public Froschstein(Color SteinFarbe) {
        this.FroschsteinFarbe = SteinFarbe;
    }
    public Froschstein() {
        this.FroschsteinFarbe = Color.None;
    }

    //public Froschstein() {}


    public Color getFroschsteinFarbe() {
        return FroschsteinFarbe;
    }
}
