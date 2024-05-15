package steps;

import de.fhkiel.tsw.Spieler;
import de.fhkiel.tsw.armyoffrogs.Color;
import io.cucumber.java.de.Angenommen;
import io.cucumber.java.de.Dann;
import io.cucumber.java.de.Wenn;
import steps.container.LogicContainer;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class ColorSteps {
    private LogicContainer container;

    private Color ausgewählteFarbe = Color.Black;

    private ArrayList<Color> ausgewählteFarben;

    public ColorSteps(LogicContainer container) {
        this.container = container;
    }

    @Angenommen("ein Spieler hat die Farbe Blau ausgewählt")
    public void ein_spieler_hat_die_farbe_blau_ausgewählt() {
        if (ausgewählteFarbe != Color.Black) {
            container.bereitsAusgewählteFarben.add(container.ausgewählteFarbe);
        }
        container.ausgewählteFarbe = Color.Blue;
    }

    @Angenommen("ein Spieler hat die Farbe Rot ausgewählt")
    public void ein_spieler_hat_die_farbe_rot_ausgewählt() {
        if (ausgewählteFarbe != Color.Black) {
            container.bereitsAusgewählteFarben.add(container.ausgewählteFarbe);
        }
        container.ausgewählteFarbe = Color.Red;
    }

    @Angenommen("ein Spieler hat die Farbe Grün ausgewählt")
    public void ein_spieler_hat_die_farbe_grün_ausgewählt() {
        if (ausgewählteFarbe != Color.Black) {
            container.bereitsAusgewählteFarben.add(container.ausgewählteFarbe);
        }
        container.ausgewählteFarbe = Color.Green;
    }

    @Angenommen("ein Spieler hat die Farbe Weiß ausgewählt")
    public void ein_spieler_hat_die_farbe_weiß_ausgewählt() {
        if (ausgewählteFarbe != Color.Black) {
            container.bereitsAusgewählteFarben.add(container.ausgewählteFarbe);
        }
        container.ausgewählteFarbe = Color.White;
    }

    @Angenommen("alle Spieler haben eine Farbe ausgewählt")
    public void alle_spieler_haben_eine_farbe_ausgewählt() {

        container.FarbenWurdenAusgewählt = true;
        container.AlleSpieler.add(new Spieler(Color.Red));
        container.AlleSpieler.add(new Spieler(Color.Blue));
        container.AlleSpieler.add(new Spieler(Color.Green));
    }

    @Wenn("die ausgewählte Farbe überprüft wird")
    public void die_ausgewählte_farbe_überprüft_wird() {
        ausgewählteFarbe = container.ausgewählteFarbe;            // WAS SOLL IN DAS "WENN"?
    }

    @Wenn("die Farben überprüft werden")
    public void die_farben_überprüft_werden() {
        container.bereitsAusgewählteFarben.add(Color.Black);
        ausgewählteFarben = container.bereitsAusgewählteFarben;         // WAS SOLL IN DAS "WENN"?
        ausgewählteFarbe = container.ausgewählteFarbe;
    }
    @Dann("ist die Farbe Blau, Grün, Rot oder Weiß")
    public void ist_die_farbe_blau_grün_rot_oder_weiß() {
        assertThat(ausgewählteFarbe).isIn(Color.Blue, Color.Red, Color.Green, Color.White);
    }
    @Dann("ist die Farbe nicht eine der vorher ausgewählten Farben")
    public void ist_die_farbe_nicht_eine_der_vorher_ausgewählten_farben() {
        assertThat(ausgewählteFarbe).isNotIn(ausgewählteFarben);
    }

    @Dann("hat jeder Spieler eine ihm zugewiesen Farbe")
    public void hat_jeder_spieler_eine_ihm_zugewiesen_farbe() {
        assertThat(container.AlleSpieler).doesNotContain(new Spieler(Color.None));
    }
}
