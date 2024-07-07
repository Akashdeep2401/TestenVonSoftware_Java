package de.fhkiel.tsw;

import de.fhkiel.tsw.armyoffrogs.Color;

/**
 * The class Froschstein represents a frog stone in the game.
 * It has a color.
 */
public class Froschstein {

  private final Color froschsteinFarbe;

  public Froschstein(Color steinFarbe) {
    this.froschsteinFarbe = steinFarbe;
  }

  public Froschstein() {
    this.froschsteinFarbe = Color.None;
  }
  
  public Color getFroschsteinFarbe() {
    return froschsteinFarbe;
  }
}
