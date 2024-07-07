package de.fhkiel.tsw;

import de.fhkiel.tsw.armyoffrogs.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The class Beutel represents the bag of frogs in the game.
 * It has a list of frog stones and a boolean to check if the bag is filled.
 */
public class Beutel {
  private final List<Froschstein> froschsteine;

  private Random random = new Random();
  private int anzFroesche;

  /**
   * Constructor for the class Beutel.
   *
   * @param spielerFarben .
   *
   */
  public Beutel(List<Color> spielerFarben) {
    this.anzFroesche = 0;
    froschsteine = new ArrayList<>();
    for (Color einzSpielerFarbe : spielerFarben) {
      for (int i = 0; i < 10; i++) {
        this.anzFroesche++;
        this.froschsteine.add(new Froschstein(einzSpielerFarbe));
      }
    }
  }

  public int getAnzFroesche() {
    anzFroesche = froschsteine.size();
    return anzFroesche;
  }

  /**
   * Method to take a frog from the bag.
   *
   * @param einSpieler .
   *
   * @return .
   *
   */
  public boolean froschNehmen(Spieler einSpieler) {
    if (anzFroesche > 0) {
      int newRandom = random.nextInt(froschsteine.size());
      if (einSpieler.froschHinzufuegen(froschsteine.get(newRandom))) {
        froschsteine.remove(newRandom);
        anzFroesche -= 1;
        return true;
      } else {
        return false;
      }
    }
    return false;
  }

  public List<Froschstein> getFroschsteine() {
    return froschsteine;
  }
}
