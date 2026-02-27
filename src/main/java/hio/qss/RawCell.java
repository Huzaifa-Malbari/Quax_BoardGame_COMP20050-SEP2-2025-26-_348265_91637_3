package hio.qss;


public class RawCell implements Cell{
    Boolean isRhombic;
    CellStatus status;

    public RawCell (Boolean isRhombic, CellStatus status) {
        this.isRhombic = isRhombic;
        this.status = status;
    }

    @Override
    public Boolean getRhombic() {
        return isRhombic;
    }

    @Override
    public CellStatus getStatus() {
        return status;
    }
}
