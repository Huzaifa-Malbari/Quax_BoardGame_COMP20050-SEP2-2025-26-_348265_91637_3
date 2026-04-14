package hio.qss;

import java.util.ArrayList;

public class SimpleStrategy implements Strategy{

    private boolean isBlack;
    private ArrayList<ArrayList<SearchNode>> paths;
    private SearchNode nextMove;

    public ArrayList<ArrayList<SearchNode>> getPaths() {
        return paths;
    }

    public SearchNode getNextMove() {
        return nextMove;
    }

    public void calculatePaths(GameState state, BoardCell lastMove, BoardCell lastOpponentMove) {

    }

}
