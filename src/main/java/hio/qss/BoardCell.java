package hio.qss;

import java.util.ArrayList;

public class BoardCell extends RawCell{

    private int row;
    private int col;
    private CellGroup group;

    public BoardCell (Boolean isRhombic, CellStatus status, int row, int col) {
        super(isRhombic, status);
        this.row = row;
        this.col = col;
        group = null;

    }

    public CellGroup getGroup() {
        return group;
    }

    public ArrayList<BoardCell> getCellsInGroup() {
        return group.getCells();
    }

    public BoardCell getFurthestCellInGroup() {
        return group.getFurthest();
    }

    public void setGroup(CellGroup group) {
        this.group = group;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isFurther(BoardCell cell) {
        if (cell.getStatus() != getStatus()) {
            return false;
        }

        int thisFurthestLine = getRow();
        int thatFurthestLine = cell.getRow();
        if (getStatus() == CellStatus.W) {
            thisFurthestLine = getCol();
            thatFurthestLine = cell.getCol();
        }

        if (getRhombic()) {
            return (thatFurthestLine > thisFurthestLine) ? true : false;
        }

        if (cell.getRhombic()) {
            return (thatFurthestLine >= thisFurthestLine) ? true : false;
        }

        return (thatFurthestLine > thisFurthestLine) ? true : false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append((getRhombic()) ? "Rhombus @ " : "Octagon @ ");
        sb.append(getRow() + " ");
        sb.append(getCol() + " Colour: ");
        sb.append((getStatus() == CellStatus.B) ? "Black" : "White");
        return sb.toString();
    }

    public ArrayList<BoardCell> getNeighbours(GameState state) {

        BoardCell[][] ocells = state.ocells();
        BoardCell[][] rcells = state.rcells();
        ArrayList<BoardCell> neighbours = new ArrayList<>();

        if (getRhombic()) {
            neighbours.add(ocells[row][col]);
            neighbours.add(ocells[row][col + 1]);
            neighbours.add(ocells[row + 1][col]);
            neighbours.add(ocells[row + 1][col + 1]);
        } else {
            if (row - 1 >= 0) {
                neighbours.add(ocells[row - 1][col]);
            }
            if (row + 1 < Game.MAX_OCTAGONS) {
                neighbours.add(ocells[row + 1][col]);
            }
            if (col - 1 >= 0) {
                neighbours.add(ocells[row][col - 1]);
            }
            if (col + 1 < Game.MAX_OCTAGONS) {
                neighbours.add(ocells[row][col + 1]);
            }

            if (col - 1 >= 0) {
                if (row < Game.MAX_RHOMBIS) {
                    neighbours.add(rcells[row][col - 1]);
                }
                if (row - 1 >= 0) {
                    neighbours.add(rcells[row - 1][col - 1]);
                }
            }
            if (col < Game.MAX_RHOMBIS) {
                if (row < Game.MAX_RHOMBIS) {
                    neighbours.add(rcells[row][col]);
                }
                if (row - 1 >= 0) {
                    neighbours.add(rcells[row - 1][col]);
                }
            }
        }

        return neighbours;
    }

    public String getAssociatedCellID() {
        StringBuilder id = new StringBuilder();
        id.append(getRhombic() ? "R" : "O");
        id.append(getRow() + "_" + getCol());
        return id.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof BoardCell)) {
            return false;
        }

        BoardCell that = (BoardCell) o;

        if (getAssociatedCellID().equals(that.getAssociatedCellID())) {
            return true;
        }

        return false;
    }

}
