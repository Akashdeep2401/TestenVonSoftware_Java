package steps;

import de.fhkiel.tsw.Spieler;
import de.fhkiel.tsw.armyoffrogs.Color;
import io.cucumber.java.de.Angenommen;
import io.cucumber.java.de.Dann;
import io.cucumber.java.de.Wenn;
import steps.container.LogicContainer;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StartSpieler {
    private LogicContainer container;

    public StartSpieler(LogicContainer container) {
        this.container = container;
    }
    /*
     * -------------------------------------------------------------
     *                       Anforderung 10                        |
     * -------------------------------------------------------------
     * */
    @Angenommen("es gibt einen Startspieler {string}")
    public void es_gibt_einen_startspieler(String spielerFarbe) {
        // Spieler hinzufügen
        ArrayList<Spieler> players = new ArrayList<>();
        Spieler player1 = new Spieler(Color.valueOf(spielerFarbe), 25);
        Spieler player2 = new Spieler(Color.Red, 20);
        Spieler player3 = new Spieler(Color.White, 30);
        Spieler player4 = new Spieler(Color.Green, 22);

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);

        container.setPlayersOrder(players);

        // Den angegebenen Spieler als Startspieler festlegen
        container.setStartspieler(player1);
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
}
