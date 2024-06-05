package steps;

import de.fhkiel.tsw.ZugAktion;
import io.cucumber.java.de.Dann;
import io.cucumber.java.de.Wenn;
import io.cucumber.java.de.Angenommen;
import steps.container.LogicContainer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    @Angenommen("eine Aktion versucht wurde auszuführen")
    public void eine_aktion_versucht_wurde_auszuführen() {
        zugAktion.attemptAction();  // Annahme, dass diese Methode in Ihrer ZugAktion Klasse existiert
    }
    @Dann("wird die nächste Aktion in der Spielreihenfolge ausgeführt")
    public void wird_die_nächste_aktion_in_der_spielreihenfolge_ausgeführt() {
        boolean isNextActionStarted = zugAktion.startNextAction(); // Annahme, dass diese Methode überprüft, ob die nächste Aktion gestartet wurde
        assertThat(isNextActionStarted).isTrue();
    }
    @Angenommen("die Aktion {string} wird ausgeführt")
    public void die_aktion_wird_ausgeführt(String action) {
        zugAktion.executeAction(action);
    }
    @Angenommen("der Froschstein wurde bewegt")
    public void der_froschstein_wurde_bewegt() {
        assertTrue(zugAktion.isFrogMoved());
    }
    @Dann("kann der Froschstein bewegt werden")
    public void kann_der_froschstein_bewegt_werden() {
        assertTrue(zugAktion.canMoveFrog());
    }

    @Dann("darf der Froschstein nicht an der selben Position wie am Anfang des Zuges sein")
    public void darfDerFroschsteinNichtAnDerSelbenPositionWieAmAnfangDesZugesSein() {

    }

    @Wenn("der Froschstein bewegt wird")
    public void derFroschsteinBewegtWird() {
        assertTrue(zugAktion.isFrogMoved());
    }
}
