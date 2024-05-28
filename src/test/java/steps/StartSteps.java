package steps;

import de.fhkiel.tsw.Gamelogic;
import de.fhkiel.tsw.armyoffrogs.Color;
import io.cucumber.java.de.Angenommen;
import io.cucumber.java.de.Dann;
import io.cucumber.java.de.Wenn;
import steps.container.LogicContainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StartSteps {

    private LogicContainer container;

    public StartSteps(LogicContainer container) {
        this.container = container;
    }

    int frösche;
    int iPlayers;

    Color[] TestColors = {Color.Blue, Color.Red, Color.White, Color.Green};

    List<Color> TestColorsArrayList = new ArrayList<>();

    @Angenommen("es läuft kein Spiel")
    public void es_läuft_kein_spiel() {
        container.logicUnderTest = new Gamelogic();
    }

    @Angenommen("das Spiel läuft")
    public void das_spiel_läuft() {
        container.logicUnderTest = new Gamelogic();
        TestColorsArrayList.addAll(Arrays.asList(TestColors));
        container.logicUnderTest.startGame(4, TestColorsArrayList);
    }

    @Wenn("der Beutelinhalt abgefragt wird")
    public void der_beutelinhalt_abgefragt_wird() {
        frösche = container.logicUnderTest.frogsInBag();
    }
    /*
    @Dann("sind {int} Frösche im Beutel")
    public void sind_frösche_im_beutel(Integer erwarteteFrösche) {
        assertThat(frösche).isEqualTo(erwarteteFrösche);
    }
    */
    @Angenommen("das Spiel ist mit {int} Spielern gestartet")
    public void das_spiel_ist_mit_spielern_gestartet(Integer spieler) {
        container.logicUnderTest = new Gamelogic();
        List<Color> TestFarben = new ArrayList<>();
        TestFarben.add(Color.Blue);
        TestFarben.add(Color.Red);
        TestFarben.add(Color.Green);
        container.logicUnderTest.startGame(spieler, TestFarben);
    }
    @Angenommen("der (erste)(zweite)(dritte)(vierte) {int} Spieler hat {int} Frösche gezogen")
    public void der_erste_spieler_hat_frösche_gezogen(int Spieler, Integer gezogeneFrösche) {
        for (int i = 0; i < gezogeneFrösche; ++i) {
            container.logicUnderTest.takeFrogFromBag(container.logicUnderTest.getReihenfolge()[Spieler]);
        }

    }

    @Angenommen("das Spiel versucht mit {int} Spielern zu starten")
    public void dasSpielVersuchtMitSpielerSpielernZuStarten(int iAnzahlSpieler) {
        container.logicUnderTest = new Gamelogic();
        List<Color> TestFarben = new ArrayList<>();
        if (iAnzahlSpieler == 2) {
            TestFarben.add(Color.Blue);
            TestFarben.add(Color.Red);
        } else if (iAnzahlSpieler == 3) {
            TestFarben.add(Color.Blue);
            TestFarben.add(Color.Red);
            TestFarben.add(Color.Green);
        } else if (iAnzahlSpieler == 4){
            TestFarben.add(Color.Blue);
            TestFarben.add(Color.Red);
            TestFarben.add(Color.Green);
            TestFarben.add(Color.White);
        }
        container.logicUnderTest.startGame(iAnzahlSpieler, TestFarben);
    }

    @Wenn("das System die Spieler zählt")
    public void dasSystemDieSpielerZählt() {
        iPlayers = container.logicUnderTest.getPlayerCount();
    }



    @Dann("wird das Spiel {int} gestartet")
    public void wirdDasSpielNichtGestartet(int SpielGestartet) {
        boolean SpielSollLaufen;
        SpielSollLaufen = SpielGestartet == 1;

        assertThat(container.logicUnderTest.isGameIsRunning()).isEqualTo(SpielSollLaufen);
    }
}
