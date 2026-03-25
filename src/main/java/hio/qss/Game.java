package hio.qss;

import javax.swing.*;
import java.lang.classfile.constantpool.LoadableConstantEntry;
import java.util.ArrayList;

public class Game {

    private static int MAX_RHOMBIS = 10;
    private static int MAX_OCTAGONS = 11;

    private boolean isBlack;
    private boolean blackWins;
    private boolean gameOver;

    private BoardCell[][] ocells;
    private BoardCell[][] rcells;


    public Game() {
        isBlack = true;
        ocells = new BoardCell[11][11];
        rcells = new BoardCell[10][10];
        blackWins = false;
        gameOver = false;
    }

    public boolean placeCell(Boolean isRhombic, int row, int col) {

        if (isRhombic) {
            if (rcells[row][col] != null) {
                return false;
            }
            BoardCell cell = new BoardCell(true, (isBlack) ? CellStatus.B : CellStatus.W, row, col);
            rcells[row][col] = cell;
            updateChains(row, col, true);
        }else {
            if (ocells[row][col] != null) {
                return false;
            }
            BoardCell cell = new BoardCell(false, (isBlack) ? CellStatus.B : CellStatus.W, row, col);
            ocells[row][col] = cell;
            updateChains(row, col, false);
        }
        isBlack = !isBlack;
        return true;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isBlackWins() {
        return blackWins;
    }

    private void updateChains(int row, int col, boolean isRhombic) {

        ArrayList<BoardCell> neighbours = getNeighbours(row, col, isRhombic);

        BoardCell thisCell = ocells[row][col];
        if (isRhombic) {
            thisCell = rcells[row][col];
        }

        for (BoardCell cell : neighbours) {
            if (cell != null) {
                thisCell.setFurthestCell(cell);
            }
        }
        BoardCell thisFurthest = thisCell.getFurthestCell();
        for (BoardCell cell : neighbours) {
            if (cell != null && (cell.getFurthestCell() != thisCell.getFurthestCell())) {
                cell.setFurthestCell(thisFurthest);
            }
        }

        checkForWin();

        System.out.println(thisCell.toString() + "'s furthest: " + thisCell.getFurthestCell().toString());

    }

    private void checkForWin() {

       if (isBlack) {
           for (int i = 0; i < MAX_OCTAGONS; i++) {
               if (ocells[0][i] == null) {
                   continue;
               }
               if (ocells[0][i].getFurthestCell().getRow() == MAX_OCTAGONS - 1) {
                   gameOver = true;
                   blackWins = true;
                   return;
               }
           }
       }else {
           for (int i = 0; i < MAX_OCTAGONS; i++) {
               if (ocells[i][0] == null) {
                   continue;
               }
               if (ocells[i][0].getFurthestCell().getCol() == MAX_OCTAGONS - 1) {
                   gameOver = true;
                   blackWins = false;
                   return;
               }
           }
       }

    }

    public ArrayList<BoardCell> getNeighbours(int row, int col, boolean isRhombic) {

        BoardCell thisCell = ocells[row][col];
        ArrayList<BoardCell> neighbours = new ArrayList<>();
        if (isRhombic) {
            thisCell  = rcells[row][col];

            neighbours.add(ocells[row][col]);
            neighbours.add(ocells[row][col + 1]);
            neighbours.add(ocells[row + 1][col]);
            neighbours.add(ocells[row + 1][col + 1]);

        } else {

            // check neighboring octagons
            if (row - 1 >= 0) {
                neighbours.add(ocells[row - 1][col]);
            }
            if (row + 1 < MAX_OCTAGONS) {
                neighbours.add(ocells[row + 1][col]);
            }
            if (col - 1 >= 0) {
                neighbours.add(ocells[row][col - 1]);
            }
            if (col + 1 < MAX_OCTAGONS) {
                neighbours.add(ocells[row][col + 1]);
            }

            // check neighboring rhombises

            if (col - 1 >= 0) {
                if (row < MAX_RHOMBIS) {
                    neighbours.add(rcells[row][col - 1]);
                }
                if (row - 1 >= 0) {
                    neighbours.add(rcells[row - 1][col - 1]);
                }
            }
            if (col < MAX_RHOMBIS) {
                if (row < MAX_RHOMBIS) {
                    neighbours.add(rcells[row][col]);
                }
                if (row - 1 >= 0) {
                    neighbours.add(rcells[row - 1][col]);
                }

            }
        }

        return neighbours;

    }

}
