package hio.qss;

import javax.swing.*;
import java.util.ArrayList;

public class Game {

    private static int MAX_RHOMBIS = 10;
    private static int MAX_OCTAGONS = 11;

    private boolean isBlack;
    private boolean gameOver;

    private BoardCell[][] ocells;
    private BoardCell[][] rcells;


    public Game() {
        isBlack = true;
        ocells = new BoardCell[11][11];
        rcells = new BoardCell[10][10];
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

    private void updateChains(int row, int col, boolean isRhombic) {

        BoardCell thisCell  = ocells[row][col];
        ArrayList<BoardCell> toUpdate = new ArrayList<>();

        if (isRhombic) {
            thisCell  = rcells[row][col];

            toUpdate.add(ocells[row][col]);
            toUpdate.add(ocells[row][col + 1]);
            toUpdate.add(ocells[row + 1][col]);
            toUpdate.add(ocells[row + 1][col + 1]);

        } else {

            // check neighboring octagons
            if (row - 1 >= 0) {
                toUpdate.add(ocells[row - 1][col]);
            }
            if (row + 1 < MAX_OCTAGONS) {
                toUpdate.add(ocells[row + 1][col]);
            }
            if (col - 1 >= 0) {
                toUpdate.add(ocells[row][col - 1]);
            }
            if (col + 1 < MAX_OCTAGONS) {
                toUpdate.add(ocells[row][col + 1]);
            }

            // check neighboring rhombises

            if (col - 1 >= 0) {
                if (row < MAX_RHOMBIS) {
                    toUpdate.add(rcells[row][col - 1]);
                }
                if (row - 1 >= 0) {
                    toUpdate.add(rcells[row - 1][col - 1]);
                }
            }
            if (col < MAX_RHOMBIS) {
                if (row < MAX_RHOMBIS) {
                    toUpdate.add(rcells[row][col]);
                }
                if (row - 1 >= 0) {
                    toUpdate.add(rcells[row - 1][col]);
                }

            }
        }

        for (BoardCell cell : toUpdate) {
            if (cell != null) {
                thisCell.setFurthestCell(cell);
                cell.setFurthestCell(thisCell);
            }
        }

        System.out.println(thisCell.toString() + "'s furthest: " + thisCell.getFurthestCell().toString());

    }

}
