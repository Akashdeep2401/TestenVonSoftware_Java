package de.fhkiel.tsw;

import de.fhkiel.tsw.armyoffrogs.Color;
import de.fhkiel.tsw.armyoffrogs.Position;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ZugAktion {

  private boolean turnStarted;
  private Map<String, Boolean> actionsPlayed;
  private String[] actionOrder;
  private int currentActionIndex;
  private int LastPlayer;

  private int currentPlayer;

  private int anzahlSpieler;
  private Gamelogic gamelogic;
  private boolean bewegenIstFertig = false;

  public ZugAktion(Gamelogic gamelogic) {
    actionsPlayed = new HashMap<>();
    actionsPlayed.put("Bewegen", false);
    actionsPlayed.put("Anlegen", false);
    actionsPlayed.put("Nachziehen", false);
    actionOrder = new String[] {"Bewegen", "Anlegen", "Nachziehen"};
    currentActionIndex = 0;
    this.gamelogic = gamelogic;
  }

  public ZugAktion() {
    actionsPlayed = new HashMap<>();
    actionsPlayed.put("Bewegen", false);
    actionsPlayed.put("Anlegen", false);
    actionsPlayed.put("Nachziehen", false);
    actionOrder = new String[] {"Bewegen", "Anlegen", "Nachziehen"};
    currentActionIndex = 0;
    currentPlayer = 0;
  }

  public void playAction(String action) {
    if (actionsPlayed.containsKey(action) && action.equals(actionOrder[currentActionIndex])) {
      actionsPlayed.put(action, true);
      executeAction(action);
      currentActionIndex++;
    } else {
      throw new IllegalStateException(
          "Aktion " + action + " ist entweder ungültig oder in falscher Reihenfolge");
    }
  }

  public void playAction(String action, Spielfeld board, Color frog, Position position) {
    if (actionsPlayed.containsKey(action) && action.equals(actionOrder[currentActionIndex])) {
      actionsPlayed.put(action, true);
      executeAction(action, board, frog, position);
      if (action.equals("Bewegen")) {
        if (!bewegenIstFertig) {
          return;
        }
      }
      currentActionIndex++;
    } else {
      throw new IllegalStateException(
          "Aktion " + action + " ist entweder ungültig oder in falscher Reihenfolge");
    }
  }

  public void executeAction(String action) {
    switch (action) {
      case "Bewegen":
        //moveFrog();
        break;
      case "Nachziehen":
        drawFrog();
        break;
    }
    // Logik zum Ausführen der Aktion
  }

  public void executeAction(String action, Spielfeld board, Color frog, Position position) {
    switch (action) {
      case "Bewegen":
        moveFrog(board, position);
        gamelogic.infoString = "Froschstein wurde bewegt";
        break;
      case "Anlegen":
        placeFrog(board, frog, position);
        gamelogic.infoString = "Froschstein wurde angelegt";
        break;
      case "Nachziehen":
        drawFrog();
        break;
    }
    // Logik zum Ausführen der Aktion
  }

  private void drawFrog() {
  }

  private void placeFrog(Spielfeld board, Color frog, Position position) {
    board.froschSetzen(new Position(frog, position.x(), position.y(), position.border()));
    frog = null;
  }

  private void moveFrog(Spielfeld board, Position position) {
    if (board.isFrogSelected()) {
      if (board.froschBewegen(position)) {
        gamelogic.infoString += "Froschstein an Position X:" + position.x() + " Y:" + position.y() + " bewegt";
        if (!board.isFrogSelected()) {
          bewegenIstFertig = true;
        }
      }
    } else if(gamelogic.getReihenfolge()[getCurrentPlayer()].getSpielerFarbe() == position.frog() && board.isFrogMovable(position)){ // Ob der Spieler den Froschstein fürs Bewegen auswählen darf
      bewegenIstFertig = false;
      board.selectFrog(position);
    }

  }

  public boolean isFrogMoved() {
    // Prüft, ob ein Froschstein bewegt wurde
    return true;
  }

  public boolean canMoveFrog() {
    // Prüft, ob ein Froschstein bewegt werden kann
    return true;
  }

  public void attemptAction() {
    // Logik zum Versuchen, eine Aktion zu starten
  }

  public boolean startNextAction(Spielfeld board, Color frog, Position position) {
    // Logik zum Starten der nächsten Aktion
    playAction(actionOrder[currentActionIndex], board, frog, position);
    return true;
  }

  public boolean startNextAction() {
    // Logik zum Starten der nächsten Aktion
    playAction(actionOrder[currentActionIndex]);
    gamelogic.infoString = "Aktion " + actionOrder[currentActionIndex] + " wurde gestartet";
    return true;
  }



  public boolean isActionPlayed(String action) {
    return actionsPlayed.getOrDefault(action, false);
  }

  public void checkCurrentTurn() {
    // Ensure that actions are in the correct order
    for (int i = 0; i < currentActionIndex; i++) {
      if (!actionsPlayed.get(actionOrder[i])) {
        throw new IllegalStateException(
            "Aktion " + actionOrder[i] + " wurde nicht korrekt gespielt");
      }
    }
  }

  public String getCurrentAction() {
    return actionOrder[currentActionIndex];
  }

  public void setCurrentAction(String action) {
    for (int i = 0; i < actionOrder.length; i++) {
      if (actionOrder[i].equals(action)) {
        currentActionIndex = i;
        break;
      }
    }
    // for every action in actionOrder, set every action to true until the specific action is reached
    for (String actionInOrder : actionOrder) {
      if (actionInOrder.equals(action)) {
        break;
      }
      actionsPlayed.put(actionInOrder, true);
    }
  }

  public boolean zugStarten(Spieler SpielerStarten) {
    if (SpielerStarten.getZugPosition() == LastPlayer + 1 ||
            ((LastPlayer == anzahlSpieler) && (SpielerStarten.getZugPosition() == 1))) {
      currentPlayer = SpielerStarten.getZugPosition();
      turnStarted = true;
      for (String action : actionOrder) {
        actionsPlayed.put(action, false);
      }
      currentActionIndex = 0;
      return true;
    } else {
      return false;
    }
  }

  public boolean zugBeenden(Spieler SpielerBeendet) {
    if (SpielerBeendet.getZugPosition() == currentPlayer) {
      LastPlayer = currentPlayer;
      return true;
    }
    return false;
  }

  public void setNextPlayer(Color color) {
    for (int i = 0; i < gamelogic.getReihenfolge().length; i++) {
      if (gamelogic.getReihenfolge()[i].getSpielerFarbe() == color) {
        currentPlayer = i + 1;
        if (i == 0) {
          LastPlayer = anzahlSpieler;
        } else {
          LastPlayer = i;
        }
      }
    }
  }

  public void setCurrentPlayer(int currentPlayer) {
    this.currentPlayer = currentPlayer;
  }

  public int getCurrentPlayer() {
    return currentPlayer;
  }

  public void setAnzahlSpieler(int anzahlSpieler) {
    this.anzahlSpieler = anzahlSpieler;
  }
}
