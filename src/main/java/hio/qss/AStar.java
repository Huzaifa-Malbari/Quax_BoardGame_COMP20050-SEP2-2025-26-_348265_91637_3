
package hio.qss;

import java.util.ArrayList;

public class AStar {
  private static ArrayList<SearchNode> toSearch;
  private static ArrayList<SearchNode> processed;
  private static SearchNode start;
  private static SearchNode end;

  public static ArrayList<SearchNode> getShortestPathBetween(
      GameState state, SearchNode start, SearchNode end) {
    toSearch = new ArrayList<SearchNode>();
    toSearch.add(start);
    processed = new ArrayList<SearchNode>();
    AStar.start = start;
    AStar.end = end;

    while (!toSearch.isEmpty()) {
      SearchNode nextBestNode = getNextBestNode();
      if (nextBestNode.equals(end)) {
        return returnPath(start, nextBestNode);
      }
      updateNeighbourCosts(state, nextBestNode);
    }

    return new ArrayList<SearchNode>();
  }

  private static SearchNode getNextBestNode() {
    SearchNode curr = toSearch.getFirst();
    for (SearchNode node : toSearch) {
      if (node.getF() < curr.getF() || node.getF() == curr.getF() && node.getH() < curr.getH()) {
        curr = node;
      }
    }
    toSearch.remove(curr);
    processed.add(curr);

    return curr;
  }

  private static void updateNeighbourCosts(GameState gameState, SearchNode curr) {
    ArrayList<BoardCell> neighbours = curr.getNeighbours(gameState);
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

  private static ArrayList<SearchNode> returnPath(SearchNode start, SearchNode end) {
    ArrayList<SearchNode> path = new ArrayList<SearchNode>();
    SearchNode currentNode = end;
    while (!currentNode.equals(start)) {
      path.addFirst(currentNode);
      currentNode = currentNode.getConnection();
    }
    path.addFirst(currentNode);

    return path;
  }
}
