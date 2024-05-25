package steps;

import io.cucumber.java.de.Dann;
import io.cucumber.java.de.Wenn;
import steps.container.LogicContainer;
import static org.assertj.core.api.Assertions.assertThat;

public class ZugSteps {
    
    private LogicContainer container;

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
}
