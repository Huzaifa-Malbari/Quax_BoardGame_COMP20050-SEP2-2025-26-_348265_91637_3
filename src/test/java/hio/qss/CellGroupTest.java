package hio.qss;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CellGroupTest {

    @Test
    void testSetFurthest() {
        CellGroup group = new CellGroup();
        BoardCell o0_0 = new BoardCell(false, CellStatus.B, 0, 0);

        group.addCell(o0_0);
        group.setFurthest(o0_0);

        BoardCell o1_0 = new BoardCell(false, CellStatus.B, 1, 0);
        assertTrue(group.getFurthest() == o0_0);
        group.setFurthest(o1_0);
        assertTrue(group.getFurthest() == o1_0);

        assertTrue(group.setFurthest(o0_0) == null);
    }

    @Test
    void testAddCell() {
        CellGroup group = new CellGroup();
        BoardCell o0_0 = new BoardCell(false, CellStatus.B, 0, 0);

        group.addCell(o0_0);
        assertTrue(group.getCells().contains(o0_0));
    }

    @Test
    void testGetCells() {
        CellGroup group = new CellGroup();
        ArrayList<BoardCell> cells = new ArrayList<BoardCell>();
        for (int i = 0; i < 5; i++) {
            cells.add(new BoardCell(true, CellStatus.B, 0, i));
            group.addCell(cells.get(i));
        }

        for (int i = 0; i < 5; i++) {
            assertTrue(group.getCells().get(i) == cells.get(i));
        }

    }

    @Test
    void testMerge() {
        CellGroup group1 = new CellGroup();
        ArrayList<BoardCell> cells1 = new ArrayList<BoardCell>();
        for (int i = 0; i < 5; i++) {
            cells1.add(new BoardCell(true, CellStatus.B, 0, i));
            group1.addCell(cells1.get(i));
        }
        group1.setFurthest(cells1.get(4));

        CellGroup group2 = new CellGroup();
        ArrayList<BoardCell> cells2 = new ArrayList<BoardCell>();
        for (int i = 0; i < 5; i++) {
            cells2.add(new BoardCell(false, CellStatus.B, 0, i));
            group2.addCell(cells2.get(i));
        }
        group2.setFurthest(cells2.get(4));

        group1.merge(group2);
        assertEquals(0, group2.size());
        assertEquals(cells1.get(4), group1.getFurthest());

        for (int i = 0; i < 5; i++) {
            assertTrue(group1.getCells().get(i) == cells1.get(i));
        }
        for (int i = 0; i < 5; i++) {
            assertTrue(group1.getCells().get(5 + i) == cells2.get(i));
        }

    }
}