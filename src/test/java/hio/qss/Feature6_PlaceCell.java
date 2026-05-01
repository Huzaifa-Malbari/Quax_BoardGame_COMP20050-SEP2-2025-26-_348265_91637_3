package hio.qss;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Feature6_PlaceCell {

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
}
