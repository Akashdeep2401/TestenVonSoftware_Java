package de.fhkiel.tsw;

import de.fhkiel.tsw.armyoffrogs.Color;
import de.fhkiel.tsw.armyoffrogs.Position;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for handling player actions.
 */
public class ZugAktion {
  Logger logger = Logger.getLogger(ZugAktion.class.getName());

  private final Map<String, Boolean> actionsPlayed;
  private final String[] actionOrder;
  private Color ausgewaehlterHandFrosch;
  private int currentActionIndex;
  private int lastPlayer;
  private int currentPlayer;

  private int anzahlSpieler;
  private Gamelogic gamelogic;
  private boolean bewegenIstFertig = false;
  private static final String BEWEGEN = "Bewegen";
  private static final String ANLEGEN = "Anlegen";
  private static final String NACHZIEHEN = "Nachziehen";
  private static final String AKTION = "Aktion ";

  public Color getAusgewaehlterHandFrosch() {
    return ausgewaehlterHandFrosch;
  }

  public void setAusgewaehlterHandFrosch(Color ausgewaehlterHandFrosch) {
    this.ausgewaehlterHandFrosch = ausgewaehlterHandFrosch;
  }
  /**
   * Constructor for ZugAktion.
   */

  public ZugAktion(Gamelogic gamelogic) {
    actionsPlayed = new HashMap<>();
    actionsPlayed.put(BEWEGEN, false);
    actionsPlayed.put(ANLEGEN, false);
    actionsPlayed.put(NACHZIEHEN, false);
    actionOrder = new String[] {BEWEGEN, ANLEGEN, NACHZIEHEN};
    currentActionIndex = 0;
    this.gamelogic = gamelogic;
  }

  /**
   * Constructor for ZugAktion.
   */
  public ZugAktion() {
    actionsPlayed = new HashMap<>();
    actionsPlayed.put(BEWEGEN, false);
    actionsPlayed.put(ANLEGEN, false);
    actionsPlayed.put(NACHZIEHEN, false);
    actionOrder = new String[] {BEWEGEN, ANLEGEN, NACHZIEHEN};
    currentActionIndex = 0;
    currentPlayer = 0;
  }

  /**
   * Play the action "Nachziehen".
   * This method is used to play the action "Nachziehen".
   *
   * @param action .
   *
   */
  public void playNachziehen(String action) {
    if (actionsPlayed.containsKey(action) && action.equals(actionOrder[currentActionIndex])) {
      executeNachziehen();
      actionsPlayed.put(action, true);
      if (logger.isLoggable(Level.INFO)) {
        logger.info(String.format("Aktion %s wurde gespielt", action));
      }
      zugBeenden(gamelogic.getReihenfolge()[currentPlayer - 1]);
      gamelogic.infoString = "Nachziehen wurde gespielt";
      zugStarten(gamelogic.getReihenfolge()[currentPlayer == anzahlSpieler ? 0 : currentPlayer]);
    } else {
      throw new IllegalStateException(
          AKTION + action + " ist entweder ungültig oder in falscher Reihenfolge");
    }
  }

  /**
   * Play the action.
   *
   * @param action .
   * @param board .
   * @param position .
   */
  public void playAction(String action, Spielfeld board, Position position) {
    if (actionsPlayed.containsKey(action) && action.equals(actionOrder[currentActionIndex])) {
      boolean actionSuccess = executeAction(action, board, position);
      if (actionSuccess) {
        actionsPlayed.put(action, true);
        if (logger.isLoggable(Level.INFO)) {
          logger.info(String.format("Aktion %s wurde gespielt", action));
        }
        currentActionIndex++;
        if (action.equals(ANLEGEN)) {
          playNachziehen(NACHZIEHEN);
        } else {
          skipAction();
        }
      }
    } else {
      throw new IllegalStateException(
          AKTION + action + " ist entweder ungültig oder in falscher Reihenfolge");
    }
  }

  public void executeNachziehen() {
    drawFrog();
  }

  /**
   * Execute the action.
   *
   * @param action .
   * @param board .
   * @param position .
   * @return .
   *
   */
  public boolean executeAction(String action, Spielfeld board, Position position) {
    switch (action) {
      case BEWEGEN:
        moveFrog(board, position);
        logger.info("Froschstein wurde bewegt");
        gamelogic.infoString = "Froschstein wurde bewegt";
        return bewegenIstFertig;
      case ANLEGEN:
        if (ausgewaehlterHandFrosch == null) {
          logger.info("Kein Froschstein ausgewählt");
          gamelogic.infoString = "Kein Froschstein ausgewählt";
          return false;
        }
        boolean placeSuccess = placeFrog(board, ausgewaehlterHandFrosch, position);
        if (placeSuccess) {
          ausgewaehlterHandFrosch = null;
          logger.info("Froschstein wurde angelegt");
          gamelogic.infoString = "Froschstein wurde angelegt";
        }
        return placeSuccess;
      case NACHZIEHEN:
        drawFrog();
        break;
      default:
        return false;
    }
    return false;
    // Logik zum Ausführen der Aktion
  }

  private void drawFrog() {
    gamelogic.getSpielBeutel().froschNehmen(gamelogic.getReihenfolge()[currentPlayer - 1]);
  }

  private boolean placeFrog(Spielfeld board, Color frog, Position position) {
    return board.froschSetzen(new Position(frog, position.x(), position.y(), position.border()));
  }

  private void moveFrog(Spielfeld board, Position position) {
    if (board.isFrogSelected()) {
      if (board.froschBewegen(position)) {
        logger.info("Froschstein an Position X:" + position.x() + " Y:" + position.y() + " bewegt");
        gamelogic.infoString +=
            "Froschstein an Position X:" + position.x() + " Y:" + position.y() + " bewegt";
        if (!board.isFrogSelected()) {
          bewegenIstFertig = true;
        }
      }
    } else if (
        gamelogic.getReihenfolge()[getCurrentPlayer() - 1].getSpielerFarbe() == position.frog()
            &&
            board.isFrogMovable(
                position)) { // Ob der Spieler den Froschstein fürs Bewegen auswählen darf
      bewegenIstFertig = false;
      board.selectFrog(position);
    }

  }

  public boolean isFrogMoved() {
    // Prüft, ob ein Froschstein bewegt wurde
    return true;
  }

  /**
   * Check if a frog can be moved.
   *
   * @return .
   *
   */
  public boolean canMoveFrog() {
    // Prüft, ob ein Froschstein bewegt werden kann
    if (gamelogic.getBoard().size() < 2) {
      return false;
    }

    for (Position frog : gamelogic.getBoard()) {
      if (frog.frog() == gamelogic.getReihenfolge()[currentPlayer - 1].getSpielerFarbe()) {
        return true;
      }
    }
    return false;
  }

  public void attemptAction() {
    // Logik zum Versuchen, eine Aktion zu starten
  }

  /**
   * Start the next action.
   *
   * @param board .
   * @param position .
   * @return .
   *
   */
  public boolean startNextAction(Spielfeld board, Position position) {
    // Logik zum Starten der nächsten Aktion
    playAction(actionOrder[currentActionIndex], board, position);
    return true;
  }

  /**
   * Start the next action.
   *
   *@return .
   *
   */
  public boolean startNextAction() {
    // Logik zum Starten der nächsten Aktion
    playNachziehen(actionOrder[currentActionIndex]);
    gamelogic.infoString = AKTION + actionOrder[currentActionIndex] + " wurde gestartet";
    return true;
  }


  public boolean isActionPlayed(String action) {
    return actionsPlayed.getOrDefault(action, false);
  }

  /**
   * Check the current turn.
   *
   */
  public void checkCurrentTurn() {
    // Ensure that actions are in the correct order
    for (int i = 0; i < currentActionIndex; i++) {
      if (Boolean.FALSE.equals(actionsPlayed.get(actionOrder[i]))) {
        throw new IllegalStateException(
            AKTION + actionOrder[i] + " wurde nicht korrekt gespielt");
      }
    }
  }

  public String getCurrentAction() {
    return actionOrder[currentActionIndex];
  }

  /**
   * Set the current action.
   *
   * @param action .
   *
   */
  public void setCurrentAction(String action) {
    for (int i = 0; i < actionOrder.length; i++) {
      if (actionOrder[i].equals(action)) {
        currentActionIndex = i;
        break;
      }
    }
    // for every action in actionOrder,
    // set every action to true until the specific action is reached
    for (String actionInOrder : actionOrder) {
      if (actionInOrder.equals(action)) {
        break;
      }
      actionsPlayed.put(actionInOrder, true);
    }
  }

  /**
   * start the turn.
   *
   * @param spielerStarten .
   *
   * @return .
   *
   */
  public boolean zugStarten(Spieler spielerStarten) {
    if (spielerStarten.getZugPosition() == lastPlayer + 1
        || ((lastPlayer == anzahlSpieler) && (spielerStarten.getZugPosition() == 1))) {
      currentPlayer = spielerStarten.getZugPosition();
      for (String action : actionOrder) {
        actionsPlayed.put(action, false);
      }
      currentActionIndex = 0;
      if (logger.isLoggable(Level.INFO)) {
        logger.info(String.format("Spieler %d/%s ist am Zug", currentPlayer,
            spielerStarten.getSpielerFarbe()));
      }
      skipAction();
      return true;
    } else {
      return false;
    }
  }

  /**
   * End the turn.
   *
   * @param spielerBeendet .
   *
   * @return .
   */
  public boolean zugBeenden(Spieler spielerBeendet) {
    if (spielerBeendet.getZugPosition() == currentPlayer) {
      lastPlayer = currentPlayer;
      return true;
    }
    return false;
  }

  /**
   * Set the next player.
   *
   * @param color .
   *
   */
  public void setNextPlayer(Color color) {
    for (int i = 0; i < gamelogic.getReihenfolge().length; i++) {
      if (gamelogic.getReihenfolge()[i].getSpielerFarbe() == color) {
        currentPlayer = i + 1;
        if (i == 0) {
          lastPlayer = anzahlSpieler;
        } else {
          lastPlayer = i;
        }
      }
    }
  }

  public int getCurrentPlayer() {
    return currentPlayer;
  }

  public void setCurrentPlayer(int currentPlayer) {
    this.currentPlayer = currentPlayer;
  }

  public void setAnzahlSpieler(int anzahlSpieler) {
    this.anzahlSpieler = anzahlSpieler;
  }

  private void skipAction() {
    if (!actionIsPossible(actionOrder[currentActionIndex])) {
      if (logger.isLoggable(Level.INFO)) {
        logger.info(
            String.format("%s%s wurde uebersprungen", AKTION, actionOrder[currentActionIndex]));
        actionsPlayed.put(actionOrder[currentActionIndex], true);
      }
      currentActionIndex++;
      if (actionOrder[currentActionIndex].equals(NACHZIEHEN)) {
        playNachziehen(actionOrder[currentActionIndex]);
      } else {
        skipAction();
      }
    } else {
      if (logger.isLoggable(Level.INFO)) {
        logger.info(AKTION + actionOrder[currentActionIndex] + " kann ausgeführt werden");
      }
    }
  }

  private boolean actionIsPossible(String action) {
    return switch (action) {
      case BEWEGEN -> canMoveFrog();
      case ANLEGEN -> canPlaceFrog();
      default -> {
        logger.info("Fehler aufgetreten");
        yield false;
      }
    };
  }

  private boolean canPlaceFrog() {
    return gamelogic.getReihenfolge()[currentPlayer - 1].getInventorySize() > 0;
  }

}
