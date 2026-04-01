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

}
