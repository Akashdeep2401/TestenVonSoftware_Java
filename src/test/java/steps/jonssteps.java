package steps;

import de.fhkiel.tsw.Gamelogic;
import io.cucumber.java.de.Angenommen;
import io.cucumber.java.de.Dann;
import io.cucumber.java.de.Wenn;

import static org.assertj.core.api.Assertions.assertThat;

public class jonssteps {

    Gamelogic logic;

    int frösche;
    int iPlayers;

    @Angenommen("es läuft kein Spiel")
    public void es_läuft_kein_spiel() {
        logic = new Gamelogic();
    }
    @Wenn("der Beutelinhalt abgefragt wird")
    public void der_beutelinhalt_abgefragt_wird() {
        frösche = logic.frogsInBag();
    }
    @Dann("sind {int} Frösche im Beutel")
    public void sind_frösche_im_beutel(Integer erwarteteFrösche) {
        assertThat(frösche).isEqualTo(erwarteteFrösche);
    }
    @Angenommen("das Spiel ist mit {int} Spielern gestartet")
    public void das_spiel_ist_mit_spielern_gestartet(Integer spieler) {
        logic = new Gamelogic();
        logic.startGame(spieler);
    }
    @Angenommen("der (erste)(zweite)(dritte)(vierte) Spieler hat {int} Frösche gezogen")
    public void der_erste_spieler_hat_frösche_gezogen(Integer gezogeneFrösche) {
        for (int i = 0; i < gezogeneFrösche; ++i) {
            logic.takeFrogFromBag();
        }

    }

    @Angenommen("das Spiel versucht mit {int} Spielern zu starten")
    public void dasSpielVersuchtMitSpielerSpielernZuStarten(int iAnzahlSpieler) {
        logic = new Gamelogic();
        logic.startGame(iAnzahlSpieler);
    }

    @Wenn("das System die Spieler zählt")
    public void dasSystemDieSpielerZählt() {
        iPlayers = logic.getPlayerCount();
    }



    @Dann("wird das Spiel {int} gestartet")
    public void wirdDasSpielNichtGestartet(int SpielGestartet) {
        boolean SpielSollLaufen;
        SpielSollLaufen = SpielGestartet == 1;

        assertThat(logic.isGameIsRunning()).isEqualTo(SpielSollLaufen);
    }
}
