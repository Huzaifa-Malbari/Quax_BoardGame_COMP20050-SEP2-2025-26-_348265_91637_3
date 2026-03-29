package hio.qss;

import javax.swing.*;
import java.util.ArrayList;

public class Game {

    private static int MAX_RHOMBIS = 10;
    private static int MAX_OCTAGONS = 11;

    private boolean isBlack;
    private boolean blackWins;
    private boolean gameOver;

    private BoardCell[][] ocells;
    private BoardCell[][] rcells;
    private ArrayList<CellGroup> cellGroups;


    public Game() {
        isBlack = true;
        ocells = new BoardCell[11][11];
        rcells = new BoardCell[10][10];
        blackWins = false;
        gameOver = false;
        cellGroups = new ArrayList<CellGroup>();
    }

    public boolean placeCell(Boolean isRhombic, int row, int col) {

        if (isRhombic) {
            if (rcells[row][col] != null) {
                return false;
            }
            BoardCell cell = createCell(row, col, isRhombic);
            rcells[row][col] = cell;
            updateChains(row, col, true);
        }else {
            if (ocells[row][col] != null) {
                return false;
            }
            BoardCell cell = createCell(row, col, isRhombic);
            ocells[row][col] = cell;
            updateChains(row, col, false);
        }
        isBlack = !isBlack;
        return true;
    }

    private BoardCell createCell(int row, int col, boolean isRhombic) {
        BoardCell cell;
        if (isRhombic) {
            cell = new BoardCell(true, (isBlack) ? CellStatus.B : CellStatus.W, row, col);
        }else {
            cell = new BoardCell(false, (isBlack) ? CellStatus.B : CellStatus.W, row, col);
        }



        return cell;
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

        BoardCell thisCell = ocells[row][col];
        if (isRhombic) {
            thisCell = rcells[row][col];
        }

        ArrayList<BoardCell> neighbours = getNeighbours(row, col, isRhombic);
        ArrayList<CellGroup> groups = new ArrayList<CellGroup>();
        CellGroup maxGroup = new CellGroup();
        for (BoardCell neighbour : neighbours) {
            if (neighbour != null && neighbour.getStatus().equals(thisCell.getStatus()) && !groups.contains(neighbour.getGroup())) {
                groups.add(neighbour.getGroup());
                if (groups.size() > 0 && groups.getLast().size() > maxGroup.size()) {
                    maxGroup = groups.getLast();
                }
            }
        }

        if (maxGroup.size() != 0) {
            groups.remove(maxGroup);
        }
        for (CellGroup group : groups) {
            maxGroup.merge(group);
        }

        maxGroup.addCell(thisCell);
        maxGroup.setFurthest(thisCell);

        checkForWin();

        System.out.println(thisCell.toString() + "'s furthest: " + thisCell.getGroup().getFurthest());

    }

    private void checkForWin() {

       if (isBlack) {
           for (int i = 0; i < MAX_OCTAGONS; i++) {
               if (ocells[0][i] == null || ocells[0][i].getStatus().equals(CellStatus.W)) {
                   continue;
               }
               if (ocells[0][i].getGroup().getFurthest().getRow() == MAX_OCTAGONS - 1) {
                   gameOver = true;
                   blackWins = true;
                   break;
               }
           }
       }else {
           for (int i = 0; i < MAX_OCTAGONS; i++) {
               if (ocells[i][0] == null || ocells[i][0].getStatus().equals(CellStatus.B)) {
                   continue;
               }
               if (ocells[i][0].getGroup().getFurthest().getCol() == MAX_OCTAGONS - 1) {
                   gameOver = true;
                   blackWins = false;
                   break;
               }
           }
       }
       if (gameOver) {
           System.out.println((blackWins) ? "Black wins" : "White wins");
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
