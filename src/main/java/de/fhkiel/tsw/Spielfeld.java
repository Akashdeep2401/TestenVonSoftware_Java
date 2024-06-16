package de.fhkiel.tsw;

import de.fhkiel.tsw.armyoffrogs.Color;
import de.fhkiel.tsw.armyoffrogs.Position;

import java.util.*;

public class Spielfeld {

    public Spielfeld() {
    }

    public Spielfeld(Set<Position> board) {
        froschfeld = board;
    }

    private Set<Position> froschfeld = new HashSet<>();

    public boolean keineKetten;

    private List<AbstractMap.SimpleEntry<Integer, Integer>> adjacentGerade = Arrays.asList(
            new AbstractMap.SimpleEntry<>(-1, 0),  // left
            new AbstractMap.SimpleEntry<>(1, 0),   // right
            new AbstractMap.SimpleEntry<>(0, -1),  // top-right
            new AbstractMap.SimpleEntry<>(0, 1),   // bottom-right
            new AbstractMap.SimpleEntry<>(-1, -1), // top-left
            new AbstractMap.SimpleEntry<>(-1, 1)   // bottom-left
    );

    private List<AbstractMap.SimpleEntry<Integer, Integer>> adjacentUngerade = Arrays.asList(
            new AbstractMap.SimpleEntry<>(-1, 0),  // left
            new AbstractMap.SimpleEntry<>(1, 0),   // right
            new AbstractMap.SimpleEntry<>(1, -1),  // top-right
            new AbstractMap.SimpleEntry<>(1, 1),   // bottom-right
            new AbstractMap.SimpleEntry<>(0, -1),  // top-left
            new AbstractMap.SimpleEntry<>(0, 1)    // bottom-left
    );

    public Set<Position> getFroschfeld() {
        return froschfeld;
    }

    public boolean froschSetzen(Position position) {
        if (froschfeld.contains(position)) {
            return false;
        }
        if (froschfeld.isEmpty()) {
            froschfeld.add(position);
            return true;
        }
        if (isAdjacent(position, false) && !isAdjacent(position, true)) {
            froschfeld.add(position);
            return true;
        }
        return false;
    }

    private boolean isAdjacent(Position position, boolean testWithColor) {
        if (testWithColor) {
            if (position.y() % 2 == 0) {
                return testColorAdjacency(position, adjacentGerade);
            } else {
                return testColorAdjacency(position, adjacentUngerade);
            }
        } else {
            if (position.y() % 2 == 0) {
                return testAdjacency(position, adjacentGerade);
            } else {
                return testAdjacency(position, adjacentUngerade);
            }
        }
    }

    private boolean testAdjacency(Position position, List<AbstractMap.SimpleEntry<Integer, Integer>> adjacentAddend) {
        return adjacentAddend.stream().anyMatch(p -> {
            for (Position pos : froschfeld) {
                if (pos.x() == position.x() + p.getKey() && pos.y() == position.y() + p.getValue()) {
                    return true;
                }
            }
            return false;
        });
    }

    private boolean testColorAdjacency(Position position, List<AbstractMap.SimpleEntry<Integer, Integer>> adjacentAddend) {
        return adjacentAddend.stream().anyMatch(p -> froschfeld.contains(new Position(position.frog(), position.x() + p.getKey(), position.y() + p.getValue(), position.border())));
    }

    // test available placing positions
    private List<AbstractMap.SimpleEntry<Integer, Integer>> testAvailablePlacingPositions() {
        List<AbstractMap.SimpleEntry<Integer, Integer>> availablePositions = new ArrayList<>();
        if (froschfeld.isEmpty()) {
            availablePositions.add(new AbstractMap.SimpleEntry<>(0, 0));
            return availablePositions;
        }
        for (Position placedFrog : froschfeld) {
            // get every adjacent position that is not occupied by a frog

            for (AbstractMap.SimpleEntry<Integer, Integer> addend : placedFrog.y() % 2 == 0 ? adjacentGerade : adjacentUngerade) {
                Position placement = new Position(placedFrog.frog(), placedFrog.x() + addend.getKey(), placedFrog.y() + addend.getValue(), placedFrog.border());
                if (!froschfeld.contains(placement)) {
                    availablePositions.add(new AbstractMap.SimpleEntry<>(placement.x(), placement.y()));
                }
            }
        }
        return availablePositions;
    }

    public List<Position> getPlacements(Color anlegeFarbe) {
        List<Position> allPlacements = new ArrayList<>();
        for (AbstractMap.SimpleEntry<Integer, Integer> freePos : testAvailablePlacingPositions()) {
            allPlacements.add(new Position(anlegeFarbe, freePos.getKey(), freePos.getValue(), Color.None));
        }
        allPlacements.removeIf(pos -> testColorAdjacency(pos, pos.y() % 2 == 0 ? adjacentGerade : adjacentUngerade));
        return allPlacements;
    }
}
