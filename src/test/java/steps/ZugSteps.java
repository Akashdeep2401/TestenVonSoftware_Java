package steps;

import de.fhkiel.tsw.ZugAktion;
import io.cucumber.java.de.Dann;
import io.cucumber.java.de.Wenn;
import io.cucumber.java.de.Angenommen;
import steps.container.LogicContainer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

public class ZugSteps {
    
    private LogicContainer container;

    private ZugAktion zugAktion = new ZugAktion();

    public ZugSteps(LogicContainer container) {
        this.container = container;

    }

    private int LetzterSpieler;

    @Wenn("Spieler {int} seinen Zug beendet hat")
    public void spieler_spieler_seinen_zug_beendet_hat(int SpielerDran) {
        container.logicUnderTest.setCurrentPlayer(SpielerDran);
        container.logicUnderTest.zugBeenden(container.logicUnderTest.getReihenfolge()[SpielerDran - 1]);
        //LetzterSpieler = container.logicUnderTest.getLastPlayer();
    }

    @Dann("ist Spieler {int} am Zug")
    public void istSpielerSpielerAmZug(int SpielerDran) {
        assertThat(container.logicUnderTest.zugStarten(container.logicUnderTest.getReihenfolge()[SpielerDran - 1])).isTrue();
    }

    @Dann("ist Spieler {int} nicht am Zug")
    public void ist_spieler_nicht_am_zug(int SpielerNichtDran) {
        assertThat(container.logicUnderTest.zugStarten(container.logicUnderTest.getReihenfolge()[SpielerNichtDran - 1])).isFalse();

    }


    @Angenommen("ein Zug wird gespielt")
    public void ein_zug_wird_gespielt() {
        zugAktion.startTurn();
    }
    @Angenommen("die Aktion {string} ist nicht gespielt worden")
    public void die_aktion_ist_nicht_gespielt_worden(String action) {
       assertThat(zugAktion.isActionPlayed(action)).isFalse();
    }
    @Wenn("der Zug abgefragt wird")
    public void der_zug_abgefragt_wird() {
        zugAktion.checkCurrentTurn();
    }
    @Dann("ist die Aktion {string} nicht gespielt worden")
    public void ist_die_aktion_nicht_gespielt_worden(String action) {
        assertThat(zugAktion.isActionPlayed(action)).isFalse();
    }
    @Dann("die Aktion {string} nicht gespielt worden")
    public void die_aktion_nicht_gespielt_worden(String action) {
        assertThat(
            zugAktion.isActionPlayed(action)
        ).isFalse();

    }

    @Dann("hat kein Spieler die Aktion {string} gespielt")
    public void hatKeinSpielerDieAktionGespielt(String action) {
        assertThat(zugAktion.isActionPlayed(action)).isFalse();
    }

    @Angenommen("die Aktion {string} ist gespielt worden")
    public void die_aktion_ist_gespielt_worden(String action) {
        zugAktion.playAction(action);
        assertThat(zugAktion.isActionPlayed(action)).isTrue();
    }
}
