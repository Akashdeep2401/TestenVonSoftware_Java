package steps;

import de.fhkiel.tsw.Spieler;
import io.cucumber.java.de.Dann;
import io.cucumber.java.de.Und;
import io.cucumber.java.de.Wenn;
import steps.container.LogicContainer;
import static org.assertj.core.api.Assertions.assertThat;

public class FroschsteinSteps {

    private LogicContainer container;

    public FroschsteinSteps(LogicContainer container) {
        this.container = container;
    }


    @Wenn("das Inventar des Spielers {int} abgefragt wird")
    public void das_inventar_des_spielers_abgefragt_wird(int speilerIndex) {
        container.Playingnow = container.logicUnderTest.getReihenfolge()[speilerIndex - 1];
    }
    @Dann("hat Spieler {int} maximal zwei Froschsteine")
    public void hat_spieler_maximal_zwei_froschsteine(int speilerIndex) {
        int froschsteine = container.Playingnow.getInventar().size();
        if (froschsteine > 2) {
            throw new IllegalStateException("Spieler hat mehr als zwei Froschsteine");
        }

    }
    @Dann("{int} Froschsteine im Inventar von jedem Spieler")
    public void froschsteine_im_inventar_von_jedem_spieler(Integer AnzahlFrösche) {
        for (Spieler EinSpieler : container.logicUnderTest.getAlleSpieler()) {
            assertThat(EinSpieler.getInventorySize()).isEqualTo(AnzahlFrösche);
        }
    }

    @Und("{int} Froschsteine von jeden Spieler werden in der GUI angezeigt")
    public void froschsteineVonJedenSpielerWerdenInDerGUIAngezeigt(int Froschsteine) {
        for (Spieler EinSpieler : container.logicUnderTest.getAlleSpieler()) {
            assertThat(container.logicUnderTest.getFrogsInHand(EinSpieler.getSpielerFarbe())).hasSize(Froschsteine);
        }
    }
}
