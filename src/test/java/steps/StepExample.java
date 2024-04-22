package steps;

import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.java.de.Dann;
import io.cucumber.java.de.Und;
import io.cucumber.java.de.Wenn;

public class StepExample {

  String name;

  @Wenn("{word} lehrt")
  public void lehrt(String name) {
    this.name = name;
  }

  @Dann("wird ein Beispiel ausgefürt")
  public void wirdEinBeispielAusgefuert() {
    System.out.println("Vrumm");
  }

  @Und("es läuft( nicht)")
  public void esLauft() {
    assertThat(name).isEqualTo("Kai");
  }
}
