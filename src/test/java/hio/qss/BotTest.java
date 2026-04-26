package hio.qss;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BotTest {

  // Checks that a new Bot stores the strategy it was given, starts as black, and
  // has no remembered moves yet.
  @Test
  void constructor_setsDefaultStateAndStrategy() {
    RecordingStrategy strategy = new RecordingStrategy();
    Bot bot = new Bot(strategy);

    assertSame(strategy, bot.getStrategy());
    assertTrue(bot.isBlack());
    assertNull(bot.getLastMove());
    assertNull(bot.getLastOpponentMove());
  }

  // Verifies that setLastMove, setLastOpponentMove, and setBlack actually update
  // the bot’s internal state.
  @Test
  void setters_updateTrackedState() {
    Bot bot = new Bot(new RecordingStrategy());
    BoardCell lastMove = new BoardCell(false, CellStatus.B, 2, 3);
    BoardCell lastOpponentMove = new BoardCell(true, CellStatus.W, 4, 5);

    bot.setLastMove(lastMove);
    bot.setLastOpponentMove(lastOpponentMove);
    bot.setBlack(false);

    assertSame(lastMove, bot.getLastMove());
    assertSame(lastOpponentMove, bot.getLastOpponentMove());
    assertFalse(bot.isBlack());
  }

  // Confirms Bot.getPaths() is just delegating to the strategy and returns the
  // same path data.
  @Test
  void getPaths_returnsPathsProvidedByStrategy() {
    RecordingStrategy strategy = new RecordingStrategy();
    ArrayList<SearchNode> path = new ArrayList<>();
    SearchNode nextMove = new SearchNode(new BoardCell(false, CellStatus.Free, 1, 1));
    path.add(nextMove);
    strategy.paths.add(path);

    Bot bot = new Bot(strategy);

    assertSame(strategy.paths, bot.getPaths());
    assertEquals(1, bot.getPaths().size());
    assertSame(nextMove, bot.getPaths().getFirst().getFirst());
  }

  /*
   * This is the most important behavior test. It verifies that when
   * Bot.calculatePaths(state) is called, the bot passes:
   * 
   * the GameState
   * its own lastMove
   * its own lastOpponentMove
   * into the strategy correctly.
   */
  @Test
  void calculatePaths_passesStateAndTrackedMovesToStrategy() {
    RecordingStrategy strategy = new RecordingStrategy();
    Bot bot = new Bot(strategy);
    GameState state = new Game().getState();
    BoardCell lastMove = new BoardCell(false, CellStatus.B, 3, 4);
    BoardCell lastOpponentMove = new BoardCell(true, CellStatus.W, 5, 6);

    bot.setLastMove(lastMove);
    bot.setLastOpponentMove(lastOpponentMove);

    bot.calculatePaths(state);

    assertSame(state, strategy.statePassed);
    assertSame(lastMove, strategy.lastMovePassed);
    assertSame(lastOpponentMove, strategy.lastOpponentMovePassed);
  }

  // Confirms Bot.getNextMove() returns whatever move the strategy selected
  @Test
  void getNextMove_returnsMoveChosenByStrategy() {
    RecordingStrategy strategy = new RecordingStrategy();
    SearchNode nextMove = new SearchNode(new BoardCell(false, CellStatus.Free, 7, 8));
    strategy.nextMove = nextMove;
    Bot bot = new Bot(strategy);

    assertSame(nextMove, bot.getNextMove());
  }

  // RecordingStrategy is a fake version of Strategy

  private static class RecordingStrategy implements Strategy {
    private final ArrayList<ArrayList<SearchNode>> paths = new ArrayList<>(); // Stores path data that the fake will
                                                                              // return when getPaths() is called.
    private SearchNode nextMove; // Stores the move that the fake will return when getNextMove() is called.
    private GameState statePassed;
    private BoardCell lastMovePassed;
    private BoardCell lastOpponentMovePassed;
    // these save the values sent by the bot into calculatePaths(...)

    @Override
    public ArrayList<ArrayList<SearchNode>> getPaths() { // we put test data into paths, then check that bot.getPaths()
                                                         // returns it
      return paths;
    }

    @Override
    public SearchNode getNextMove() { // we put a SearchNode into nextMove, then check that bot.getNextMove() returns
                                      // it.
      return nextMove;
    }

    // here we don’t calculate anything at all; we just record the arguments and
    // verify the bot passed the right ones.
    @Override
    public void calculatePaths(GameState state, BoardCell lastMove, BoardCell lastOpponentMove) {
      statePassed = state;
      lastMovePassed = lastMove;
      lastOpponentMovePassed = lastOpponentMove;
    }

    @Override
    public ArrayList<SearchNode> getChosenPath() {
      return paths.getFirst();
    }
  }
}
