package hio.qss;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchNodeTest {

    @Test
    void testCalculateDistance() {
        Game game = new Game();
        GameState state = game.getState();
        BoardCell[][] ocells = state.ocells();
        BoardCell[][] rcells = state.rcells();

        assertEquals(0, SearchNode.calculateDistance(ocells[0][0], ocells[0][0]));
        assertEquals(1, SearchNode.calculateDistance(ocells[0][0], ocells[0][1]));
        assertEquals(1, SearchNode.calculateDistance(ocells[0][0], ocells[1][0]));
        assertEquals(2, SearchNode.calculateDistance(ocells[0][0], ocells[1][1]));
        assertEquals(10, SearchNode.calculateDistance(ocells[0][0], ocells[0][10]));
        assertEquals(20, SearchNode.calculateDistance(ocells[0][0], ocells[10][10]));

        assertEquals(1, SearchNode.calculateDistance(ocells[0][0], rcells[0][0]));
        assertEquals(1, SearchNode.calculateDistance(rcells[0][0], ocells[0][0]));
        assertEquals(3, SearchNode.calculateDistance(ocells[0][0], rcells[1][1]));
        assertEquals(5, SearchNode.calculateDistance(ocells[1][1], rcells[3][3]));

        assertEquals(0, SearchNode.calculateDistance(rcells[0][0], rcells[0][0]));
        assertEquals(2, SearchNode.calculateDistance(rcells[0][0], rcells[0][1]));
        assertEquals(10, SearchNode.calculateDistance(rcells[0][0], rcells[0][9]));
        assertEquals(18, SearchNode.calculateDistance(rcells[0][0], rcells[9][9]));
        assertEquals(16, SearchNode.calculateDistance(rcells[1][1], rcells[9][9]));
    }
}