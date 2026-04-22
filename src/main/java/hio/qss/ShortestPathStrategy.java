package hio.qss;

import java.util.ArrayList;

public class ShortestPathStrategy implements Strategy{
    private ArrayList<ArrayList<SearchNode>> paths;
    private ArrayList<SearchNode> shortestPath;

    @Override
    public ArrayList<ArrayList<SearchNode>> getPaths() {
        return paths;
    }

    @Override
    public SearchNode getNextMove() {
        return shortestPath.getFirst();
    }

    @Override
    public void calculatePaths(GameState state, BoardCell lastMove, BoardCell lastOpponentMove) {
        paths = new ArrayList<ArrayList<SearchNode>>();
        shortestPath = null;

        if (lastMove == null) {
            ArrayList<SearchNode> path =  new ArrayList<>();
            path.add(new SearchNode(getNextFreeCell(state)));
            addPath(path);
            return;
        }

        addNeighbourPaths(state, lastMove);

        if (paths.size() != 0) {
            return;
        }

        for (BoardCell cell : lastMove.getGroup().getCells()) {
            addNeighbourPaths(state, cell);
        }

        if (paths.size() != 0) {
            return;
        }

//        System.out.println("No neighbours found");

        ArrayList<SearchNode> path =  new ArrayList<>();
        path.add(new SearchNode(getNextFreeCell(state)));
        addPath(path);
    }

    private void addNeighbourPaths(GameState state, BoardCell lastMove) {

        for (BoardCell neighbour : lastMove.getNeighbours(state)) {
            if (!neighbour.getStatus().equals(CellStatus.Free)) {
                continue;
            }
//            BoardCell neighbourBoardCell = new BoardCell(neighbour.getRhombic(), lastMove.getStatus(),
//                    neighbour.getRow(), neighbour.getCol());
//            if (!lastMove.isFurther(neighbourBoardCell)) {
//                continue;
//            }
            SearchNode start = new SearchNode(neighbour);
            for (int i = 0; i < Game.MAX_OCTAGONS; i++) {
//            int significantCoordinate = (state.isBlack()) ? neighbour.getCol() : neighbour.getRow();
//            for (int i = significantCoordinate; i <= significantCoordinate; i++) {

                SearchNode end;
                if (state.isBlack()) {
                    end = new SearchNode(state.ocells()[Game.MAX_OCTAGONS - 1][i]);
                }else {
                    end = new SearchNode(state.ocells()[i][Game.MAX_OCTAGONS - 1]);
                }

//                System.out.println("Shortest path between " + start + "and " + end);
                ArrayList<SearchNode> path = getShortestPathBetween(state, start, end);
                addPath(path);

            }
        }

    }

    private void addPath(ArrayList<SearchNode> path) {

        if (path.size() == 0) {
            return;
        }

        paths.add(path);

        if (shortestPath == null || path.size() < shortestPath.size()
                || path.size() == shortestPath.size() && path.getFirst().getRhombic()) {
            shortestPath = path;
        }

    }

    private ArrayList<SearchNode> getShortestPathBetween(GameState state, SearchNode start, SearchNode end) {

        ArrayList<SearchNode> path = new ArrayList<SearchNode>();

        ArrayList<SearchNode> toSearch = new ArrayList<SearchNode>();
        toSearch.add(start);
        ArrayList<SearchNode> processed = new ArrayList<SearchNode>();

        while (!toSearch.isEmpty()) {

            SearchNode curr = toSearch.getFirst();
            for (SearchNode node : toSearch) {
                if (node.getF() < curr.getF() || node.getF() == curr.getF() && node.getH() < curr.getH()) {
                    curr = node;
                }
            }

            toSearch.remove(curr);
            processed.add(curr);

            if (curr.equals(end)) {

                SearchNode currentNode = curr;
                while (!currentNode.equals(start)) {
                    path.addFirst(currentNode);
                    currentNode = currentNode.getConnection();
                }
                path.addFirst(currentNode);

                return path;
            }


            ArrayList<BoardCell> neighbours = curr.getNeighbours(state);
            for (BoardCell neighbour : neighbours) {

                if (!(neighbour.getStatus().equals(CellStatus.Free) && !processed.contains(neighbour))) {
                    continue;
                }

                boolean inSearch = toSearch.contains(neighbour);
                int costToNeighbour = curr.getG() + SearchNode.calculateDistance(curr, neighbour);

                SearchNode searchNeighbour = new SearchNode(neighbour);
                if (inSearch) {
                    searchNeighbour = toSearch.get(toSearch.indexOf(neighbour));
                }


                if (!inSearch || costToNeighbour < searchNeighbour.getG()) {
                    searchNeighbour.setG(costToNeighbour);
                    searchNeighbour.setConnection(curr);

                    if (!inSearch) {
                        searchNeighbour.setH(SearchNode.calculateDistance(searchNeighbour, end));
                        toSearch.add(searchNeighbour);
                    }
                }

            }

        }

        return path;
    }


    private BoardCell getNextFreeCell(GameState state) {

        for (int i = 0; i < state.ocells().length; i++) {
            for (int j = 0; j < state.ocells().length; j++) {

                if (!state.isBlack()) {
                    int temp = i;
                    i = j;
                    j = temp;
                }

                if (state.ocells()[i][j].getStatus().equals(CellStatus.Free)){
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
                if (state.rcells()[i][j].getStatus().equals(CellStatus.Free)){
                    return state.rcells()[i][j];
                }

            }
        }

        return null;
    }

    @Override
    public ArrayList<SearchNode> getChosenPath() {
        return shortestPath;
    }

}
