package de.fhkiel.tsw;

import de.fhkiel.tsw.armyoffrogs.Color;
import de.fhkiel.tsw.armyoffrogs.Game;
import de.fhkiel.tsw.armyoffrogs.Position;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;

/**
 * The class Gamelogic represents the game logic of the game.
 */
public class Gamelogic implements Game {
  private final Random newRandom = new Random();
  Logger logger = Logger.getLogger(Gamelogic.class.getName());



  public ZugAktion zugAktion;
  public String infoString;
  int lastPlayer;
  private Beutel spielBeutel;
  private int intSpieler;
  private boolean gameIsRunning;
  private Color[] colorPlayers;
  private Spieler[] alleSpieler;
  private Spieler[] reihenfolge;
  private Spielfeld frogBoard;

  /**
   * Constructor for the class Gamelogic.
   */
  public Gamelogic() {
    gameIsRunning = false;
    intSpieler = 0;
    lastPlayer = 0;
    spielBeutel = new Beutel(new ArrayList<>());
    colorPlayers = new Color[0];
    zugAktion = new ZugAktion(this);
    frogBoard = new Spielfeld(this);
    infoString = "Das Spiel wurde geladen";
  }

  /**
   * Constructor for the class Gamelogic.
   *
   * @param newBoard .
   */

  public Gamelogic(Set<Position> newBoard) {
    gameIsRunning = false;
    intSpieler = 0;
    lastPlayer = 0;
    spielBeutel = new Beutel(new ArrayList<>());
    colorPlayers = new Color[0];
    zugAktion = new ZugAktion(this);
    frogBoard = new Spielfeld(newBoard, this);
    infoString = "Das Spiel wurde geladen";
  }

  private void clearPreviousGame() {
    gameIsRunning = false;
    intSpieler = 0;
    lastPlayer = 0;
    spielBeutel = new Beutel(new ArrayList<>());
    colorPlayers = new Color[0];
    zugAktion = new ZugAktion(this);
    frogBoard = new Spielfeld(this);
  }

  @Override
  public boolean newGame(int i) {
    clearPreviousGame();
    return startGame(i,
        new ArrayList<>(Arrays.asList(Color.Red, Color.Blue, Color.Green, Color.White)));
  }


  @Override
  public Color[] players() {
    return colorPlayers;
  }

  @Override
  public String getInfo() {
    return infoString;
  }

  @Override
  public int frogsInBag() {
    return spielBeutel.getAnzFroesche();
  }

  @Override
  public List<Color> getFrogsInHand(Color color) {
    for (Spieler einSpieler : alleSpieler) {
      if (einSpieler.getSpielerFarbe() == color) {
        return einSpieler.getInventar().stream().map(Froschstein::getFroschsteinFarbe)
            .toList();
      }
    }
    return new ArrayList<>(List.of(Color.Blue, Color.Green));
  }

  @Override
  public Set<Position> getBoard() {
    return frogBoard.getFroschfeld();
  }

  @Override
  public void clicked(Position position) {

    if (zugAktion.getCurrentAction().equals("Nachziehen")) {
      logger.info("Du kannst keine Frösche setzen, wenn du ziehst");
      infoString += "Du kannst keine Frösche setzen, wenn du ziehst";
      return;
    }

    if (zugAktion.getAusgewaehlterHandFrosch() == null
        && zugAktion.getCurrentAction().equals("Anlegen")) {
      logger.info("Kein Froschstein ausgewählt");
      infoString = "Kein Froschstein ausgewählt";
      return;
    }
    zugAktion.startNextAction(frogBoard, position);
  }

  @Override
  public void selectedFrogInHand(Color spielerFarbe, Color froschFarbe) {
    infoString = "Spieler nicht gefunden";
    for (Spieler einSpieler : alleSpieler) {
      if (einSpieler.getZugPosition() == getCurrentPlayer()
          && einSpieler.getSpielerFarbe() == spielerFarbe) {
        for (Froschstein froschstein : einSpieler.getInventar()) {
          if (froschstein.getFroschsteinFarbe() == froschFarbe) {
            einSpieler.getInventar().remove(froschstein);
            zugAktion.setAusgewaehlterHandFrosch(froschFarbe);
            infoString = "Froschstein ausgewählt";
            return;
          }
        }
        infoString = "Froschstein nicht im Inventar gefunden";
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

  /**
   * Method to start the game.
   *
   * @param spieler .
   *
   * @param spielerFarben .
   *
   * @return .
   *
   */
  public boolean startGame(int spieler, List<Color> spielerFarben) {
    alleSpieler = new Spieler[spieler];
    reihenfolge = new Spieler[spieler];
    intSpieler = spieler;
    if (spieler < spielerFarben.size()) {
      spielerFarben = spielerFarben.subList(0, spieler);
    }

    if (!checkPlayerCount(spieler)) {
      gameIsRunning = false;
      logger.info("Spiel kann nicht gestartet werden. Zu viele oder zu wenige Spieler");
      return gameIsRunning;
    }
    colorPlayers = new Color[spieler];
    int j = 0;
    for (Color einzSpielerFarbe : spielerFarben) {
      alleSpieler[j] = new Spieler(einzSpielerFarbe);
      colorPlayers[j] = einzSpielerFarbe;
      j++;
    }

    beutelBefuellen(spielerFarben);

    reihenfolgeBestimmen(alleSpieler);
    erstesFroescheNehmen(2);

    logger.info("Spiel ist gestartet");
    gameIsRunning = true;
    zugAktion.setAnzahlSpieler(intSpieler);
    zugAktion.zugStarten(reihenfolge[0]);
    logger.info("Der erste Spieler ist " + reihenfolge[0].getSpielerFarbe());
    return gameIsRunning;
  }

  /**
   * Method to take the first frogs.
   *
   * @param anzahlFroesche .
   *
   */
  public void erstesFroescheNehmen(int anzahlFroesche) {
    for (Spieler einSpieler : alleSpieler) {
      for (int i = 0; i < anzahlFroesche; i++) {
        spielBeutel.froschNehmen(einSpieler);
      }
    }
    logger.info("Die ersten Frösche wurden gezogen");
  }

  public void takeFrogFromBag(Spieler einSpieler) {
    spielBeutel.froschNehmen(einSpieler);
  }

  private boolean checkPlayerCount(int intAnzSpieler) {
    return intAnzSpieler <= 4 && intAnzSpieler >= 2;
  }

  public void beutelBefuellen(List<Color> farbenInBeutel) {
    spielBeutel = new Beutel(farbenInBeutel);
  }

  public int getPlayerCount() {
    return intSpieler;
  }

  public boolean isGameIsRunning() {
    return gameIsRunning;
  }

  public Beutel getSpielBeutel() {
    return spielBeutel;
  }

  /**
   * Method to determine the order of the players.
   *
   * @param alleSpieler .
   *
   */
  public void reihenfolgeBestimmen(Spieler[] alleSpieler) {
    int index =  newRandom.nextInt(alleSpieler.length);
    Spieler ersterSpieler = alleSpieler[index];
    reihenfolge[0] = ersterSpieler;
    reihenfolge[0].setStartspieler(true);
    reihenfolge[0].setZugPosition(1);
    int i = 1;
    for (Spieler spieler : alleSpieler) {
      if (spieler == ersterSpieler) {
        continue;
      }
      reihenfolge[i] = spieler;
      reihenfolge[i].setZugPosition(i + 1);
      i++;
    }
  }

  public Spieler getStartSpieler() {
    return reihenfolge[0];
  }

  public Spieler[] getReihenfolge() {
    return reihenfolge;
  }

  public int getLastPlayer() {
    return lastPlayer;
  }


  public int getCurrentPlayer() {
    return zugAktion.getCurrentPlayer();
  }

  public Spieler[] getAlleSpieler() {
    return alleSpieler;
  }

  public Spielfeld getFrogBoard() {
    return frogBoard;
  }

  public boolean hasNoChains() {
    return frogBoard.getKeineKetten();
  }

  /**
   * Method to get the wrong placement.
   *
   * @return .
   *
   */
  public Position getWrongPlacement() {
    Position wrongPlacement = frogBoard.getChainPlacement();
    if (wrongPlacement == null) {
      logger.info("Keine falsche Position gefunden");
    }
    return wrongPlacement;
  }
}
