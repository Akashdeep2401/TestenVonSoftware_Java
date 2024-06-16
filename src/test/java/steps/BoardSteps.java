package steps;

import de.fhkiel.tsw.Froschstein;
import de.fhkiel.tsw.Gamelogic;
import de.fhkiel.tsw.Spielfeld;
import de.fhkiel.tsw.ZugAktion;
import de.fhkiel.tsw.armyoffrogs.Color;
import de.fhkiel.tsw.armyoffrogs.Position;
import io.cucumber.java.de.Dann;
import io.cucumber.java.de.Und;
import io.cucumber.java.de.Wenn;
import io.cucumber.java.de.Angenommen;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import steps.container.LogicContainer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class BoardSteps {
    private LogicContainer container;
    private Spielfeld testBoard;
    private Position playableFrog;
    private int frogsOnBoard;

    public BoardSteps(LogicContainer container) {
        this.container = container;
    }

    @Wenn("mindestens einer kann gelegt werden")
    public void mindestens_einer_kann_gelegt_werden() {
        frogsOnBoard = container.logicUnderTest.getBoard().size();
        testBoard = container.logicUnderTest.getFrogBoard();
        boolean froschsteinCanBePlaced = false;
        for (Froschstein testFroschstein : container.testFroschsteinInventar) {
            for (Position possiblePlacement : testBoard.getPlacements(testFroschstein.getFroschsteinFarbe())) {
                if (testBoard.froschSetzen(possiblePlacement)) {
                    playableFrog = possiblePlacement;
                    froschsteinCanBePlaced = true;
                }

            }
        }
        assertTrue(froschsteinCanBePlaced);
    }

    @Dann("wird ein Froschstein gelegt")
    public void wirdEinFroschsteinGelegt() {
        container.logicUnderTest.clicked(playableFrog);
        assertThat(container.logicUnderTest.getBoard().size()).isGreaterThan(frogsOnBoard);
    }

    @Dann("ist keine Kette aus Froschsteinen gebildet worden")
    public void istKeineKetteAusFroschsteinenGebildetWorden() {
        assertThat(container.logicUnderTest.hasNoChains()).isTrue();
    }
    @Wenn("die Aktion {string} so gespielt wird das eine Kette entstehen würde")
    public void dieAktionSoGespieltWirdDasEineKetteEntstehenWürde(String aktion) {
        container.logicUnderTest.zugAktion.setCurrentAction(aktion);
        if (aktion.equals("Anlegen")) {
            container.AnzFröscheInventarVorAktion = container.logicUnderTest.getReihenfolge()[container.logicUnderTest.getCurrentPlayer()].getInventorySize();
            container.AnzFröscheAufSpielfeldVorAktion = container.logicUnderTest.getBoard().size();
            Position wrongPlacement = container.logicUnderTest.getWrongPlacement();
            container.logicUnderTest.clicked(wrongPlacement);
        }
        if (aktion.equals("Bewegen")) {
            container.testBoard = container.logicUnderTest.getBoard();
            container.logicUnderTest.clicked(new Position(Color.Red, 2, 0, Color.None));
            container.logicUnderTest.clicked(new Position(Color.Red, 4, 4, Color.None));
        }
    }

    @Dann("wird der Froschstein nicht angelegt")
    public void wirdDerFroschsteinNichtAngelegt() {
        assertThat(container.logicUnderTest.getBoard().size()).isEqualTo(container.AnzFröscheAufSpielfeldVorAktion);
        assertThat(container.logicUnderTest.getReihenfolge()[container.logicUnderTest.getCurrentPlayer()].getInventorySize()).isEqualTo(container.AnzFröscheInventarVorAktion);
    }
}
