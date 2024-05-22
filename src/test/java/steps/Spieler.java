package steps;


import de.fhkiel.tsw.armyoffrogs.Color;

public class Spieler {
    private Color spielerFarbe = Color.None;
    private boolean startspieler = false;  // Neues Feld für Startspieler

    public Spieler(Color neueFarbe) {
        this.spielerFarbe = neueFarbe;
    }

    public Color getSpielerFarbe() {
        return spielerFarbe;
    }

    public void setSpielerFarbe(Color spielerFarbe) {
        this.spielerFarbe = spielerFarbe;
    }

    public boolean isStartspieler() {
        return startspieler;  // Getter für Startspieler
    }

    public void setStartspieler(boolean startspieler) {
        this.startspieler = startspieler;
    }
}
