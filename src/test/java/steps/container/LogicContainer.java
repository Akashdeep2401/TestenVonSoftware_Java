package steps.container;

import de.fhkiel.tsw.Gamelogic;
import de.fhkiel.tsw.Spieler;
import de.fhkiel.tsw.armyoffrogs.Color;

import java.util.ArrayList;
import java.util.List;

public class LogicContainer {
    public Gamelogic logicUnderTest;
    public Color ausgewählteFarbe;
    public boolean FarbenWurdenAusgewählt;

    public ArrayList<Spieler> AlleSpieler = new ArrayList<Spieler>();
    public ArrayList<Color> bereitsAusgewählteFarben = new ArrayList<Color>();

    public boolean checkBagContent() {
        return true;
    }

    public List<Spieler> getPlayersOrder() {
        return AlleSpieler;
    }

    public void setPlayersOrder(ArrayList<Spieler> players) {


    }

    public void setStartspieler(Spieler player1) {
    }
}

