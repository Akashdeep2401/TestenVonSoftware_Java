package de.fhkiel.tsw;

import de.fhkiel.tsw.armyoffrogs.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Beutel {
  private final List<Froschstein> Froschsteine;
  private boolean BeutelBefüllt = false;

  private Random random = new Random();
  private int AnzFrösche;
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

  public int getAnzFrösche() {
    AnzFrösche = Froschsteine.size();
    return AnzFrösche;
  }

  public boolean froschNehmen(Spieler EinSpieler) {
    if (AnzFrösche > 0) {
      int Random = random.nextInt(Froschsteine.size());
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
