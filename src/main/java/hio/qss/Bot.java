package hio.qss;

import java.util.ArrayList;

public class Bot {
    private BoardCell lastMove;
    private BoardCell lastOpponentMove;
    private boolean isBlack;
    private Strategy strategy;

    public Bot(Strategy strategy) {
        lastMove = null;
        lastOpponentMove = null;
        isBlack = true;
        this.strategy = strategy;
    }

    public ArrayList<ArrayList<SearchNode>> getPaths() {
        return strategy.getPaths();
    }

    public void calculatePaths(GameState state) {
        strategy.calculatePaths(state, lastMove, lastOpponentMove);
    }

    public BoardCell getNextMove() {
        return strategy.getNextMove();
    }

    public boolean isBlack() {
        return isBlack;
    }

    public BoardCell getLastMove() {
        return lastMove;
    }

    public void setLastMove(BoardCell lastMove) {
        this.lastMove = lastMove;
    }

    public BoardCell getLastOpponentMove() {
        return lastOpponentMove;
    }

    public void setLastOpponentMove(BoardCell lastOpponentMove) {
        this.lastOpponentMove = lastOpponentMove;
    }

    public void setBlack(boolean black) {
        isBlack = black;
    }
}
