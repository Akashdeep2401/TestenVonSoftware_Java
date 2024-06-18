package de.fhkiel.tsw;

import de.fhkiel.tsw.armyoffrogs.Color;
import de.fhkiel.tsw.armyoffrogs.Position;

import java.util.*;

public class Spielfeld {

    public Spielfeld() {
    }

    public Spielfeld(Set<Position> board, Gamelogic gamelogic) {
        this.gamelogic = gamelogic;
        froschfeld = board;
    }

    private Gamelogic gamelogic;
    private Set<Position> froschfeld = new HashSet<>();

    public boolean keineKetten;
    private Position selectedFrog = null;

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

    public boolean froschSetzen(Position toBePlacedFrog) {
        // For placing frogs
        if (froschfeld.isEmpty()) {
            froschfeld.add(toBePlacedFrog);
            return true;
        }

        if (isAdjacent(toBePlacedFrog, true)) {
            return false;
        }
        // General placability check
        return testPlacability(toBePlacedFrog);
    }

    public boolean froschBewegen(Position jumpingPosition) {
        if (jumpingPosition.equals(selectedFrog)) {
            selectedFrog = null;
            return false;
        }

        if (!getJumpablePositions().contains(jumpingPosition)) {
            return false;
        }
        boolean jumped = testPlacability(selectedFrog);
        if (jumped) {
            froschfeld.remove(selectedFrog);
            selectedFrog = new Position(selectedFrog.frog(), jumpingPosition.x(), jumpingPosition.y(), jumpingPosition.border());
        }
        return jumped;
    }
    private boolean testPlacability(Position frog) {
        if (froschfeld.contains(frog)) {
            return false;
        }
        if (!isAdjacent(frog, false)) {
            return false;
        }
        froschfeld.add(frog);
        if (hasChains()) {
            froschfeld.remove(frog);
            return false;
        }
        return true;
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

    // Check if there are chains of 3 individual connections between frogs
    private boolean hasChains() {
        Set<Position> visited = new HashSet<>();
        Set<Position> chain = new HashSet<>();
        for (Position frog : froschfeld) {
            if (!visited.contains(frog)) {
                chain.clear();
                if (dfs(frog, visited, chain)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(Position frog, Set<Position> visited, Set<Position> chain) {
        visited.add(frog);
        chain.add(frog);

        for (Position neighbor : getNeighbors(frog)) {
            if (!visited.contains(neighbor)) {
                if (dfs(neighbor, visited, chain)) {
                    return true;
                }
            } else if (chain.size() >= 3 && chain.contains(neighbor)) {
                return true;
            }
        }

        chain.remove(frog);
        return false;
    }

    private List<Position> getNeighbors(Position frog) {
        List<Position> neighbors = new ArrayList<>();
        List<AbstractMap.SimpleEntry<Integer, Integer>> adjacent = frog.y() % 2 == 0 ? adjacentGerade : adjacentUngerade;
        for (AbstractMap.SimpleEntry<Integer, Integer> addend : adjacent) {
            Position neighbor = new Position(Color.None, frog.x() + addend.getKey(), frog.y() + addend.getValue(), frog.border());
            for (Position actualFrog : froschfeld) {
                if (actualFrog.x() == neighbor.x() && actualFrog.y() == neighbor.y()) {
                    neighbors.add(actualFrog);
                }
            }
        }
        return neighbors;
    }
    // returns a position where a frog creates a chain
    public Position getChainPlacement() {
        Set<Position> visited = new HashSet<>();
        Set<Position> chain = new HashSet<>();
        for (Position frog : froschfeld) {
            if (!visited.contains(frog)) {
                chain.clear();
                if (dfs(frog, visited, chain)) {
                    for (Position neighbor : getNeighbors(frog)) {
                        if (!chain.contains(neighbor)) {
                            return neighbor;
                        }
                    }
                }
            }
        }
        return null;
    }

    public void selectFrog(Position position) {
        froschfeld.remove(position);
        selectedFrog = position;
    }
    public boolean isFrogSelected() {
        return selectedFrog != null;
    }


    private List<Position> getJumpablePositions() {
        List<Position> jumpablePositions = new ArrayList<>();
        for (int i = 0; i < adjacentGerade.size(); i++) {
            Position jumpPosition = searchJumpLoop(i, 0, selectedFrog);
            if (jumpPosition == null) {
                continue;
            }
            jumpablePositions.add(jumpPosition);
        }
        return jumpablePositions;
    }

    private Position searchJumpLoop(int direction, int jumpDistance, Position oldJumpPosition) {
        AbstractMap.SimpleEntry<Integer, Integer> addend = oldJumpPosition.y() % 2 == 0 ? adjacentGerade.get(direction) : adjacentUngerade.get(direction);
        Position jumpPosition = new Position(Color.None, oldJumpPosition.x() + addend.getKey(), oldJumpPosition.y() + addend.getValue(), Color.None);
        for (Position einzFeld : froschfeld) {
            if (jumpPosition.x() == einzFeld.x() && jumpPosition.y() == einzFeld.y()) {
                jumpPosition = searchJumpLoop(direction, jumpDistance + 1, jumpPosition);
                break;
            }
        }
        if (jumpDistance < 1) {
            return null;
        }
        return jumpPosition;
    }
}