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

  @Test
  void moveCountIncrementsAfterEachPlace() {
    QSSController qssController = new QSSController();
    assertEquals(0, qssController.game.getMoveCount());
    qssController.game.placeCell(false, 0, 0);
    assertEquals(1, qssController.game.getMoveCount());
    qssController.game.placeCell(false, 1, 1);
    assertEquals(2, qssController.game.getMoveCount());
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
    for(int col = 0; col <= 10; col++) {
      qssController.game.placeCell(false, 1,  col);
      if(!qssController.game.isGameOver()){
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
    for(int row = 0; row <= 10; row++) {
      qssController.game.placeCell(false, row, 5);
      if(!qssController.game.isGameOver()){
        qssController.game.placeCell(false, row, 6);
      }
    }
    assertTrue(qssController.game.isGameOver());
    int countBefore = qssController.game.getMoveCount();
    qssController.game.placeCell(false, 0, 9);
    assertEquals(countBefore, qssController.game.getMoveCount());
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

  @Test
  void shouldShowBotStrategyButton() {
    QSSController controller = new QSSController();
    controller.game = new Game();
    assertTrue(controller.isBotStrategyButtonEnabled());
  }

  @Test
  void shouldShowBotStrategy() {
    QSSController controller = new QSSController();
    controller.game = new Game();
    Bot bot = controller.game.getBot();
    bot.calculatePaths(controller.game.getState());
    assertNotNull(bot.getNextMove());
  }
}