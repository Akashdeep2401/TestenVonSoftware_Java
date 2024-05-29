package de.fhkiel.tsw;

import java.util.HashMap;
import java.util.Map;

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
      currentActionIndex++;
    } else {
      throw new IllegalStateException(
          "Aktion " + action + " ist entweder ungültig oder in falscher Reihenfolge");
    }
  }

  public void executeAction(String action) {
    // Logik zum Ausführen der Aktion
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

  public boolean startNextAction() {
    // Logik zum Starten der nächsten Aktion
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

}
