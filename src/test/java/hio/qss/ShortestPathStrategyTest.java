package hio.qss;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ShortestPathStrategyTest {

    @Test
    void testGetShortestPathBetween() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Game game = new Game();
        GameState state = game.getState();
        BoardCell[][] ocells = state.ocells();
        BoardCell[][] rcells = state.rcells();

        ArrayList<SearchNode> path;
        path =  AStar.getShortestPathBetween(state, new SearchNode(ocells[0][0]), new SearchNode(ocells[0][10]));
        assertEquals(11, path.size());

        path = AStar.getShortestPathBetween(state, new SearchNode(ocells[0][0]), new SearchNode(ocells[10][10]));
        assertEquals(21, path.size());

        path = AStar.getShortestPathBetween(state, new SearchNode(ocells[0][0]), new SearchNode(rcells[0][0]));
        assertEquals(2, path.size());

        path = AStar.getShortestPathBetween(state, new SearchNode(rcells[0][0]), new SearchNode(rcells[0][9]));
        assertEquals(11, path.size());

        path = AStar.getShortestPathBetween(state, new SearchNode(rcells[0][0]), new SearchNode(rcells[9][9]));
        assertEquals(19, path.size());
    }

    @Test
    void testAddNeighbourPaths() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Class clazz = Class.forName("hio.qss.ShortestPathStrategy");
        Method method = clazz.getDeclaredMethod("addNeighbourPaths", GameState.class, BoardCell.class);
        method.setAccessible(true);
        Field pathsField = clazz.getDeclaredField("paths");
        pathsField.setAccessible(true);

        ShortestPathStrategy strategy = new ShortestPathStrategy();
        Game game = new Game();
        GameState state = game.getState();
        BoardCell[][] ocells = state.ocells();
        BoardCell[][] rcells = state.rcells();

        pathsField.set(strategy, new ArrayList<ArrayList<SearchNode>>());
        ArrayList<ArrayList<SearchNode>> paths = (ArrayList<ArrayList<SearchNode>>) pathsField.get(strategy);

        method.invoke(strategy, state, ocells[0][0]);
        assertEquals(3*11, paths.size());

    }

    @Test
    void testAddPath() throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        Class clazz = Class.forName("hio.qss.ShortestPathStrategy");
        Method method = clazz.getDeclaredMethod("addPath", ArrayList.class);
        method.setAccessible(true);
        Field pathsField = clazz.getDeclaredField("paths");
        pathsField.setAccessible(true);

        ShortestPathStrategy strategy = new ShortestPathStrategy();
        Game game = new Game();
        GameState state = game.getState();
        BoardCell[][] ocells = state.ocells();
        BoardCell[][] rcells = state.rcells();

        pathsField.set(strategy, new ArrayList<ArrayList<SearchNode>>());
        ArrayList<ArrayList<SearchNode>> paths = (ArrayList<ArrayList<SearchNode>>) pathsField.get(strategy);

        ArrayList<SearchNode> newPath = new ArrayList<SearchNode>();
        method.invoke(strategy, newPath);
        assertEquals(0, paths.size());

        newPath.add(new SearchNode(rcells[1][1]));
        method.invoke(strategy, newPath);
        assertEquals(1, paths.size());


    }

    @Test
    void testGetChosenPath() {

        Game game = new Game();

        Bot bot = new Bot(new ShortestPathStrategy());
        bot.calculatePaths(game.getState());
        bot.setLastMove(game.getState().ocells()[0][0]);
        assertEquals(game.getState().ocells()[0][0], bot.getNextMove());
        assertEquals(1, bot.getChosenPath().size());

        bot.calculatePaths(game.getState());
        assertEquals(game.getState().ocells()[1][0], bot.getNextMove());
        assertEquals(10, bot.getChosenPath().size());

        bot.setLastMove(game.getState().ocells()[9][0]);
        bot.calculatePaths(game.getState());
        assertEquals(game.getState().ocells()[10][0], bot.getNextMove());
        assertEquals(1, bot.getChosenPath().size());

    }

}