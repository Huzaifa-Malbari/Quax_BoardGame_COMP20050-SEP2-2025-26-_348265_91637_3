package hio.qss;

import java.util.ArrayList;

public class SimpleStrategy implements Strategy{

    private ArrayList<ArrayList<SearchNode>> paths;

    public SimpleStrategy () {
    }

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
            paths.add( new ArrayList<>());
            paths.get(0).add(new SearchNode(getNextFreeCell(state)));
            return;
        }

        BoardCell furthest = null;
        for (BoardCell neighbour : lastMove.getNeighbours(state)) {
            if (neighbour.getStatus().equals(CellStatus.Free)) {
                if (furthest == null || furthest.isFurther(neighbour)) {
                    furthest = neighbour;
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
                if (state.ocells()[i][j].getStatus().equals(CellStatus.Free)){
                    return state.ocells()[i][j];
                }

            }
        }

        for (int i = 0; i < state.rcells().length; i++) {
            for (int j = 0; j < state.rcells().length; j++) {
                if (state.rcells()[i][j].getStatus().equals(CellStatus.Free)){
                    paths.add(new ArrayList<>());
                    paths.get(0).add(new SearchNode(state.rcells()[i][j]));
                    return state.rcells()[i][j];
                }

            }
        }

        return null;
    }

}
