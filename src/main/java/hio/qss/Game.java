package hio.qss;

import javax.swing.*;

public class Game {
  private Boolean isBlack;
  private Cell[][] ocells;
  private Cell[][] rcells;
  private int moveCount; // added by Ioan

  public Game() {
    isBlack = true;
    ocells = new Cell[11][11];
    rcells = new Cell[10][10];
    moveCount = 0;
  }

  public Boolean placeCell(Boolean isRhombic, int row, int col) {

    if (isRhombic) {
      if (rcells[row][col] != null) {
        return false;
      }
      RawCell cell = new RawCell(true, (isBlack) ? CellStatus.B : CellStatus.W);
      rcells[row][col] = cell;
    } else {
      if (ocells[row][col] != null) {
        return false;
      }
      RawCell cell = new RawCell(false, (isBlack) ? CellStatus.B : CellStatus.W);
      ocells[row][col] = cell;
    }
    moveCount++;
    isBlack = !isBlack;
    return true;
  }

  public Boolean isBlack() {
    return isBlack;
  }

  public int getMoveCount() {
    return moveCount;
  }
}
