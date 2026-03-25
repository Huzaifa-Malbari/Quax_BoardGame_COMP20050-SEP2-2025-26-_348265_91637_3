package hio.qss;

public class BoardCell extends RawCell{

    private int row;
    private int col;
    private BoardCell furthestCell;

    public BoardCell (Boolean isRhombic, CellStatus status, int row, int col) {
        super(isRhombic, status);
        this.row = row;
        this.col = col;
        this.furthestCell = this;
    }

    public BoardCell getFurthestCell() {
        if (furthestCell == this) {
            return this;
        }
        furthestCell = resolveFurthest(furthestCell);
        return furthestCell;
    }

    public void setFurthestCell(BoardCell furthestCell) {
        if (furthestCell == null) {
            return;
        }
        BoardCell otherFurtheset = furthestCell.getFurthestCell();
        if (getFurthestCell().isFurther(otherFurtheset)) {
            this.furthestCell = otherFurtheset;
        }
    }

    private BoardCell resolveFurthest(BoardCell cell) {

        if (cell.getFurthestCell() == cell) {
            return cell;
        }

        cell.setFurthestCell(resolveFurthest(cell.getFurthestCell()));
        return cell.getFurthestCell();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isFurther(BoardCell cell) {
        if (cell == null) {
            return false;
        }

        if (cell.getStatus() != getStatus()) {
            return false;
        }

        if (getRow() < cell.getRow() || getCol() < cell.getCol()) {
            return true;
        }else {
            return false;
        }


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

}
