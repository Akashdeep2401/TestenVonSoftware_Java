package de.fhkiel.tsw;

import de.fhkiel.tsw.armyoffrogs.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The class Spieler represents a player in the game.
 * It has a color, an inventory and a position on the board.
 */
public class Spieler {


  private final List<Froschstein> inventar = new ArrayList<>();
  Color spielerFarbe = Color.None;
  private boolean startspieler = false;
  private int zugPosition;

  public Spieler(Color neueFarbe) {
    this.spielerFarbe = neueFarbe;
  }

  public Spieler(Color neueFarbe, int age) {
    this.spielerFarbe = neueFarbe;
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

  public int getZugPosition() {
    return zugPosition;
  }

  public void setZugPosition(int zugPosition) {
    this.zugPosition = zugPosition;
  }

  /**
   * Adds a frog to the inventory of the player.
   *
   * @param einFroschstein .
   *
   * @return .
   */
  public boolean froschHinzufuegen(Froschstein einFroschstein) {
    if (inventar.size() >= 2) {
      return false;
    }
    inventar.add(einFroschstein);
    return true;
  }

  public int getInventorySize() {
    return inventar.size();
  }

  public List<Froschstein> getInventar() {
    return inventar;
  }
}
