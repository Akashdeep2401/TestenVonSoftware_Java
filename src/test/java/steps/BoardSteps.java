package steps;

import de.fhkiel.tsw.Froschstein;
import de.fhkiel.tsw.Gamelogic;
import de.fhkiel.tsw.armyoffrogs.Color;
import io.cucumber.java.de.Angenommen;
import io.cucumber.java.de.Dann;
import io.cucumber.java.de.Wenn;
import org.assertj.core.api.Condition;
import steps.container.LogicContainer;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;


public class BoardSteps {

  private LogicContainer container;
  public BoardSteps() {
    this.container = new LogicContainer();
  }

  @Dann("ist keine Kette aus Froschsteinen gebildet worden")
  public void istKeineKetteAusFroschsteinenGebildetWorden() {
    assertThat(container.logicUnderTest.hasNoChains()).isTrue();
  }
}
