package de.fhkiel.tsw;

import de.fhkiel.tsw.armyoffrogs.Color;
import de.fhkiel.tsw.armyoffrogs.Game;
import de.fhkiel.tsw.armyoffrogs.Position;

import java.util.*;

public class Gamelogic implements Game {


  public boolean keineKetten;
  private Beutel SpielBeutel;
  private int iSpieler;

  private boolean GameIsRunning;

  private Color[] CPlayers;
  private Spieler[] AlleSpieler;

  private Spieler[] Reihenfolge;

  int LastPlayer;

  private int currentPlayer;


  //private Set<Position> frogBoard = new HashSet<>();
  private Spielfeld frogBoard = new Spielfeld();
  private Color ausgewählterFrosch;
  public ZugAktion zugAktion;
  public String infoString;

  public Gamelogic() {
    GameIsRunning = false;
    iSpieler = 0;
    LastPlayer = 0;
    currentPlayer = 0;
    SpielBeutel = new Beutel(new ArrayList<>());
    CPlayers = new Color[0];
    zugAktion = new ZugAktion(this);
  }

  public Gamelogic(Set<Position> newBoard) {
    GameIsRunning = false;
    iSpieler = 0;
    LastPlayer = 0;
    currentPlayer = 0;
    SpielBeutel = new Beutel(new ArrayList<>());
    CPlayers = new Color[0];
    zugAktion = new ZugAktion(this);
    frogBoard = new Spielfeld(newBoard, Gamelogic.this);
  }

  private void clearPreviousGame() {
    GameIsRunning = false;
    iSpieler = 0;
    LastPlayer = 0;
    currentPlayer = 0;
    SpielBeutel = new Beutel(new ArrayList<>());
    CPlayers = new Color[0];
    zugAktion = new ZugAktion(this);
    frogBoard = new Spielfeld();
  }

  @Override
  public boolean newGame(int i) {
    clearPreviousGame();
    return startGame(i,
        new ArrayList<>(Arrays.asList(Color.Red, Color.Blue, Color.Green, Color.White)));
  }



  @Override
  public Color[] players() {
    return CPlayers;
  }

  @Override
  public String getInfo() {
    return infoString;
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
    return frogBoard.getFroschfeld();
  }

  @Override
  public void clicked(Position position) {

    if (zugAktion.getCurrentAction().equals("Nachziehen")) {
      infoString += "Du kannst keine Frösche setzen, wenn du ziehst";
      return;
    }

    zugAktion.startNextAction(frogBoard, ausgewählterFrosch, position);

    //frogBoard.froschSetzen(new Position(ausgewählterFrosch, position.x(), position.y(), position.border()));
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
    zugAktion.zugStarten(Reihenfolge[0]);
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

  public int getCurrentPlayer() {
    return currentPlayer;
  }

  public Spieler[] getAlleSpieler() {
    return AlleSpieler;
  }
  public Spielfeld getFrogBoard() {
    return frogBoard;
  }

  public boolean hasNoChains() {
    return frogBoard.keineKetten;
  }
  public Position getWrongPlacement() {
    Position wrongPlacement = frogBoard.getChainPlacement();
    if (wrongPlacement == null) {
      System.out.println("Keine falsche Position gefunden");
    }
    return frogBoard.getChainPlacement();
  }
}
