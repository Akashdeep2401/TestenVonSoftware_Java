package steps;

import de.fhkiel.tsw.Froschstein;
import de.fhkiel.tsw.Gamelogic;
import de.fhkiel.tsw.armyoffrogs.Color;
import de.fhkiel.tsw.armyoffrogs.Position;
import io.cucumber.java.de.Angenommen;
import io.cucumber.java.de.Dann;
import io.cucumber.java.de.Wenn;
import org.assertj.core.api.Condition;
import steps.container.LogicContainer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

public class BeutelSteps {

  private final LogicContainer container;

  public BeutelSteps(LogicContainer container) {
    this.container = container;
  }

  @Angenommen("jeder Spieler sich am Anfang {int} Frösche genommen hat")
  public void jeder_spieler_sich_am_anfang_frösche_genommen_hat(Integer AnzahlFrösche2) {
    container.logicUnderTest.erstesFröscheNehmen(AnzahlFrösche2);
  }

  @Wenn("der Beutel befüllt wurde")
  public void der_beutel_befüllt_wurde() {
    container.logicUnderTest = new Gamelogic();
    container.logicUnderTest.beutelBefüllen(container.bereitsAusgewählteFarben);
  }

  @Dann("sind nur Froschsteine im Beutel die Blau, Grün, Rot oder Weiß sind")
  public void sind_nur_froschsteine_im_beutel_die_blau_grün_rot_oder_weiß_sind() {
    List<Color> RichtigeFarben = new ArrayList<>();
    RichtigeFarben.add(Color.Blue);
    RichtigeFarben.add(Color.Green);
    RichtigeFarben.add(Color.Red);
    RichtigeFarben.add(Color.White);
    assertThat(container.logicUnderTest.getSpielBeutel().getFroschsteine()).allSatisfy(
        TestStein -> {
          assertThat(TestStein.getFroschsteinFarbe()).isIn(RichtigeFarben);
        });
  }

  @Dann("sind {int} Frösche im Beutel")
  public void sind_frösche_im_beutel(int erwarteteFrösche) {
    assertThat(container.logicUnderTest.frogsInBag()).isEqualTo(erwarteteFrösche);
  }

  @Dann("sind nur Froschsteine mit den Farben der Spieler im Beutel")
  public void sind_nur_froschsteine_mit_den_farben_der_spieler_im_beutel() {
    assertThat(container.logicUnderTest.getSpielBeutel().getFroschsteine()).allSatisfy(
        TestStein -> {
          assertThat(TestStein.getFroschsteinFarbe()).isIn(container.bereitsAusgewählteFarben);
        });
  }

  @Dann("sind {int} Froschsteine von jeder ausgewählten Farbe im Beutel")
  public void sind_froschsteine_von_jeder_ausgewählten_farbe_im_beutel(
      Integer AnzahlSteine) { // AnzahlSteine ist immer 10
    for (Color Farbe : container.logicUnderTest.players()) { // Für jede Farbe die ausgewählt wurde
      Condition<Froschstein> RichtigerStein =
          new Condition<>(TestStein -> TestStein.getFroschsteinFarbe() == Farbe,
              "Richtiger Stein"); // Ist die Froschsteinfarbe die Farbe eines Spielers
      assertThat(container.logicUnderTest.getSpielBeutel().getFroschsteine()).haveExactly(
          AnzahlSteine, RichtigerStein); // Sind 10 Steine jeder Farbe im Beutel
    }
  }

  @Dann("wird ein Froschstein zufällig aus dem Beutel genommen")
  public void wirdEinFroschsteinZufälligAusDemBeutelGenommen() {
    int currentPlayer = container.logicUnderTest.getCurrentPlayer();
    container.logicUnderTest.getSpielBeutel()
        .froschNehmen(container.logicUnderTest.getReihenfolge()[currentPlayer - 1]);
    assertThat(container.logicUnderTest.frogsInBag()).isGreaterThan(
        container.AnzFröscheBeutelVorAktion);
    assertThat(container.logicUnderTest.getReihenfolge()[currentPlayer
        - 1].getInventorySize()).isGreaterThan(container.AnzFröscheInventarVorAktion);
  }
}


