package de.fhkiel.tsw;

import de.fhkiel.tsw.armyoffrogs.Color;
import de.fhkiel.tsw.armyoffrogs.Position;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Spielfeld class.
 */
public class Spielfeld {
  Logger logger = Logger.getLogger(Spielfeld.class.getName());
  private final List<AbstractMap.SimpleEntry<Integer, Integer>> adjacentGerade = Arrays.asList(
      new AbstractMap.SimpleEntry<>(-1, 0),  // left
      new AbstractMap.SimpleEntry<>(1, 0),   // right
      new AbstractMap.SimpleEntry<>(0, -1),  // top-right
      new AbstractMap.SimpleEntry<>(0, 1),   // bottom-right
      new AbstractMap.SimpleEntry<>(-1, -1), // top-left
      new AbstractMap.SimpleEntry<>(-1, 1)   // bottom-left
  );
  private final List<AbstractMap.SimpleEntry<Integer, Integer>> adjacentUngerade = Arrays.asList(
      new AbstractMap.SimpleEntry<>(-1, 0),  // left
      new AbstractMap.SimpleEntry<>(1, 0),   // right
      new AbstractMap.SimpleEntry<>(1, -1),  // top-right
      new AbstractMap.SimpleEntry<>(1, 1),   // bottom-right
      new AbstractMap.SimpleEntry<>(0, -1),  // top-left
      new AbstractMap.SimpleEntry<>(0, 1)    // bottom-left
  );
  private boolean keineKetten;
  private Gamelogic gamelogic;
  private Set<Position> froschfeld = new HashSet<>();
  private Position selectedFrog = null;

  public Spielfeld(Gamelogic gamelogic) {
    this.gamelogic = gamelogic;
  }

  public Spielfeld(Set<Position> board, Gamelogic gamelogic) {
    this.gamelogic = gamelogic;
    froschfeld = board;
  }

  public Set<Position> getFroschfeld() {
    return froschfeld;
  }

  public boolean getKeineKetten() {
    return keineKetten;
  }

  public void setKeineKetten(boolean keineKetten) {
    this.keineKetten = keineKetten;
  }


  /**
   * Place a frog on the board.
   *
   * @param toBePlacedFrog .
   *
   * @return .
   */
  public boolean froschSetzen(Position toBePlacedFrog) {
    // For placing frogs
    if (froschfeld.isEmpty()) {
      froschfeld.add(toBePlacedFrog);
      return true;
    }

    if (isAdjacent(toBePlacedFrog, true)) {
      logger.info("Frosch ist neben einem Frosch mit der gleichen Farbe");
      gamelogic.infoString = "Frosch ist neben einem Frosch mit der gleichen Farbe";
      return false;
    }
    // General placability check
    return testPlacability(toBePlacedFrog);
  }

  /**
   * Move a frog on the board.
   *
   * @param jumpingPosition .
   *
   * @return .
   */
  public boolean froschBewegen(Position jumpingPosition) {
    if (jumpingPosition.equals(selectedFrog)) {
      selectedFrog = null;
      return true;
    }

    if (!getJumpablePositions().contains(jumpingPosition)) {
      return false;
    }
    Position newFrogPosition =
        new Position(selectedFrog.frog(), jumpingPosition.x(), jumpingPosition.y(),
            jumpingPosition.border());
    boolean jumped = testPlacability(newFrogPosition);
    if (jumped) {
      froschfeld.remove(selectedFrog);
      selectedFrog = newFrogPosition;
    }
    return jumped;
  }

  /**
   * Check if a frog is movable.
   *
   * @param frog .
   *
   * @return .
   */
  public boolean isFrogMovable(Position frog) {
    if (frog.frog() != Color.None && frog.frog() != Color.Black) {
      Position saveFrog = selectedFrog;
      selectedFrog = frog;
      if (!getJumpablePositions().isEmpty()) {
        selectedFrog = saveFrog;
        return true;
      }
      selectedFrog = saveFrog;
      return false;
    }
    return false;
  }

  private boolean testPlacability(Position frog) {
    if (froschfeld.contains(frog)) {
      gamelogic.infoString = "Diesen Frosch gibt es schon auf dem Spielfeld";
      return false;
    }
    if (!isAdjacent(frog, false)) {
      gamelogic.infoString = "Frosch ist nicht neben einem Frosch anderen Frosch platziert worden";
      return false;
    }
    froschfeld.add(frog);
    if (hasChain()) {
      gamelogic.infoString = "Es gibt eine Kette von mindestens 3 Froschsteinen";
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

  private boolean testAdjacency(Position position,
                                List<AbstractMap.SimpleEntry<Integer, Integer>> adjacentAddend) {
    return adjacentAddend.stream().anyMatch(p -> {
      for (Position pos : froschfeld) {
        if (pos.x() == position.x() + p.getKey() && pos.y() == position.y() + p.getValue()) {
          return true;
        }
      }
      return false;
    });
  }

  private boolean testColorAdjacency(Position position,
                                     List<AbstractMap.SimpleEntry<Integer,
                                         Integer>> adjacentAddend) {
    return adjacentAddend.stream().anyMatch(p -> froschfeld.contains(
        new Position(position.frog(), position.x() + p.getKey(), position.y() + p.getValue(),
            position.border())));
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

      for (AbstractMap.SimpleEntry<Integer, Integer> addend : placedFrog.y() % 2 == 0
          ? adjacentGerade : adjacentUngerade) {
        Position placement = new Position(placedFrog.frog(), placedFrog.x() + addend.getKey(),
            placedFrog.y() + addend.getValue(), placedFrog.border());
        if (!froschfeld.contains(placement)) {
          availablePositions.add(new AbstractMap.SimpleEntry<>(placement.x(), placement.y()));
        }
      }
    }
    return availablePositions;
  }

  /**
   * Get all possible placements.
   *
   * @param anlegeFarbe .
   *
   * @return .
   *
   */
  public List<Position> getPlacements(Color anlegeFarbe) {
    List<Position> allPlacements = new ArrayList<>();
    for (AbstractMap.SimpleEntry<Integer, Integer> freePos : testAvailablePlacingPositions()) {
      allPlacements.add(
          new Position(anlegeFarbe, freePos.getKey(), freePos.getValue(), Color.None));
    }
    allPlacements.removeIf(
        pos -> testColorAdjacency(pos, pos.y() % 2 == 0 ? adjacentGerade : adjacentUngerade));
    return allPlacements;
  }

  // Check if there are chains of 3 individual connections between frogs
  private boolean hasChain() {
    Set<Position> visited = new HashSet<>();
    LinkedList<Position> chain = new LinkedList<>();
    for (Position frog : froschfeld) {
      if (!visited.contains(frog)) {
        chain.clear();
        if (dfs(frog, visited, chain, 4)) {
          keineKetten = false;
          return true;
        }
      }
    }
    keineKetten = true;
    return false;
  }

  private List<Position> getNeighbors(Position frog) {
    List<Position> neighbors = new ArrayList<>();
    List<AbstractMap.SimpleEntry<Integer, Integer>> adjacent =
        frog.y() % 2 == 0 ? adjacentGerade : adjacentUngerade;
    for (AbstractMap.SimpleEntry<Integer, Integer> addend : adjacent) {
      Position neighbor =
          new Position(Color.None, frog.x() + addend.getKey(), frog.y() + addend.getValue(),
              frog.border());
      for (Position actualFrog : froschfeld) {
        if (actualFrog.x() == neighbor.x() && actualFrog.y() == neighbor.y()) {
          neighbors.add(actualFrog);
          break;
        }
      }
    }
    return neighbors;
  }

  /**
   * Get the position of a chain.
   *
   * @return .
   *
   */

  public Position getChainPlacement() {
    Set<Position> visited = new HashSet<>();
    LinkedList<Position> chain = new LinkedList<>();
    for (Position frog : froschfeld) {
      if (!visited.contains(frog)) {
        chain.clear();
        if (dfs(frog, visited, chain, 3)) {
          Position chainPosition = findChainPosition(chain);
          if (chainPosition != null) {
            return chainPosition;
          }
        }
      }
    }
    return null;
  }

  private Position findChainPosition(LinkedList<Position> chain) {
    for (Position neighbor : chain) {
      if (getNeighbors(neighbor).size() == 1) {
        Position chainPosition = getChainPositionForNeighbor(neighbor);
        if (chainPosition != null) {
          return chainPosition;
        }
      }
    }
    return null;
  }

  private Position getChainPositionForNeighbor(Position neighbor) {
    List<AbstractMap.SimpleEntry<Integer, Integer>> addends
        = neighbor.y() % 2 == 0 ? adjacentGerade : adjacentUngerade;
    for (AbstractMap.SimpleEntry<Integer, Integer> addend : addends) {
      Position chainPosition = new Position(Color.None, neighbor.x() + addend.getKey(),
          neighbor.y() + addend.getValue(), Color.None);
      if (getNeighbors(chainPosition).size() == 1) {
        return chainPosition;
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
      Position jumpPosition = searchJumpLoop(i, 0, selectedFrog).getKey();
      if (jumpPosition == null) {
        continue;
      }
      jumpablePositions.add(jumpPosition);
    }
    return jumpablePositions;
  }

  /**
   * Get the position of a jumpable frog.
   *
   * @return .
   *
   */
  public Position getJumpablePosition() {
    List<Position> jumpablePositions = getJumpablePositions();
    if (jumpablePositions.isEmpty()) {
      return null;
    }
    return jumpablePositions.get(0);
  }

  private AbstractMap.SimpleEntry<Position, Integer> searchJumpLoop(int direction, int jumpDistance,
                                                                    Position oldJumpPosition) {
    AbstractMap.SimpleEntry<Integer, Integer> addend =
        oldJumpPosition.y() % 2 == 0 ? adjacentGerade.get(direction) :
            adjacentUngerade.get(direction);
    Position jumpPosition = new Position(Color.None, oldJumpPosition.x() + addend.getKey(),
        oldJumpPosition.y() + addend.getValue(), Color.None);
    for (Position einzFeld : froschfeld) {
      if (jumpPosition.x() == einzFeld.x() && jumpPosition.y() == einzFeld.y()) {
        AbstractMap.SimpleEntry<Position, Integer> result =
            searchJumpLoop(direction, jumpDistance + 1, jumpPosition);
        jumpPosition = result.getKey();
        jumpDistance = result.getValue();
        break;
      }
    }
    if (jumpDistance < 1) {
      return new AbstractMap.SimpleEntry<>(null, jumpDistance);
    }
    return new AbstractMap.SimpleEntry<>(jumpPosition, jumpDistance);
  }

  /**
   * Check if there is an island.
   *
   * @return .
   *
   */
  public boolean hasIsland() {
    if (froschfeld.size() == 1) {
      return false;
    } else {
      for (Position frog : froschfeld) {
        if (getNeighbors(frog).isEmpty()) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Check if there is a chain.
   *
   * @param current .
   *
   * @param visited .
   *
   * @param chain .
   *
   * @param chainLength .
   *
   * @return .
   *
   */
  public boolean dfs(Position current, Set<Position> visited,
                     LinkedList<Position> chain, final int chainLength) {
    visited.add(current);
    chain.add(current);

    // Check if the chain is valid up to this point
    if (chain.size() >= chainLength && isValidChain(chain, chainLength)) {
      return true; // Valid chain found
    }

    // Continue searching only if we haven't reached the desired length
    if (chain.size() < chainLength) {
      for (Position neighbor : getNeighbors(current)) {
        if (!visited.contains(neighbor) && dfs(neighbor, visited, chain, chainLength)) {
          return true; // Successful chain found
        }
      }
    }

    // Backtrack if the path doesn't lead to a solution
    visited.remove(current);
    chain.removeLast(); // Efficiently remove the last element
    return false;
  }

  private boolean isValidChain(LinkedList<Position> chain, int chainLength) {
    if (chain.size() < chainLength) {
      return false; // Ensure the chain has the desired length
    }

    Position first = chain.getFirst();
    Position last = chain.getLast();

    boolean isValidStartOrEnd = isValidStartOrEndPosition(first) && isValidStartOrEndPosition(last)
        && !(getNeighbors(first).size() > 2 && getNeighbors(last).size() > 2);

    if (!isValidStartOrEnd) {
      return false; // Start or end position doesn't meet criteria
    }

    // Check middle positions
    for (int i = 1; i < chain.size() - 1; i++) {
      Position middle = chain.get(i);
      if (getNeighbors(middle).size() != 2) {
        return false; // Middle elements must have exactly 2 neighbors
      }
    }

    return true; // Chain meets all criteria
  }

  private boolean isValidStartOrEndPosition(Position pos) {
    int neighborCount = getNeighbors(pos).size();
    return neighborCount == 1 || neighborCount <= 4;
  }
}
