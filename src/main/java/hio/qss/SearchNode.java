package hio.qss;

public class SearchNode extends BoardCell{
    private SearchNode connection;
    private int g;
    private int h;
    private int f;

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
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public SearchNode(BoardCell cell) {
        super(cell.getRhombic(), cell.getStatus(), cell.getRow(), cell.getCol());
        g = 0;
        h = 0;
        f = 0;
    }

    public int calculateH(BoardCell thisCell, BoardCell target) {
        int dx = Math.abs(target.getRow() - thisCell.getRow());
        int dy = Math.abs(target.getCol() - thisCell.getCol());
        if (getRhombic()) {
//            int dx = Math.abs(target.getRow() - getRow());
//            int dy = Math.abs(target.getCol() - getCol());
            h = dx + dy;
            if (target.getRhombic()) {
//                dx = (dx == 0) ? 1 : dx;
//                dy = (dy == 0) ? 1 : dy;
                if ((dx == 0) || (dy == 0)) {
                    h--;
                }
                return h;
            }
//            dx = (dx == 0) ? dx : dx - 1;
//            dy = (dy == 0) ? dy : dy - 1;
            if ((dx != 0) || (dy != 0)) {
                h--;
            }
            h = (h == 0) ? 1 : h;
            return h;

        }

        if (target.getRhombic()) {
            return calculateH(target, thisCell);
        }



        return 0;
    }

}
