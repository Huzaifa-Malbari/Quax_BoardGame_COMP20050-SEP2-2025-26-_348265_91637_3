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
