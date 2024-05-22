package de.fhkiel.tsw;

import de.fhkiel.tsw.armyoffrogs.Color;
import de.fhkiel.tsw.armyoffrogs.Game;
import de.fhkiel.tsw.armyoffrogs.Position;

import java.util.List;
import java.util.Set;

public class Gamelogic implements Game {


    private Beutel SpielBeutel;

    private int iSpieler;

    private boolean GameIsRunning;

    private Color[] CPlayers;
    private Spieler[] AlleSpieler;


    @Override
    public boolean newGame(int i) {
        return false;
    }

    @Override
    public Color[] players() {
        return CPlayers;
    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public int frogsInBag() {
        return SpielBeutel.getAnzFrösche();
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

    public void startGame(int spieler, List<Color> SpielerFarben) {
        AlleSpieler = new Spieler[spieler];
        iSpieler = spieler;
        if (!checkPlayerCount(spieler)) {
            GameIsRunning = false;
            System.out.println("Spiel kann nicht gestartet werden. Zu viele oder zu wenige Spieler");
            return;
        }
        CPlayers = new Color[spieler];
        int j = 0;
        for (Color EinzSpielerFarbe : SpielerFarben) {
            AlleSpieler[j] = new Spieler(EinzSpielerFarbe);
            CPlayers[j] = EinzSpielerFarbe;
            j++;
        }

        BeutelBefüllen(SpielerFarben);

        GameIsRunning = true;
    }

    public void ErstesFröscheNehmen() {
        for (int i = 0; i < 2 * iSpieler; ++i) {
            SpielBeutel.FroschNehmen();
        }
        System.out.println("Die ersten Frösche wurden gezogen");
    }

    public void takeFrogFromBag() {
        SpielBeutel.FroschNehmen();
    }

    private boolean checkPlayerCount(int iAnzSpieler) {
        return iAnzSpieler <= 4 && iAnzSpieler >= 2;
    }
    private void BeutelBefüllen(List<Color> FarbenInBeutel) {
        SpielBeutel = new Beutel(FarbenInBeutel);
    }
    public int getPlayerCount(){
        return iSpieler;
    }

    public boolean isGameIsRunning() {
        return GameIsRunning;
    }

    public Beutel getSpielBeutel() {
        return SpielBeutel;
    }
}
