
package hio.qss;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class Feature8_UnoccupiedCellPlacementOnly {
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
}
