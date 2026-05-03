
package hio.qss;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class Feature9_WinningChain {
  @Test
  void restartButtonTest() {
    QSSController qssController = new QSSController();

    qssController.game.placeCell(false, 0, 0);
    qssController.game.placeCell(false, 0, 1);

    qssController.game = new Game();

    assertEquals(0, qssController.game.getMoveCount());
    assertTrue(qssController.game.isBlack());
    assertFalse(qssController.game.isGameOver());
    assertFalse(qssController.game.isBlackWins());
  }

  @Test
  void displayWinner() {
    QSSController qssController = new QSSController();
    for (int row = 0; row <= 10; row++) {
      qssController.game.placeCell(false, row, 0);
      if (!qssController.game.isGameOver()) {
        qssController.game.placeCell(false, row, 1);
      }
    }
    assertTrue(qssController.game.isGameOver());
    assertTrue(qssController.game.isBlackWins());
  }

  @Test
  void gamNotOverAtStart() {
    QSSController qssController = new QSSController();
    assertFalse(qssController.game.isGameOver());
    assertFalse(qssController.game.isBlackWins());
  }

  @Test
  void whiteWinsTest() {
    QSSController qssController = new QSSController();
    for (int col = 0; col <= 10; col++) {
      qssController.game.placeCell(false, 1, col);
      if (!qssController.game.isGameOver()) {
        qssController.game.placeCell(false, 5, col);
      }
    }
    assertTrue(qssController.game.isGameOver());
    assertFalse(qssController.game.isBlackWins());
  }

  @Test
  void rhombicCellPlacementSwitchesTurnTest() {
    QSSController qssController = new QSSController();
    assertTrue(qssController.game.isBlack());
    qssController.game.placeCell(true, 0, 0);
    assertFalse(qssController.game.isBlack());
  }

  @Test
  void noMoveAfterGameOverTest() {
    QSSController qssController = new QSSController();
    for (int row = 0; row <= 10; row++) {
      qssController.game.placeCell(false, row, 5);
      if (!qssController.game.isGameOver()) {
        qssController.game.placeCell(false, row, 6);
      }
    }
    assertTrue(qssController.game.isGameOver());
    int countBefore = qssController.game.getMoveCount();
    qssController.game.placeCell(false, 0, 9);
    assertEquals(countBefore, qssController.game.getMoveCount());
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
  void testGetNeighbours()
      throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
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
    ArrayList<BoardCell> neighbours =
        (ocells[0][0]).getNeighbours(new GameState(ocells, rcells, game.isBlack()));
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
}
