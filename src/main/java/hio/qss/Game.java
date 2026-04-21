package hio.qss;

import javax.swing.*;
import java.util.ArrayList;

public class Game {

    public static int MAX_RHOMBIS = 10;
    public static int MAX_OCTAGONS = 11;

    private boolean isBlack;
    private boolean blackWins;
    private boolean gameOver;

    private BoardCell[][] ocells;
    private BoardCell[][] rcells;
    private int moveCount; // added by Ioan

    private Bot bot;

    public Game() {
        isBlack = true;
        ocells = new BoardCell[11][11];
        rcells = new BoardCell[10][10];
        blackWins = false;
        gameOver = false;
        moveCount = 0;
//        bot = new Bot(new SimpleStrategy());
        bot = new Bot(new ShortestPathStrategy());

        initialiseBoard();
    }

    private void initialiseBoard() {
        for (int i = 0; i < MAX_OCTAGONS; i++) {
            for (int j = 0; j < MAX_OCTAGONS; j++) {
                ocells[i][j] = new BoardCell(false, CellStatus.Free, i, j);
            }
        }
        for (int i = 0; i < MAX_RHOMBIS; i++) {
            for (int j = 0; j < MAX_RHOMBIS; j++) {
                rcells[i][j] = new BoardCell(true, CellStatus.Free, i, j);
            }
        }
    }

    public boolean placeCell(Boolean isRhombic, int row, int col) {

        if (isRhombic) {
            if (!rcells[row][col].getStatus().equals(CellStatus.Free) || gameOver) {
                return false;
            }
            BoardCell cell = createCell(row, col, isRhombic);
            rcells[row][col] = cell;
            updateChains(cell);
        }else {
            if (!ocells[row][col].getStatus().equals(CellStatus.Free) || gameOver) {
                return false;
            }
            BoardCell cell = createCell(row, col, isRhombic);
            ocells[row][col] = cell;
            updateChains(cell);
        }
        moveCount++;
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

    private BoardCell createCell(BoardCell cell) {
        return createCell(cell.getRow(), cell.getCol(), cell.getRhombic());
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

    private void updateChains(BoardCell thisCell) {

        int row = thisCell.getRow();
        int col = thisCell.getCol();
        boolean isRhombic = thisCell.getRhombic();

        if (isRhombic) {
            thisCell = rcells[row][col];
        }

        ArrayList<BoardCell> neighbours = thisCell.getNeighbours(new GameState(ocells, rcells, isBlack));
        ArrayList<CellGroup> groups = new ArrayList<CellGroup>();
        CellGroup maxGroup = new CellGroup();
        for (BoardCell neighbour : neighbours) {
            if (neighbour.getStatus().equals(thisCell.getStatus()) && !groups.contains(neighbour.getGroup())) {
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
    }

    private void checkForWin() {

       if (isBlack) {
           for (int i = 0; i < MAX_OCTAGONS; i++) {
               if (!ocells[0][i].getStatus().equals(CellStatus.B)) {
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
               if (!ocells[i][0].getStatus().equals(CellStatus.W)) {
                   continue;
               }
               if (ocells[i][0].getGroup().getFurthest().getCol() == MAX_OCTAGONS - 1) {
                   gameOver = true;
                   blackWins = false;
                   break;
               }
           }
       }
    }


    public Bot getBot() {
        return bot;
    }

    public void setBot(Bot bot) {
        this.bot = bot;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public GameState getState() {
        return new GameState(ocells, rcells, isBlack);
    }

    public BoardCell getBoardCellWithID(String id) {
        Boolean isRhombic = (id.charAt(0) == 'O') ? false : true;
        String[] tockens = id.substring(1).split("_");
        int row = Integer.valueOf(tockens[0]);
        int col = Integer.valueOf(tockens[1]);
        if (isRhombic) {
            return rcells[row][col];
        }else {
            return ocells[row][col];
        }
    }

}
