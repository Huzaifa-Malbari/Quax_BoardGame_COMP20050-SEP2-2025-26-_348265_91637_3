package hio.qss;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardCellTest {

    @Test
    void testSetFurthestCell() {
        BoardCell cell1 = new BoardCell(false, CellStatus.B, 0,0);
        BoardCell cell2 = new BoardCell(false, CellStatus.B, 1,0);
        BoardCell cell3 = new BoardCell(false, CellStatus.B, 2,0);

        cell1.setFurthestCell(cell2);
        assertEquals(cell2, cell1.getFurthestCell());

        cell2.setFurthestCell(cell3);
        assertEquals(cell3, cell2.getFurthestCell());

        assertEquals(cell3, cell1.getFurthestCell());
    }

    @Test
    void testIsFurther() {
        BoardCell cell1 = new BoardCell(false, CellStatus.B, 0,0);
        BoardCell cell2 = new BoardCell(false, CellStatus.B, 1,0);
        BoardCell cell3 = new BoardCell(false, CellStatus.B, 2,0);

        BoardCell cella = new BoardCell(true, CellStatus.B, 0,0);
        BoardCell cellb = new BoardCell(true, CellStatus.B, 1,0);
        BoardCell cellc = new BoardCell(true, CellStatus.B, 2,0);

        assertTrue(cell1.isFurther(cell2));
        assertTrue(cell1.isFurther(cell3));
        assertFalse(cell2.isFurther(cell1));

        assertTrue(cell1.isFurther(cella));
        assertTrue(cella.isFurther(cell2));
        assertFalse(cella.isFurther(cell1));

    }
}