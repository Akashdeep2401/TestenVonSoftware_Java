package de.fhkiel.tsw;

import de.fhkiel.tsw.armyoffrogs.Color;
import de.fhkiel.tsw.armyoffrogs.Game;
import de.fhkiel.tsw.armyoffrogs.Position;

import java.util.*;

public class Gamelogic implements Game {


  private Beutel SpielBeutel;
  private int iSpieler;

  private boolean GameIsRunning;

  private Color[] CPlayers;
  private Spieler[] AlleSpieler;

  private Spieler[] Reihenfolge;

  int LastPlayer;

  private int currentPlayer;
  private Set<Position> Board = new HashSet<>();
  private Color ausgewählterFrosch;
  private ZugAktion zugAktion;

  public Gamelogic() {
    GameIsRunning = false;
    iSpieler = 0;
    LastPlayer = 0;
    currentPlayer = 0;
    SpielBeutel = new Beutel(new ArrayList<>());
    CPlayers = new Color[0];
    zugAktion = new ZugAktion();
  }

  public Gamelogic(Set<Position> newBoard) {
    GameIsRunning = false;
    iSpieler = 0;
    LastPlayer = 0;
    currentPlayer = 0;
    SpielBeutel = new Beutel(new ArrayList<>());
    CPlayers = new Color[0];
    zugAktion = new ZugAktion();
    Board = newBoard;
  }

  @Override
  public boolean newGame(int i) {
    return startGame(i,
        new ArrayList<>(Arrays.asList(Color.Red, Color.Blue, Color.Green, Color.White)));
  }

  @Override
  public Color[] players() {
    return CPlayers;
  }

  @Override
  public String getInfo() {
    return zugAktion.getCurrentAction();
  }

  @Override
  public int frogsInBag() {
    return SpielBeutel.getAnzFrösche();
  }

  @Override
  public List<Color> getFrogsInHand(Color color) {
    for (Spieler EinSpieler : AlleSpieler) {
      if (EinSpieler.getSpielerFarbe() == color) {
        System.out.println("Spieler hat " + EinSpieler.getInventar().size() + " Frösche ");
        return EinSpieler.getInventar().stream().map(Froschstein::getFroschsteinFarbe).toList();
      }
    }
    return new ArrayList<>(List.of(new Color[] {Color.Blue, Color.Green}));
  }

  @Override
  public Set<Position> getBoard() {
    return Board;
  }

  @Override
  public void clicked(Position position) {

    if (zugAktion.getCurrentAction().equals("Nachziehen")) {
      return;
    }

    zugAktion.startNextAction(Board, ausgewählterFrosch, position);

    Board.add(new Position(ausgewählterFrosch, position.x(), position.y(), position.border()));
    ausgewählterFrosch = null;
  }

  @Override
  public void selectedFrogInHand(Color SpielerFarbe, Color FroschFarbe) {
    for (Spieler EinSpieler : AlleSpieler) {
      if (EinSpieler.getSpielerFarbe() == SpielerFarbe) {
        ausgewählterFrosch = FroschFarbe;
        for (Froschstein froschstein : EinSpieler.getInventar()) {
          if (froschstein.getFroschsteinFarbe() == FroschFarbe) {
            EinSpieler.getInventar().remove(froschstein);
            break;
          }
        }
      }
    }
  }

  @Override
  public Color winner() {
    return Color.None;
  }

  @Override
  public boolean save(String s) {
    return true;
  }

  @Override
  public boolean load(String s) {
    return true;
  }

  public boolean startGame(int spieler, List<Color> SpielerFarben) {
    System.out.println("Spiel ist gestartet");
    AlleSpieler = new Spieler[spieler];
    Reihenfolge = new Spieler[spieler];
    iSpieler = spieler;
    if (spieler < SpielerFarben.size()) {
      SpielerFarben = SpielerFarben.subList(0, spieler);
    }

    if (!checkPlayerCount(spieler)) {
      GameIsRunning = false;
      System.out.println("Spiel kann nicht gestartet werden. Zu viele oder zu wenige Spieler");
      return GameIsRunning;
    }
    CPlayers = new Color[spieler];
    int j = 0;
    for (Color EinzSpielerFarbe : SpielerFarben) {
      AlleSpieler[j] = new Spieler(EinzSpielerFarbe);
      CPlayers[j] = EinzSpielerFarbe;
      j++;
    }

    beutelBefüllen(SpielerFarben);

    reihenfolgeBestimmen(AlleSpieler);
    erstesFröscheNehmen(2);

    System.out.println("Spiel ist gestartet");
    GameIsRunning = true;
    zugAktion.startTurn();
    return GameIsRunning;
  }

  public void erstesFröscheNehmen(int AnzahlFrösche) {
    for (Spieler EinSpieler : AlleSpieler) {
      for (int i = 0; i < AnzahlFrösche; i++) {
        SpielBeutel.froschNehmen(EinSpieler);
      }
    }
    System.out.println("Die ersten Frösche wurden gezogen");
  }

  public void takeFrogFromBag(Spieler EinSpieler) {
    SpielBeutel.froschNehmen(EinSpieler);
  }

  private boolean checkPlayerCount(int iAnzSpieler) {
    return iAnzSpieler <= 4 && iAnzSpieler >= 2;
  }

  private void beutelBefüllen(List<Color> FarbenInBeutel) {
    SpielBeutel = new Beutel(FarbenInBeutel);
  }

  public int getPlayerCount() {
    return iSpieler;
  }

  public boolean isGameIsRunning() {
    return GameIsRunning;
  }

  public Beutel getSpielBeutel() {
    return SpielBeutel;
  }

  public void reihenfolgeBestimmen(Spieler[] AlleSpieler) {
    int Index = new Random().nextInt(AlleSpieler.length);
    Spieler ersterSpieler = AlleSpieler[Index];
    Reihenfolge[0] = ersterSpieler;
    Reihenfolge[0].setStartspieler(true);
    Reihenfolge[0].setZugPosition(1);
    int i = 1;
    for (Spieler spieler : AlleSpieler) {
      if (spieler == ersterSpieler) {
        continue;
      }
      Reihenfolge[i] = spieler;
      Reihenfolge[i].setZugPosition(i + 1);
      i++;
    }
  }

  public void zugBeenden(Spieler SpielerBeendet) {
    LastPlayer = currentPlayer;
  }

  public boolean zugStarten(Spieler SpielerStarten) {
    if (SpielerStarten.getZugPosition() == LastPlayer + 1 ||
        ((LastPlayer == iSpieler) && (SpielerStarten.getZugPosition() == 1))) {
      currentPlayer = SpielerStarten.getZugPosition();
      return true;
    } else {
      return false;
    }
  }

  public Spieler getStartSpieler() {
    return Reihenfolge[0];
  }

  public Spieler[] getReihenfolge() {
    return Reihenfolge;
  }

  public int getLastPlayer() {
    return LastPlayer;
  }

  public void setCurrentPlayer(int currentPlayer) {
    this.currentPlayer = currentPlayer;
  }

  public Spieler[] getAlleSpieler() {
    return AlleSpieler;
  }
}
