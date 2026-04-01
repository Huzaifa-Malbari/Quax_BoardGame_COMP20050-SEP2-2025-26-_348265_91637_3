package hio.qss;

import org.junit.jupiter.api.Test;

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
  void testCheckForWin() {

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
}
