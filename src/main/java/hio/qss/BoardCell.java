package hio.qss;

public class BoardCell extends RawCell{

    private int row;
    private int col;
    private CellGroup group;


    public CellGroup getGroup() {
        return group;
    }

    public void setGroup(CellGroup group) {
        this.group = group;
    }


    public BoardCell (Boolean isRhombic, CellStatus status, int row, int col) {
        super(isRhombic, status);
        this.row = row;
        this.col = col;
        group = null;

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

        // this is octagonal

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

}
