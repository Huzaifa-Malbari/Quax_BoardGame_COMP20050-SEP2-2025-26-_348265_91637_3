
package hio.qss;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class Feature7_PieRuleButton {
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
