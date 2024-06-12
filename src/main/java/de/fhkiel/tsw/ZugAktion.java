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

  public ZugAktion() {
    actionsPlayed = new HashMap<>();
    actionsPlayed.put("Bewegen", false);
    actionsPlayed.put("Anlegen", false);
    actionsPlayed.put("Nachziehen", false);
    actionOrder = new String[] {"Bewegen", "Anlegen", "Nachziehen"};
    currentActionIndex = 0;
  }

  public void startTurn() {
    turnStarted = true;
    for (String action : actionOrder) {
      actionsPlayed.put(action, false);
    }
    currentActionIndex = 0;
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

  public void playAction(String action, Set<Position> board, Color frog, Position position) {
    if (actionsPlayed.containsKey(action) && action.equals(actionOrder[currentActionIndex])) {
      actionsPlayed.put(action, true);
      executeAction(action, board, frog, position);
      currentActionIndex++;
    } else {
      throw new IllegalStateException(
          "Aktion " + action + " ist entweder ungültig oder in falscher Reihenfolge");
    }
  }

  public void executeAction(String action) {
    switch (action) {
      case "Bewegen":
        moveFrog();
        break;
      case "Nachziehen":
        drawFrog();
        break;
    }
    // Logik zum Ausführen der Aktion
  }

  public void executeAction(String action, Set<Position> board, Color frog, Position position) {
    switch (action) {
      case "Bewegen":
        moveFrog();
        break;
      case "Anlegen":
        placeFrog(board, frog, position);
        break;
      case "Nachziehen":
        drawFrog();
        break;
    }
    // Logik zum Ausführen der Aktion
  }

  private void drawFrog() {
  }

  private void placeFrog(Set<Position> board, Color frog, Position position) {
    board.add(new Position(frog, position.x(), position.y(), position.border()));
    frog = null;
  }

  private void moveFrog() {

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

  public boolean startNextAction(Set<Position> board, Color frog, Position position) {
    // Logik zum Starten der nächsten Aktion
    playAction(actionOrder[currentActionIndex], board, frog, position);
    return true;
  }

  public boolean startNextAction() {
    // Logik zum Starten der nächsten Aktion
    playAction(actionOrder[currentActionIndex]);
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
}
