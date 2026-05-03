
package hio.qss;

import java.util.ArrayList;

public class CellGroup {
  private ArrayList<BoardCell> cells;
  private BoardCell furthest;

  public CellGroup() {
    cells = new ArrayList<BoardCell>();
    furthest = null;
  }

  public BoardCell setFurthest(BoardCell newFurthest) {
    if (furthest == null || furthest.isFurther(newFurthest)) {
      BoardCell old = furthest;
      furthest = newFurthest;
      return old;
    }

    return null;
  }

  public BoardCell getFurthest() {
    return furthest;
  }

  public void addCell(BoardCell cell) {
    cells.add(cell);
    cell.setGroup(this);
  }

  public ArrayList<BoardCell> getCells() {
    return cells;
  }

  public void merge(CellGroup other) {
    if (other != this) {
      ArrayList<BoardCell> otherCells = other.getCells();
      while (!otherCells.isEmpty()) {
        addCell(otherCells.removeFirst());
      }

      setFurthest(other.getFurthest());
    }
  }

  public int size() {
    return cells.size();
  }
}
