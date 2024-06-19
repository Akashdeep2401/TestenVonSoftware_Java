package de.fhkiel.tsw;

import de.fhkiel.tsw.armyoffrogs.Color;

import java.util.ArrayList;
import java.util.List;

public class Spieler {


  private final List<Froschstein> Inventar = new ArrayList<>();
  Color SpielerFarbe = Color.None;
  private Beutel beutel;
  private boolean startspieler = false;
  private int ZugPosition;

  public Spieler(Color NeueFarbe) {
    this.SpielerFarbe = NeueFarbe;
    this.beutel = new Beutel(new ArrayList<>()); //Initialize the bag
  }

  public Spieler(Color neueFarbe, int age) {
    this.SpielerFarbe = neueFarbe;
  }

  public Color getSpielerFarbe() {
    return SpielerFarbe;
  }

  public void setSpielerFarbe(Color spielerFarbe) {
    SpielerFarbe = spielerFarbe;
  }

  public boolean isStartspieler() {
    return startspieler;
  }

  public void setStartspieler(boolean startspieler) {
    this.startspieler = startspieler;
  }

  public int getZugPosition() {
    return ZugPosition;
  }

  public void setZugPosition(int zugPosition) {
    ZugPosition = zugPosition;
  }

  public boolean froschHinzufügen(Froschstein EinFroschstein) {
    if (Inventar.size() >= 2) {
      return false;
    }
    Inventar.add(EinFroschstein);
    return true;
  }

  public int getInventorySize() {
    return Inventar.size();
  }

  public List<Froschstein> getInventar() {
    return Inventar;
  }
}
