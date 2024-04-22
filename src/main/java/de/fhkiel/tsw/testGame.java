package de.fhkiel.tsw;

import de.fhkiel.tsw.armyoffrogs.Color;
import de.fhkiel.tsw.armyoffrogs.Game;
import de.fhkiel.tsw.armyoffrogs.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class testGame implements Game {

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
        return "es geht!!!";
    }

    @Override
    public int frogsInBag() {
        return 0;
    }

    @Override
    public List<Color> getFrogsInHand(Color color) {
        return new ArrayList<>();
    }

    @Override
    public Set<Position> getBoard() {
        return new HashSet<>();
    }

    @Override
    public void clicked(Position position) {

    }

    @Override
    public void selectedFrogInHand(Color color, Color color1) {

    }

    @Override
    public Color winner() {
        return Color.None;
    }

    @Override
    public boolean save(String s) {
        return false;
    }

    @Override
    public boolean load(String s) {
        return false;
    }
}
