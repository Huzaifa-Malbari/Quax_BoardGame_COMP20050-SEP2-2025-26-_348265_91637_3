package hio.qss;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
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
    for(int row = 0; row <= 10; row++) {
        qssController.game.placeCell(false, row, 0);
        if(!qssController.game.isGameOver()){
            qssController.game.placeCell(false, row, 1);
        }
    }
    assertTrue(qssController.game.isGameOver());
    assertTrue(qssController.game.isBlackWins());
    }

  /*
   * Unit tests added by Ioan - Check if pie rule button should be shown in
   * various situations
   */
  @Test
  void shouldShowPieRuleButton_falseAtStart() {
    QSSController controller = new QSSController();
    controller.game = new Game();

    assertFalse(controller.shouldShowPieRuleButton());
  }

  @Test
  void shouldShowPieRuleButton_trueAfterFirstMove() {
    QSSController controller = new QSSController();
    controller.game = new Game();

    controller.game.placeCell(false, 0, 0);

    assertTrue(controller.shouldShowPieRuleButton());
  }

  @Test
  void shouldShowPieRuleButton_falseAfterSecondMove() {
    QSSController controller = new QSSController();
    controller.game = new Game();

    controller.game.placeCell(false, 0, 0);
    controller.game.placeCell(false, 0, 1);

    assertFalse(controller.shouldShowPieRuleButton());
  }
}