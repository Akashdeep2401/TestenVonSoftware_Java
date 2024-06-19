package steps.container;

import de.fhkiel.tsw.Froschstein;
import de.fhkiel.tsw.Gamelogic;
import de.fhkiel.tsw.Spieler;
import de.fhkiel.tsw.armyoffrogs.Color;
import de.fhkiel.tsw.armyoffrogs.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LogicContainer {
  public Gamelogic logicUnderTest;
  public Color ausgewählteFarbe;
  public boolean FarbenWurdenAusgewählt;
  public Spieler Playingnow;
  public int AnzFröscheBeutelVorAktion;
  public int AnzFröscheInventarVorAktion;
  public int AnzFröscheAufSpielfeldVorAktion;

  public ArrayList<Spieler> AlleSpieler = new ArrayList<Spieler>();

  public ArrayList<Spieler> Reihenfolge = new ArrayList<Spieler>();

  public ArrayList<Color> bereitsAusgewählteFarben = new ArrayList<Color>();

  public Spieler StartSpieler;

  public Color[] TestColors = {Color.Blue, Color.Red, Color.White, Color.Green};

  public ArrayList<Color> TestColorsArrayList = new ArrayList<Color>();

  public List<Froschstein> testFroschsteinInventar;
  public Spieler testSpieler;
  public Set<Position> testBoard;

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
