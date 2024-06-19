package steps;

import de.fhkiel.tsw.Gamelogic;
import de.fhkiel.tsw.armyoffrogs.Color;
import de.fhkiel.tsw.armyoffrogs.Position;
import io.cucumber.java.de.Angenommen;
import io.cucumber.java.de.Dann;
import io.cucumber.java.de.Und;
import io.cucumber.java.de.Wenn;

import java.util.HashSet;
import java.util.Set;

import steps.container.LogicContainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StartSteps {

  private final LogicContainer container;
  int frösche;
  int iPlayers;
  private boolean SpielGestartet = false;
  public StartSteps(LogicContainer container) {
    this.container = container;
  }

  @Angenommen("es läuft kein Spiel")
  public void es_läuft_kein_spiel() {
    container.logicUnderTest = new Gamelogic();
  }

  @Angenommen("das Spiel läuft")
  public void das_spiel_läuft() {
    container.logicUnderTest = new Gamelogic();
    container.TestColorsArrayList = new ArrayList<>();
    container.TestColorsArrayList.addAll(Arrays.asList(container.TestColors));
    container.logicUnderTest.startGame(4, container.TestColorsArrayList);
  }

  @Wenn("der Beutelinhalt abgefragt wird")
  public void der_beutelinhalt_abgefragt_wird() {
    frösche = container.logicUnderTest.frogsInBag();
  }

  @Angenommen("das Spiel ist mit {int} Spielern gestartet")
  public void das_spiel_ist_mit_spielern_gestartet(Integer spieler) {
    container.logicUnderTest = new Gamelogic();
    List<Color> TestFarben = new ArrayList<>();
    TestFarben.add(Color.Blue);
    TestFarben.add(Color.Red);
    TestFarben.add(Color.Green);
    assertThat(container.logicUnderTest.newGame(spieler)).isTrue();
  }

  @Angenommen("der (erste)(zweite)(dritte)(vierte) {int} Spieler hat {int} Frösche gezogen")
  public void der_erste_spieler_hat_frösche_gezogen(int Spieler, Integer gezogeneFrösche) {
    for (int i = 0; i < gezogeneFrösche; ++i) {
      container.logicUnderTest.takeFrogFromBag(container.logicUnderTest.getReihenfolge()[Spieler]);
    }

  }

  @Angenommen("das Spiel versucht mit {int} Spielern zu starten")
  public void dasSpielVersuchtMitSpielerSpielernZuStarten(int iAnzahlSpieler) {
    container.logicUnderTest = new Gamelogic();
    List<Color> TestFarben = new ArrayList<>();
    if (iAnzahlSpieler == 2) {
      TestFarben.add(Color.Blue);
      TestFarben.add(Color.Red);
    } else if (iAnzahlSpieler == 3) {
      TestFarben.add(Color.Blue);
      TestFarben.add(Color.Red);
      TestFarben.add(Color.Green);
    } else if (iAnzahlSpieler == 4) {
      TestFarben.add(Color.Blue);
      TestFarben.add(Color.Red);
      TestFarben.add(Color.Green);
      TestFarben.add(Color.White);
    }
    container.logicUnderTest.startGame(iAnzahlSpieler, TestFarben);
  }

  @Wenn("das System die Spieler zählt")
  public void dasSystemDieSpielerZählt() {
    iPlayers = container.logicUnderTest.getPlayerCount();
  }


  @Dann("wird das Spiel {int} gestartet")
  public void wirdDasSpielNichtGestartet(int SpielGestartet) {
    boolean SpielSollLaufen;
    SpielSollLaufen = SpielGestartet == 1;

    assertThat(container.logicUnderTest.isGameIsRunning()).isEqualTo(SpielSollLaufen);
  }


  @Angenommen("das Spiel ist a gestartet b")
  public void das_spiel_ist_a_huhugestartet() {
    container.logicUnderTest = new Gamelogic();
    SpielGestartet = container.logicUnderTest.newGame(2);
  }

  @Dann("ist das Spiel a gestartet")
  public void ist_das_spiel_a_gestartet() {
    assertThat(SpielGestartet).isTrue();
  }
}
