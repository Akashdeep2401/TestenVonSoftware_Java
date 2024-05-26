package steps;

import de.fhkiel.tsw.Spieler;
import io.cucumber.java.de.Dann;
import io.cucumber.java.de.Wenn;
import steps.container.LogicContainer;

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
        /*int froschsteine = container.Playingnow.getBeutel().getFroschsteine().size();
        if (froschsteine > 2) {
            throw new IllegalStateException("Spieler hat mehr als zwei Froschsteine");
        }

         */

    }
}
