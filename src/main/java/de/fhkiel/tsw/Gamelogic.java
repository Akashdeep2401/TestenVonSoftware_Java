package de.fhkiel.tsw;

import de.fhkiel.tsw.armyoffrogs.Color;
import de.fhkiel.tsw.armyoffrogs.Game;
import de.fhkiel.tsw.armyoffrogs.Position;

import java.util.List;
import java.util.Set;

public class Gamelogic implements Game {


    private Beutel SpielBeutel = new Beutel();

    private int iSpieler;

    private boolean GameIsRunning;


    @Override
    public boolean newGame(int i) {
        return false;
    }

    @Override
    public Color[] players() {
        return new Color[0];
    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public int frogsInBag() {
        return SpielBeutel.getFrösche();
    }

    @Override
    public List<Color> getFrogsInHand(Color color) {
        return null;
    }

    @Override
    public Set<Position> getBoard() {
        return null;
    }

    @Override
    public void clicked(Position position) {

    }

    @Override
    public void selectedFrogInHand(Color color, Color color1) {

    }

    @Override
    public Color winner() {
        return null;
    }

    @Override
    public boolean save(String s) {
        return false;
    }

    @Override
    public boolean load(String s) {
        return false;
    }

    public void startGame(int spieler) {
        iSpieler = spieler;
        if (!checkPlayerCount(spieler)) {
            GameIsRunning = false;
            System.out.println("Spiel kann nicht gestartet werden. Zu viele oder zu wenige Spieler");
            return;
        }
        SpielBeutel = new Beutel(spieler * 10);
        for (int i = 0; i < 2 * spieler; ++i) {
            SpielBeutel.FroschNehmen();
        }
        GameIsRunning = true;
        System.out.println("Das Spiel wurde gestartet");
    }

    public void takeFrogFromBag() {
        SpielBeutel.FroschNehmen();
    }

    private boolean checkPlayerCount(int iAnzSpieler) {
        return iAnzSpieler <= 4 && iAnzSpieler >= 2;
    }
    public int getPlayerCount(){
        return iSpieler;
    }

    public boolean isGameIsRunning() {
        return GameIsRunning;
    }
}
