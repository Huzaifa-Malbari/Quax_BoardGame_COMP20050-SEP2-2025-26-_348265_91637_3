package hio.qss;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ShortestPathStrategyTest {

    @Test
    void testCalculateShortestPathBetween() throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, InvocationTargetException, IllegalAccessException {

        Game game = new Game();
        Bot bot = new Bot(new ShortestPathStrategy());

        Class clazz = Class.forName("hio.qss.ShortestPathStrategy");
        Method method = clazz.getDeclaredMethod("getShortestPathBetween", GameState.class,  SearchNode.class, SearchNode.class);
        method.setAccessible(true);

        GameState state = game.getState();
        ShortestPathStrategy strategy = (ShortestPathStrategy) bot.getStrategy();
        method.invoke(strategy, state, new SearchNode(state.ocells()[0][0]), new SearchNode(state.ocells()[0][10]));

        ArrayList<SearchNode> shortestPath;

        shortestPath = (ArrayList<SearchNode>) method.invoke(strategy, state,
                new SearchNode(state.ocells()[0][0]), new SearchNode(state.ocells()[0][10]));
        assertEquals(11, shortestPath.size());

        shortestPath = (ArrayList<SearchNode>) method.invoke(strategy, state,
                new SearchNode(state.ocells()[0][0]), new SearchNode(state.ocells()[1][0]));
        assertEquals(2, shortestPath.size());


        shortestPath = (ArrayList<SearchNode>) method.invoke(strategy, state,
                new SearchNode(state.ocells()[0][0]), new SearchNode(state.ocells()[1][10]));
        assertEquals(12, shortestPath.size());

    }

}