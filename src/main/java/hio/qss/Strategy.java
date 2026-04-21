package hio.qss;

import java.util.ArrayList;

public interface Strategy {
    public ArrayList<ArrayList<SearchNode>> getPaths();
    public SearchNode getNextMove();
    public void calculatePaths(GameState state, BoardCell lastMove, BoardCell lastOpponentMove);
}
