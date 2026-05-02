package hio.qss;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Feature12_ShowStrategy {

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
