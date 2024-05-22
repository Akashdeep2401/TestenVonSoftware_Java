package steps;

import de.fhkiel.tsw.Spieler;
import de.fhkiel.tsw.armyoffrogs.Color;
import io.cucumber.java.de.Angenommen;
import io.cucumber.java.de.Dann;
import io.cucumber.java.de.Wenn;
import steps.container.LogicContainer;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
public class BeutelinhaltSteps {
    private LogicContainer container;

    public BeutelinhaltSteps(LogicContainer container) {
        this.container = container;
    }

    @Angenommen("der Beutelinhalt wurde überprüft")
    public void der_beutelinhalt_wurde_überprüft() {
        boolean expecBagContentCount = true;
        assertThat(container.checkBagContent()).isEqualTo(expecBagContentCount);
    }
    @Wenn("die Reihenfolge abgefragt wird")
    public void die_reihenfolge_abgefragt_wird() {
            Object playersOrder = container.getPlayersOrder();

    }
    @Dann("ist einer der Spieler ein Startspieler")
    public void ist_einer_der_spieler_ein_startspieler() {
        // Die Liste der Spieler in der Reihenfolge abrufen
        ArrayList<Spieler> playersOrder = (ArrayList<Spieler>) container.getPlayersOrder();

        // Überprüfen, ob es einen Startspieler gibt
        boolean hasStartPlayer = false;
        for (Spieler player : playersOrder) {
            if (player.isStartspieler()) {
                hasStartPlayer = true;
                break;
            }
        }

        // Assert, dass es mindestens einen Startspieler gibt
        assertThat(hasStartPlayer).isTrue();
    }



}
