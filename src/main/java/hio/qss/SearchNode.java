package hio.qss;

public class SearchNode extends BoardCell{
    private SearchNode connection;
    private int g;
    private int h;

    public SearchNode getConnection() {
        return connection;
    }

    public void setConnection(SearchNode connection) {
        this.connection = connection;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getF() {
        return h + g;
    }

    public SearchNode(BoardCell cell) {
        super(cell.getRhombic(), cell.getStatus(), cell.getRow(), cell.getCol());
        g = 0;
        h = 0;
    }

    public static int calculateDistance(BoardCell thisCell, BoardCell target) {
        int dx = Math.abs(target.getRow() - thisCell.getRow());
        int dy = Math.abs(target.getCol() - thisCell.getCol());
        int newH = dx + dy;
        if (thisCell.getRhombic()) {
            if (target.getRhombic()) {
                if (newH == 0) {
                    return newH;
                }
                if ((dx == 0) || (dy == 0)) {
                    newH++;
                }
                return newH;
            }
            if ((dx != 0) || (dy != 0)) {
                newH++;
            }
            newH = (newH == 0) ? 1 : newH;
            return newH;

        }

        if (target.getRhombic()) {
            return calculateDistance(target, thisCell);
        }

        return newH;
    }

}
