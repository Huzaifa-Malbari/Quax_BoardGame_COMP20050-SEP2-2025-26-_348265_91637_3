package hio.qss;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QSSControllerTest {
    @Test
   void setPlayerTurnTextTest() {

        QSSController qssController = new QSSController();

        assertEquals("Black's Turn", qssController.getTurnText());

        qssController.game.placeCell(false, 0, 0); // switches turn from Black to White
        assertEquals("White's Turn", qssController.getTurnText());
   }

   @Test
    void restartButtonTest() {
        QSSController qssController = new QSSController();

    }

    @Test
    void displayWinner() {
        QSSController qssController = new QSSController();
    }
}