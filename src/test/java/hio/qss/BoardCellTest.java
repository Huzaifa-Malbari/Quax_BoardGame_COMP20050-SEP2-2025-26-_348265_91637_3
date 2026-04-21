package hio.qss;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardCellTest {

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

    @Test
    void testGetNeighbours() throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {

        // Initialize board and fill cells
        Class clazz = Class.forName("hio.qss.Game");
        Field oc = clazz.getDeclaredField("ocells");
        Field rc = clazz.getDeclaredField("rcells");
        oc.setAccessible(true);
        rc.setAccessible(true);

        Game game = new Game();
        BoardCell[][] ocells = (BoardCell[][]) oc.get(game);
        BoardCell[][] rcells = (BoardCell[][]) rc.get(game);

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                game.placeCell(false, i, j);
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                game.placeCell(true, i, j);
            }
        }

        // Top left octagon
        ArrayList<BoardCell> neighbours = (ocells[0][0]).getNeighbours(new GameState(ocells, rcells, game.isBlack()));
        assertTrue(neighbours.contains(ocells[0][1]));
        assertTrue(neighbours.contains(ocells[1][0]));
        assertTrue(neighbours.contains(rcells[0][0]));

        // Top right octagon
        neighbours = (ocells[0][10]).getNeighbours(new GameState(ocells, rcells, game.isBlack()));
        assertTrue(neighbours.contains(ocells[0][9]));
        assertTrue(neighbours.contains(ocells[1][10]));
        assertTrue(neighbours.contains(rcells[0][9]));

        // Bottom right octagon
        neighbours = (ocells[10][10]).getNeighbours(new GameState(ocells, rcells, game.isBlack()));
        assertTrue(neighbours.contains(ocells[10][9]));
        assertTrue(neighbours.contains(ocells[9][10]));
        assertTrue(neighbours.contains(rcells[9][9]));

        // Bottom left octagon
        neighbours = (ocells[10][0]).getNeighbours(new GameState(ocells, rcells, game.isBlack()));
        assertTrue(neighbours.contains(ocells[10][1]));
        assertTrue(neighbours.contains(ocells[9][0]));
        assertTrue(neighbours.contains(rcells[9][0]));

        // Interior octagon
        neighbours = (ocells[5][5]).getNeighbours(new GameState(ocells, rcells, game.isBlack()));
        assertTrue(neighbours.contains(ocells[4][5]));
        assertTrue(neighbours.contains(ocells[6][5]));
        assertTrue(neighbours.contains(ocells[5][4]));
        assertTrue(neighbours.contains(ocells[5][6]));
        assertTrue(neighbours.contains(rcells[4][4]));
        assertTrue(neighbours.contains(rcells[4][5]));
        assertTrue(neighbours.contains(rcells[5][4]));
        assertTrue(neighbours.contains(rcells[5][5]));

        // rhombus
        neighbours = (rcells[0][0]).getNeighbours(new GameState(ocells, rcells, game.isBlack()));
        assertTrue(neighbours.contains(ocells[0][0]));
        assertTrue(neighbours.contains(ocells[0][1]));
        assertTrue(neighbours.contains(ocells[1][0]));
        assertTrue(neighbours.contains(ocells[1][1]));
    }

    @Test
    void testEquals() {

        ArrayList<BoardCell> cells = new ArrayList<BoardCell>();
        BoardCell first = new BoardCell(false, CellStatus.B, 0, 0);
        BoardCell second = new BoardCell(false, CellStatus.B, 0, 0);
        BoardCell other = new BoardCell(true, CellStatus.B, 0, 0);

        assertEquals(first, first);
        assertEquals(first, second);
        assertFalse(first.equals(other));

        cells.add(first);
        cells.add(other);

        assertTrue(cells.contains(second));

    }

}