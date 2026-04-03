package hio.qss;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.*;
import java.util.ArrayList;

public class StressTests {

    @Test
    void testMaxCellGroups() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {

        Class clazz = Class.forName("hio.qss.Game");
        Field oc = clazz.getDeclaredField("ocells");
        oc.setAccessible(true);

        Game game = new Game();

        BoardCell[][] ocells = (BoardCell[][]) oc.get(game);

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++)  {
                game.placeCell(false, i, j);
            }
        }

        ArrayList<CellGroup> groups = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++)  {
                CellGroup group = ((BoardCell) ocells[i][j]).getGroup();
                if (!groups.contains(group)) {
                    groups.add(group);
                }
            }
        }

        assertEquals(11*11, groups.size());
    }

    @Test
    void testMergeBiggestAndSmallestGroups() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {

        Class clazz = Class.forName("hio.qss.Game");
        Field oc = clazz.getDeclaredField("ocells");
        oc.setAccessible(true);

        Field blk = clazz.getDeclaredField("isBlack");
        blk.setAccessible(true);

        Game game = new Game();

        BoardCell[][] ocells = (BoardCell[][]) oc.get(game);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 11; j++)  {
                game.placeCell(false, i, j);
                blk.set(game, true);
            }
        }
        for (int i = 0; i < 10; i++) {
            game.placeCell(false, 9, i);
            blk.set(game, true);
        }
//        for (int i = 0; i < 9; i++) {
//            game.placeCell(false, 10, i);
//            blk.set(game, true);
//        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++)  {
                game.placeCell(true, i, j);
                blk.set(game, true);
            }
        }
        for (int i = 0; i < 9; i++) {
            game.placeCell(true, 9, i);
            blk.set(game, true);
        }

        CellGroup big = ocells[0][0].getGroup();
        assertEquals(10*11 + 10*10 - 2, big.size());

        blk.set(game, true);
        game.placeCell(false, 10, 10);
        CellGroup small = ocells[10][10].getGroup();
        assertEquals(1, small.size());

        blk.set(game, true);
        game.placeCell(true, 9, 9);

        assertEquals(10*11 + 10*10, big.size());
        assertEquals(0, small.size());
        assertTrue(big.getCells().contains(ocells[10][10]));

    }

}
