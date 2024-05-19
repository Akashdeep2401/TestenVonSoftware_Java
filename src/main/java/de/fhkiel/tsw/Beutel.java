package de.fhkiel.tsw;

import de.fhkiel.tsw.armyoffrogs.Color;

import java.util.ArrayList;
import java.util.List;

public class Beutel {
    private boolean BeutelBefüllt = false;
    private List<Froschstein> Froschsteine;
    public Beutel(int frösche, List<Color> SpielerFarben) {
        Froschsteine = new ArrayList<>();
        this.AnzFrösche = frösche;
        for (Color einzSpielerFarbe : SpielerFarben) {
            for (int i = 0; i < 10; i++) {
                this.Froschsteine.add(new Froschstein(einzSpielerFarbe));
            }
        }
        this.BeutelBefüllt = true;
    }

    private int AnzFrösche;

    public int getAnzFrösche() {
        return AnzFrösche;
    }

    public void FroschNehmen() {
        if(AnzFrösche > 0) {
            AnzFrösche -= 1;
        }
    }

    public List<Froschstein> getFroschsteine() {
        return Froschsteine;
    }
}
