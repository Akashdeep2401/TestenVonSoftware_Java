package steps;

import de.fhkiel.tsw.Gamelogic;
import de.fhkiel.tsw.Spieler;
import de.fhkiel.tsw.armyoffrogs.Color;
import io.cucumber.java.de.Dann;
import io.cucumber.java.de.Wenn;
import steps.container.LogicContainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayOrderSteps {

    private LogicContainer container;

    public PlayOrderSteps(LogicContainer container) {
        this.container = container;
    }



    @Wenn("die Spielreihenfolge abgefragt wird")
    public void die_spielreihenfolge_abgefragt_wird() {
        container.getPlayersOrder();
    }

    @Dann("ist Spieler {string} der erste Spieler in der Spielreihenfolge")
    public void ist_spieler_der_erste_spieler_in_der_spielreihenfolge(String spielerFarbe) {
        List<Spieler> playersOrder = container.getPlayersOrder();
        Spieler firstPlayer = playersOrder.get(0);
        assertThat(firstPlayer.getSpielerFarbe()).isEqualTo(Color.valueOf(spielerFarbe));
    }

    @Wenn("die Reihenfolge bestimmt wurde")
    public void die_reihenfolge_bestimmt_wurde() {
        container.logicUnderTest = new Gamelogic();
        // Spieler hinzufügen
        ArrayList<Color> players = new ArrayList<>();
        Color player1 = (Color.Blue);
        Color player2 = (Color.Red);
        Color player3 = (Color.White);
        Color player4 = (Color.Green);

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);

        container.logicUnderTest.startGame(4, players);

        container.setStartspieler(container.logicUnderTest.getStartSpieler());

        container.Reihenfolge.addAll(Arrays.asList(container.logicUnderTest.getReihenfolge()));
    }

    @Dann("ist einer der Spieler ein Startspieler")
    public void ist_einer_der_spieler_ein_startspieler() {
        List<Spieler> playersOrder = container.Reihenfolge;
        boolean hasStartPlayer = false;
        for (Spieler player : playersOrder) {
            if (player.isStartspieler()) {
                hasStartPlayer = true;
                break;
            }
        }
        assertThat(hasStartPlayer).isTrue();
        assertThat(container.StartSpieler).isNotNull();
    }

    @Dann("ist der Startspieler der erste Spieler in der Spielreihenfolge")
    public void ist_der_startspieler_der_erste_spieler_in_der_spielreihenfolge() {
        assertThat(container.StartSpieler).isEqualTo(container.logicUnderTest.getReihenfolge()[0]);
    }
}
