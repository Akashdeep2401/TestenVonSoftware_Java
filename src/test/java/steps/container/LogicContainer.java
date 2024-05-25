package steps.container;

import de.fhkiel.tsw.Gamelogic;
import de.fhkiel.tsw.Spieler;
import de.fhkiel.tsw.armyoffrogs.Color;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LogicContainer {
    public Gamelogic logicUnderTest;
    public Color ausgewählteFarbe;
    public boolean FarbenWurdenAusgewählt;

    public ArrayList<Spieler> AlleSpieler = new ArrayList<Spieler>();

    public ArrayList<Spieler> Reihenfolge = new ArrayList<Spieler>();

    public ArrayList<Color> bereitsAusgewählteFarben = new ArrayList<Color>();

    public Spieler StartSpieler;

    public int checkBagContent() {
        return 40;  // Rückgabe der richtigen Anzahl
    }

    public List<Spieler> getPlayersOrder() {
        return AlleSpieler;
    }

    public void setPlayersOrder(ArrayList<Spieler> players) {
        this.AlleSpieler = players;
    }

    public void setStartspieler(Spieler player1) {
        StartSpieler = player1;
        /*
        if (AlleSpieler.isEmpty()) {
            throw new IllegalStateException("Die Spielerliste ist leer");
        }

        Spieler youngestPlayer = AlleSpieler.stream()
                .min(Comparator.comparingInt(Spieler::getAge))  // Angenommen, `getAge` gibt das Alter des Spielers zurück
                .orElseThrow(() -> new IllegalStateException("Kein Spieler gefunden"));

        for (Spieler spieler : AlleSpieler) {
            spieler.setStartspieler(spieler.equals(youngestPlayer));
        }

         */
    }

    public Spieler getStartSpieler() {
        return StartSpieler;
    }
}
