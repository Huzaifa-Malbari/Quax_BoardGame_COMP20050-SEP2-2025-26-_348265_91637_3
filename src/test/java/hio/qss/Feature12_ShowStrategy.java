
package hio.qss;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import org.junit.jupiter.api.Test;

public class Feature12_ShowStrategy {
  @Test
  void shouldShowBotStrategyButton() {
    QSSController controller = new QSSController();
    controller.game = new Game();
    assertTrue(controller.isBotStrategyButtonEnabled());
  }

  @Test
  void shouldShowBotStrategy()
      throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
    QSSController controller = new QSSController();
    controller.game = new Game();

    Class clazz = Class.forName("hio.qss.QSSController");
    Field qbot = clazz.getDeclaredField("bot");
    qbot.setAccessible(true);

    Bot bot = (Bot) qbot.get(controller);
    bot.calculatePaths(controller.game.getState());
    assertNotNull(bot.getNextMove());
  }
}
