package steps;

import de.fhkiel.tsw.Froschstein;
import de.fhkiel.tsw.Gamelogic;
import de.fhkiel.tsw.Spieler;
import de.fhkiel.tsw.ZugAktion;
import de.fhkiel.tsw.armyoffrogs.Color;
import de.fhkiel.tsw.armyoffrogs.Position;
import io.cucumber.java.de.Dann;
import io.cucumber.java.de.Und;
import io.cucumber.java.de.Wenn;
import io.cucumber.java.de.Angenommen;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import steps.container.LogicContainer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ZugSteps {

  private final LogicContainer container;

  private final ZugAktion zugAktion = new ZugAktion();
  private int LetzterSpieler;
  private Spieler currentPlayer;
  public ZugSteps(LogicContainer container) {
    this.container = container;

  }

  @Wenn("Spieler {int} seinen Zug beendet hat")
  public void spieler_spieler_seinen_zug_beendet_hat(int SpielerDran) {
    container.logicUnderTest.zugAktion.setNextPlayer(
        container.logicUnderTest.getReihenfolge()[SpielerDran - 1].getSpielerFarbe());
    assertTrue(container.logicUnderTest.zugAktion.zugBeenden(
        container.logicUnderTest.getReihenfolge()[SpielerDran - 1]));
  }

  @Dann("ist Spieler {int} am Zug")
  public void istSpielerSpielerAmZug(int SpielerDran) {
    assertThat(container.logicUnderTest.zugAktion.zugStarten(
        container.logicUnderTest.getReihenfolge()[SpielerDran - 1])).isTrue();
  }

  @Dann("ist Spieler {int} nicht am Zug")
  public void ist_spieler_nicht_am_zug(int SpielerNichtDran) {
    assertThat(container.logicUnderTest.zugAktion.zugStarten(
        container.logicUnderTest.getReihenfolge()[SpielerNichtDran - 1])).isFalse();

  }


  @Angenommen("ein Zug wird gespielt")
  public void ein_zug_wird_gespielt() {
    container.logicUnderTest.zugAktion.zugStarten(
        container.logicUnderTest.getReihenfolge()[container.logicUnderTest.getCurrentPlayer() - 1]);
  }

  @Angenommen("die Aktion {string} ist nicht gespielt worden")
  public void die_aktion_ist_nicht_gespielt_worden(String action) {
    assertThat(zugAktion.isActionPlayed(action)).isFalse();
  }

  @Angenommen("das Spiel hat angefangen und es wurden 11 Züge gespielt")
  public void das_spiel_läuft() {
    Set<Position> testBoard = new HashSet<>();
    testBoard.add(new Position(Color.Red, 0, 3, Color.None));
    testBoard.add(new Position(Color.Red, 1, 2, Color.None));
    testBoard.add(new Position(Color.Red, 2, 1, Color.None));
    testBoard.add(new Position(Color.Green, 3, 0, Color.None));
    testBoard.add(new Position(Color.Blue, 0, 2, Color.None));
    testBoard.add(new Position(Color.Red, 1, 1, Color.None));
    testBoard.add(new Position(Color.Red, 2, 0, Color.None));
    testBoard.add(new Position(Color.Red, 3, 3, Color.None));
    testBoard.add(new Position(Color.Red, 0, 1, Color.None));
    testBoard.add(new Position(Color.White, 1, 0, Color.None));
    testBoard.add(new Position(Color.Red, 3, 2, Color.None));
    container.logicUnderTest = new Gamelogic(testBoard);

    container.TestColorsArrayList.addAll(Arrays.asList(container.TestColors));
    container.logicUnderTest.startGame(4, container.TestColorsArrayList);
  }

  @Angenommen("das Spiel hat angefangen und es wurde ein Zug gespielt")
  public void das_spiel_läuft_mit_einem_zug() {
    Set<Position> testBoard = new HashSet<>();
    testBoard.add(new Position(Color.Red, 0, 3, Color.None));
    container.logicUnderTest = new Gamelogic(testBoard);

    container.TestColorsArrayList.addAll(Arrays.asList(container.TestColors));
    container.logicUnderTest.startGame(4, container.TestColorsArrayList);
  }

  @Wenn("der Zug abgefragt wird")
  public void der_zug_abgefragt_wird() {
    zugAktion.checkCurrentTurn();
  }

  @Dann("ist die Aktion {string} nicht gespielt worden")
  public void ist_die_aktion_nicht_gespielt_worden(String action) {
    assertThat(container.logicUnderTest.zugAktion.isActionPlayed(action)).isFalse();
  }

  @Dann("die Aktion {string} nicht gespielt worden")
  public void die_aktion_nicht_gespielt_worden(String action) {
    assertThat(
        zugAktion.isActionPlayed(action)
    ).isFalse();

  }

  @Dann("hat kein Spieler die Aktion {string} gespielt")
  public void hatKeinSpielerDieAktionGespielt(String action) {
    assertThat(zugAktion.isActionPlayed(action)).isFalse();
  }

  @Angenommen("die Aktion {string} ist gespielt worden")
  public void die_aktion_ist_gespielt_worden(String action) {
    if (action.equals("Bewegen")) {
      container.logicUnderTest.clicked(new Position(Color.Green, 3, 0, Color.None));
      container.logicUnderTest.clicked(new Position(Color.None, 2, 2, Color.None));
      container.logicUnderTest.clicked(new Position(Color.Green, 2, 2, Color.None));
    } else if (action.equals("Anlegen")) {
      container.logicUnderTest.zugAktion.setAusgewaehlterHandFrosch(Color.Green);
      container.logicUnderTest.clicked(new Position(Color.Green, 3, 0, Color.None));
    }

    assertThat(container.logicUnderTest.zugAktion.isActionPlayed(action)).isTrue();
  }

  @Angenommen("eine Aktion versucht wurde auszuführen")
  public void eine_aktion_versucht_wurde_auszuführen() {
    zugAktion.attemptAction();  // Annahme, dass diese Methode in Ihrer ZugAktion Klasse existiert
  }

  @Dann("wird die nächste Aktion in der Spielreihenfolge ausgeführt")
  public void wird_die_nächste_aktion_in_der_spielreihenfolge_ausgeführt() {
    boolean isNextActionStarted =
        zugAktion.startNextAction(); // Annahme, dass diese Methode überprüft, ob die nächste Aktion gestartet wurde
    assertThat(isNextActionStarted).isTrue();
  }

  @Wenn("die Aktion {string} ausgeführt wird")
  public void die_aktion_wird_ausgeführt(String action) {
    container.logicUnderTest.zugAktion.setCurrentAction(action);
  }

  @Angenommen("der Froschstein wurde bewegt")
  public void der_froschstein_wurde_bewegt() {
    assertTrue(zugAktion.isFrogMoved());
  }

  @Dann("kann der Froschstein bewegt werden")
  public void kann_der_froschstein_bewegt_werden() {
    assertTrue(zugAktion.canMoveFrog());
  }

  @Dann("darf der Froschstein nicht an der selben Position wie am Anfang des Zuges sein")
  public void darfDerFroschsteinNichtAnDerSelbenPositionWieAmAnfangDesZugesSein() {

  }

  @Wenn("der Froschstein bewegt wird")
  public void derFroschsteinBewegtWird() {
    assertTrue(zugAktion.isFrogMoved());
  }

  @Wenn("der Spieler der am Zug ist hat mindestens einen Froschstein im Inventar")
  public void derSpielerDerAmZugIstHatMindestensEinenFroschsteinImInventar() {
    container.testFroschsteinInventar =
        container.logicUnderTest.getReihenfolge()[container.logicUnderTest.getCurrentPlayer()
            - 1].getInventar();
    int rotCounter = 0;
    for (Froschstein froschstein : container.testFroschsteinInventar) {
      if (froschstein.getFroschsteinFarbe() == Color.Red) {
        rotCounter++;
      }
    }
    if (rotCounter == 2) {
      container.logicUnderTest.getReihenfolge()[container.logicUnderTest.getCurrentPlayer()
          - 1].getInventar().remove(1);
      container.logicUnderTest.getReihenfolge()[container.logicUnderTest.getCurrentPlayer()
          - 1].getInventar().add(new Froschstein(Color.Blue));
    }
    assertFalse(
        container.logicUnderTest.getReihenfolge()[container.logicUnderTest.getCurrentPlayer()
            - 1].getInventar().isEmpty());
    assertFalse(container.testFroschsteinInventar.isEmpty());
  }

  @Und("der Spieler der am Zug ist höchstens einen Froschstein im Inventar hat")
  public void derSpielerDerAmZugIstHöchstensEinenFroschsteinImInventarHat() {
    currentPlayer =
        container.logicUnderTest.getReihenfolge()[container.logicUnderTest.getCurrentPlayer() - 1];
    if (currentPlayer.getInventar().size() > 1) {
      currentPlayer.getInventar().remove(1);
    }
    container.testFroschsteinInventar = currentPlayer.getInventar();
    assertThat(currentPlayer.getInventar()).hasSizeLessThanOrEqualTo(1);
  }

  @Wenn("die Aktion {string} beendet wird")
  public void dieAktionBeendetWird(String arg0) {
  }

  @Dann("wird der Froschstein nicht bewegt")
  public void wirdDerFroschsteinNichtBewegt() {
    assertThat(container.logicUnderTest.getBoard()).isEqualTo(container.testBoard);
  }

  @Und("Rot ist am Zug")
  public void rotIstAmZug() {
    //set current player to red

    container.logicUnderTest.zugAktion.setNextPlayer(Color.Red);
  }

  @Und("{string} ist am Zug")
  public void blauIstAmZug(String farbe) {
    container.logicUnderTest.zugAktion = new ZugAktion(container.logicUnderTest);
    container.logicUnderTest.zugAktion.setAnzahlSpieler(container.logicUnderTest.getPlayerCount());
    switch (farbe) {
      case "Blau" -> container.logicUnderTest.zugAktion.setNextPlayer(Color.Blue);
      case "Grün" -> container.logicUnderTest.zugAktion.setNextPlayer(Color.Green);
      case "Weiß" -> container.logicUnderTest.zugAktion.setNextPlayer(Color.White);
      case "Rot" -> container.logicUnderTest.zugAktion.setNextPlayer(Color.Red);
    }
  }

  @Und("der Spieler der am Zug ist hat mindestens einen weissen Froschstein im Inventar")
  public void derSpielerDerAmZugIstHatMindestensEinenWeissenFroschsteinImInventar() {
    container.testFroschsteinInventar =
        container.logicUnderTest.getReihenfolge()[container.logicUnderTest.getCurrentPlayer()
            - 1].getInventar();
    int rotCounter = 0;
    for (Froschstein froschstein : container.testFroschsteinInventar) {
      if (froschstein.getFroschsteinFarbe() == Color.Blue) {
        rotCounter++;
      }
    }
    if (rotCounter < 1) {
      container.logicUnderTest.getReihenfolge()[container.logicUnderTest.getCurrentPlayer()
          - 1].getInventar().remove(1);
      container.logicUnderTest.getReihenfolge()[container.logicUnderTest.getCurrentPlayer()
          - 1].getInventar().add(new Froschstein(Color.White));
    }
  }
}
