
package hio.qss;

import java.util.ArrayList;

public class SimpleStrategy implements Strategy {
  private ArrayList<ArrayList<SearchNode>> paths;

  public SimpleStrategy() {}

  public ArrayList<ArrayList<SearchNode>> getPaths() {
    return paths;
  }

  public SearchNode getNextMove() {
    ArrayList<SearchNode> shortestPath = null;
    for (ArrayList<SearchNode> currPath : paths) {
      if (shortestPath == null || currPath.size() < shortestPath.size()) {
        shortestPath = currPath;
      }
    }
    return shortestPath.getFirst();
  }

  public void calculatePaths(GameState state, BoardCell lastMove, BoardCell lastOpponentMove) {
    paths = new ArrayList<ArrayList<SearchNode>>();

    if (lastMove == null) {
      paths.add(new ArrayList<>());
      paths.get(0).add(new SearchNode(getNextFreeCell(state)));
      return;
    }

    BoardCell furthest = null;
    for (BoardCell neighbour : lastMove.getNeighbours(state)) {
      BoardCell neighbourBoardCell = new BoardCell(neighbour.getRhombic(),
          (state.isBlack()) ? CellStatus.B : CellStatus.W, neighbour.getRow(), neighbour.getCol());
      if (neighbour.getStatus().equals(CellStatus.Free)) {
        if (furthest == null || furthest.isFurther(neighbourBoardCell)) {
          furthest = neighbourBoardCell;
        }
      }
    }

    if (furthest == null) {
      System.out.println("No neighbours found");
      furthest = getNextFreeCell(state);
    }

    paths.add(new ArrayList<>());
    paths.get(0).add(new SearchNode(furthest));
  }

  private BoardCell getNextFreeCell(GameState state) {
    for (int i = 0; i < state.ocells().length; i++) {
      for (int j = 0; j < state.ocells().length; j++) {
        if (!state.isBlack()) {
          int temp = i;
          i = j;
          j = temp;
        }

        if (state.ocells()[i][j].getStatus().equals(CellStatus.Free)) {
          return state.ocells()[i][j];
        }

        if (!state.isBlack()) {
          int temp = i;
          i = j;
          j = temp;
        }
      }
    }

    for (int i = 0; i < state.rcells().length; i++) {
      for (int j = 0; j < state.rcells().length; j++) {
        if (state.rcells()[i][j].getStatus().equals(CellStatus.Free)) {
          return state.rcells()[i][j];
        }
      }
    }

    return null;
  }

  @Override
  public ArrayList<SearchNode> getChosenPath() {
    return paths.getFirst();
  }
}
