package hio.qss;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

  @Test
  void testPlaceCellValid() {
    Game game = new Game();
    Boolean b = game.placeCell(true, 0, 0);
    assertTrue(b, "valid cell placement failed");

    b = game.placeCell(false, 0, 0);
    assertTrue(b, "valid cell placement failed");

    b = game.placeCell(true, 9, 9);
    assertTrue(b, "valid cell placement failed");

    b = game.placeCell(false, 10, 10);
    assertTrue(b, "valid cell placement failed");
  }

  @Test
  void testPlaceCellAlreadyOccupied() {
    Game game = new Game();
    game.placeCell(true, 0, 0);
    Boolean b = game.placeCell(true, 0, 0);
    assertFalse(b, "occupied cell placement successful");

    game.placeCell(false, 0, 0);
    b = game.placeCell(false, 0, 0);
    assertFalse(b, "occupied cell placement successful");
  }

  @Test
  void testPlaceCellInvalidCell() {
    Game game = new Game();
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> game.placeCell(true, -1, 0));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> game.placeCell(true, 0, 10));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> game.placeCell(false, -1, 0));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> game.placeCell(false, 0, 11));
  }

  @Test
  void testIsBlack() {
    Game game = new Game();
    assertTrue(game.isBlack(), "It is not blacks turn at start");
    game.placeCell(true, 0, 0);
    assertFalse(game.isBlack(), "It is still blacks turn after they placed");
  }

  @Test
  void testCheckForWinAndUpdateChains() {
    // Check all possible straight lines for both black and white

    for (int i = 0; i < 11; i++) {
      Game game = new Game();

      for (int j = 0; j < 11; j++) {
        game.placeCell(false, j, i);
        game.placeCell(false, j, (i + 1) % 11);
      }
      assertTrue(game.isGameOver());
      assertTrue(game.isBlackWins());
    }

    for (int i = 0; i < 11; i++) {
      Game game = new Game();

      for (int j = 0; j < 11; j++) {
        game.placeCell(false, (i + 1) % 11, j);
        game.placeCell(false, i, j);
      }
      assertTrue(game.isGameOver());
      assertFalse(game.isBlackWins());
    }

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
    ArrayList<BoardCell> neighbours = game.getNeighbours(ocells[0][0]);
    assertTrue(neighbours.contains(ocells[0][1]));
    assertTrue(neighbours.contains(ocells[1][0]));
    assertTrue(neighbours.contains(rcells[0][0]));

    // Top right octagon
    neighbours = game.getNeighbours(ocells[0][10]);
    assertTrue(neighbours.contains(ocells[0][9]));
    assertTrue(neighbours.contains(ocells[1][10]));
    assertTrue(neighbours.contains(rcells[0][9]));

    // Bottom right octagon
    neighbours = game.getNeighbours(ocells[10][10]);
    assertTrue(neighbours.contains(ocells[10][9]));
    assertTrue(neighbours.contains(ocells[9][10]));
    assertTrue(neighbours.contains(rcells[9][9]));

    // Bottom left octagon
    neighbours = game.getNeighbours(ocells[10][0]);
    assertTrue(neighbours.contains(ocells[10][1]));
    assertTrue(neighbours.contains(ocells[9][0]));
    assertTrue(neighbours.contains(rcells[9][0]));

    // Interior octagon
    neighbours = game.getNeighbours(ocells[5][5]);
    assertTrue(neighbours.contains(ocells[4][5]));
    assertTrue(neighbours.contains(ocells[6][5]));
    assertTrue(neighbours.contains(ocells[5][4]));
    assertTrue(neighbours.contains(ocells[5][6]));
    assertTrue(neighbours.contains(rcells[4][4]));
    assertTrue(neighbours.contains(rcells[4][5]));
    assertTrue(neighbours.contains(rcells[5][4]));
    assertTrue(neighbours.contains(rcells[5][5]));

    // rhombus
    neighbours = game.getNeighbours(rcells[0][0]);
    assertTrue(neighbours.contains(ocells[0][0]));
    assertTrue(neighbours.contains(ocells[0][1]));
    assertTrue(neighbours.contains(ocells[1][0]));
    assertTrue(neighbours.contains(ocells[1][1]));
  }

  /* Test for pie rule event - move count */

  @Test
  void newGame_startsWithZeroMoves() {
    Game game = new Game();

    assertEquals(0, game.getMoveCount());
  }

  @Test
  void placeCell_successfulMove_incrementsMoveCount() {
    Game game = new Game();

    game.placeCell(false, 0, 0);

    assertEquals(1, game.getMoveCount());
  }

  @Test
  void placeCell_failedMove_doesNotIncrementMoveCount() {
    Game game = new Game();

    assertTrue(game.placeCell(false, 0, 0));
    assertFalse(game.placeCell(false, 0, 0));

    assertEquals(1, game.getMoveCount());
  }
}
