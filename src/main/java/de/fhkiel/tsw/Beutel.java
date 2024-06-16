package de.fhkiel.tsw;

import de.fhkiel.tsw.armyoffrogs.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Beutel {
  private boolean BeutelBefüllt = false;
  private List<Froschstein> Froschsteine;

  public Beutel(List<Color> SpielerFarben) {
    this.AnzFrösche = 0;
    Froschsteine = new ArrayList<>();
    for (Color einzSpielerFarbe : SpielerFarben) {
      for (int i = 0; i < 10; i++) {
        this.AnzFrösche++;
        this.Froschsteine.add(new Froschstein(einzSpielerFarbe));
      }
    }
    this.BeutelBefüllt = true;
  }

  private int AnzFrösche;

  public int getAnzFrösche() {
    AnzFrösche = Froschsteine.size();
    return AnzFrösche;
  }

  public boolean froschNehmen(Spieler EinSpieler) {
    if (AnzFrösche > 0) {
      int Random = new Random().nextInt(Froschsteine.size());
      if (EinSpieler.froschHinzufügen(Froschsteine.get(Random))) {
        Froschsteine.remove(Random);
        AnzFrösche -= 1;
        return true;
      } else {
        return false;
      }
    }
    return false;
  }

  public List<Froschstein> getFroschsteine() {
    return Froschsteine;
  }
}
