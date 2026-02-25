package hio.qss;

import javax.swing.*;

public class Game {
    private Boolean isBlack;
    private Cell[][] ocells;
    private Cell[][] rcells;

    public Game() {
        isBlack = true;
        ocells = new Cell[11][11];
        rcells = new Cell[11][11];
    }

    public Boolean placeCell(Boolean isRhombic, int row, int col) {

        if (isRhombic) {
            if (rcells[row][col] != null) {
                JOptionPane.showMessageDialog(null,"Cell already occupied");
                return false;
            }
            RawCell cell = new RawCell(true, (isBlack) ? CellStatus.B : CellStatus.W);
            rcells[row][col] = cell;
        }else {
            if (ocells[row][col] != null) {
                JOptionPane.showMessageDialog(null,"Cell already occupied");
                return false;
            }
            RawCell cell = new RawCell(false, (isBlack) ? CellStatus.B : CellStatus.W);
            ocells[row][col] = cell;
        }
        isBlack = !isBlack;
        return true;
    }

    public Boolean isBlack() {
        return isBlack;
    }
}
