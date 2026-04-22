package hio.qss;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import java.util.ArrayList;
import java.util.Random;


public class QSSController {
  @FXML
  private Polygon O0_0;

  @FXML
  private Polygon O0_1;

  @FXML
  private Polygon O0_10;

  @FXML
  private Polygon O0_2;

  @FXML
  private Polygon O0_3;

  @FXML
  private Polygon O0_4;

  @FXML
  private Polygon O0_5;

  @FXML
  private Polygon O0_6;

  @FXML
  private Polygon O0_7;

  @FXML
  private Polygon O0_8;

  @FXML
  private Polygon O0_9;

  @FXML
  private Polygon O10_0;

  @FXML
  private Polygon O10_1;

  @FXML
  private Polygon O10_10;

  @FXML
  private Polygon promptOct;

  @FXML
  private Polygon O10_12;

  @FXML
  private Polygon O10_2;

  @FXML
  private Polygon O10_3;

  @FXML
  private Polygon O10_4;

  @FXML
  private Polygon O10_5;

  @FXML
  private Polygon O10_6;

  @FXML
  private Polygon O10_7;

  @FXML
  private Polygon O10_8;

  @FXML
  private Polygon O10_9;

  @FXML
  private Polygon O1_0;

  @FXML
  private Polygon O1_1;

  @FXML
  private Polygon O1_10;

  @FXML
  private Polygon O1_2;

  @FXML
  private Polygon O1_3;

  @FXML
  private Polygon O1_4;

  @FXML
  private Polygon O1_5;

  @FXML
  private Polygon O1_6;

  @FXML
  private Polygon O1_7;

  @FXML
  private Polygon O1_8;

  @FXML
  private Polygon O1_9;

  @FXML
  private Polygon O2_0;

  @FXML
  private Polygon O2_1;

  @FXML
  private Polygon O2_10;

  @FXML
  private Polygon O2_2;

  @FXML
  private Polygon O2_3;

  @FXML
  private Polygon O2_4;

  @FXML
  private Polygon O2_5;

  @FXML
  private Polygon O2_6;

  @FXML
  private Polygon O2_7;

  @FXML
  private Polygon O2_8;

  @FXML
  private Polygon O2_9;

  @FXML
  private Polygon O3_0;

  @FXML
  private Polygon O3_1;

  @FXML
  private Polygon O3_10;

  @FXML
  private Polygon O3_2;

  @FXML
  private Polygon O3_3;

  @FXML
  private Polygon O3_4;

  @FXML
  private Polygon O3_5;

  @FXML
  private Polygon O3_6;

  @FXML
  private Polygon O3_7;

  @FXML
  private Polygon O3_8;

  @FXML
  private Polygon O3_9;

  @FXML
  private Polygon O4_0;

  @FXML
  private Polygon O4_1;

  @FXML
  private Polygon O4_10;

  @FXML
  private Polygon O4_2;

  @FXML
  private Polygon O4_3;

  @FXML
  private Polygon O4_4;

  @FXML
  private Polygon O4_5;

  @FXML
  private Polygon O4_6;

  @FXML
  private Polygon O4_7;

  @FXML
  private Polygon O4_8;

  @FXML
  private Polygon O4_9;

  @FXML
  private Polygon O5_0;

  @FXML
  private Polygon O5_1;

  @FXML
  private Polygon O5_10;

  @FXML
  private Polygon O5_2;

  @FXML
  private Polygon O5_3;

  @FXML
  private Polygon O5_4;

  @FXML
  private Polygon O5_5;

  @FXML
  private Polygon O5_6;

  @FXML
  private Polygon O5_7;

  @FXML
  private Polygon O5_8;

  @FXML
  private Polygon O5_9;

  @FXML
  private Polygon O6_0;

  @FXML
  private Polygon O6_1;

  @FXML
  private Polygon O6_10;

  @FXML
  private Polygon O6_2;

  @FXML
  private Polygon O6_3;

  @FXML
  private Polygon O6_4;

  @FXML
  private Polygon O6_5;

  @FXML
  private Polygon O6_6;

  @FXML
  private Polygon O6_7;

  @FXML
  private Polygon O6_8;

  @FXML
  private Polygon O6_9;

  @FXML
  private Polygon O7_0;

  @FXML
  private Polygon O7_1;

  @FXML
  private Polygon O7_10;

  @FXML
  private Polygon O7_2;

  @FXML
  private Polygon O7_3;

  @FXML
  private Polygon O7_4;

  @FXML
  private Polygon O7_5;

  @FXML
  private Polygon O7_6;

  @FXML
  private Polygon O7_7;

  @FXML
  private Polygon O7_8;

  @FXML
  private Polygon O7_9;

  @FXML
  private Polygon O8_0;

  @FXML
  private Polygon O8_1;

  @FXML
  private Polygon O8_10;

  @FXML
  private Polygon O8_2;

  @FXML
  private Polygon O8_3;

  @FXML
  private Polygon O8_4;

  @FXML
  private Polygon O8_5;

  @FXML
  private Polygon O8_6;

  @FXML
  private Polygon O8_7;

  @FXML
  private Polygon O8_8;

  @FXML
  private Polygon O8_9;

  @FXML
  private Polygon O9_0;

  @FXML
  private Polygon O9_1;

  @FXML
  private Polygon O9_10;

  @FXML
  private Polygon O9_2;

  @FXML
  private Polygon O9_3;

  @FXML
  private Polygon O9_4;

  @FXML
  private Polygon O9_5;

  @FXML
  private Polygon O9_6;

  @FXML
  private Polygon O9_7;

  @FXML
  private Polygon O9_8;

  @FXML
  private Polygon O9_9;

  @FXML
  private Polygon O10;

  @FXML
  private Polygon O_11;

  @FXML
  private Polygon R0_0;

  @FXML
  private Polygon R0_1;

  @FXML
  private Polygon R0_2;

  @FXML
  private Polygon R0_3;

  @FXML
  private Polygon R0_4;

  @FXML
  private Polygon R0_5;

  @FXML
  private Polygon R0_6;

  @FXML
  private Polygon R0_7;

  @FXML
  private Polygon R0_8;

  @FXML
  private Polygon R0_9;

  @FXML
  private Polygon R1_0;

  @FXML
  private Polygon R1_1;

  @FXML
  private Polygon R1_2;

  @FXML
  private Polygon R1_3;

  @FXML
  private Polygon R1_4;

  @FXML
  private Polygon R1_5;

  @FXML
  private Polygon R1_6;

  @FXML
  private Polygon R1_7;

  @FXML
  private Polygon R1_8;

  @FXML
  private Polygon R1_9;

  @FXML
  private Polygon R2_0;

  @FXML
  private Polygon R2_1;

  @FXML
  private Polygon R2_2;

  @FXML
  private Polygon R2_3;

  @FXML
  private Polygon R2_4;

  @FXML
  private Polygon R2_5;

  @FXML
  private Polygon R2_6;

  @FXML
  private Polygon R2_7;

  @FXML
  private Polygon R2_8;

  @FXML
  private Polygon R2_9;

  @FXML
  private Polygon R3_0;

  @FXML
  private Polygon R3_1;

  @FXML
  private Polygon R3_2;

  @FXML
  private Polygon R3_3;

  @FXML
  private Polygon R3_4;

  @FXML
  private Polygon R3_5;

  @FXML
  private Polygon R3_6;

  @FXML
  private Polygon R3_7;

  @FXML
  private Polygon R3_8;

  @FXML
  private Polygon R3_9;

  @FXML
  private Polygon R4_0;

  @FXML
  private Polygon R4_1;

  @FXML
  private Polygon R4_2;

  @FXML
  private Polygon R4_3;

  @FXML
  private Polygon R4_4;

  @FXML
  private Polygon R4_5;

  @FXML
  private Polygon R4_6;

  @FXML
  private Polygon R4_7;

  @FXML
  private Polygon R4_8;

  @FXML
  private Polygon R4_9;

  @FXML
  private Polygon R5_0;

  @FXML
  private Polygon R5_1;

  @FXML
  private Polygon R5_2;

  @FXML
  private Polygon R5_3;

  @FXML
  private Polygon R5_4;

  @FXML
  private Polygon R5_5;

  @FXML
  private Polygon R5_6;

  @FXML
  private Polygon R5_7;

  @FXML
  private Polygon R5_8;

  @FXML
  private Polygon R5_9;

  @FXML
  private Polygon R6_0;

  @FXML
  private Polygon R6_1;

  @FXML
  private Polygon R6_2;

  @FXML
  private Polygon R6_3;

  @FXML
  private Polygon R6_4;

  @FXML
  private Polygon R6_5;

  @FXML
  private Polygon R6_6;

  @FXML
  private Polygon R6_7;

  @FXML
  private Polygon R6_8;

  @FXML
  private Polygon R6_9;

  @FXML
  private Polygon R7_0;

  @FXML
  private Polygon R7_1;

  @FXML
  private Polygon R7_2;

  @FXML
  private Polygon R7_3;

  @FXML
  private Polygon R7_4;

  @FXML
  private Polygon R7_5;

  @FXML
  private Polygon R7_6;

  @FXML
  private Polygon R7_7;

  @FXML
  private Polygon R7_8;

  @FXML
  private Polygon R7_9;

  @FXML
  private Polygon R8_0;

  @FXML
  private Polygon R8_1;

  @FXML
  private Polygon R8_2;

  @FXML
  private Polygon R8_3;

  @FXML
  private Polygon R8_4;

  @FXML
  private Polygon R8_5;

  @FXML
  private Polygon R8_6;

  @FXML
  private Polygon R8_7;

  @FXML
  private Polygon R8_8;

  @FXML
  private Polygon R8_9;

  @FXML
  private Polygon R9_0;

  @FXML
  private Polygon R9_1;

  @FXML
  private Polygon R9_2;

  @FXML
  private Polygon R9_3;

  @FXML
  private Polygon R9_4;

  @FXML
  private Polygon R9_5;

  @FXML
  private Polygon R9_6;

  @FXML
  private Polygon R9_7;

  @FXML
  private Polygon R9_8;

  @FXML
  private Polygon R9_9;

  @FXML
  private Polygon promptRhombus;

  @FXML
  private Polygon R9_11;

  @FXML
  private ScrollPane textualStrategyPane;

  @FXML
  private Button showBotStrategyButton;

  @FXML
  private Label turnLabel;

  @FXML
  private Button pieRuleButton; // added by Ioan




  private boolean pieRuleUsedOrExpired = false; // added by Ioan
  private Polygon highlightedBotCell = null;
  private boolean showStrategy = false;

  ArrayList<Polygon> changedColour;
  ArrayList<Paint> oldColour;
  boolean botStrategyButtonEnabled = true;

  Game game = new Game();

  @FXML
  void getCellID(MouseEvent event) {
    Polygon polygon = (Polygon) event.getSource();
    placeCell(polygon);
    if (game.isBlack() == game.getBot().isBlack()) {
      botMove();
    }
  }

  @FXML
  private void activatePieRule() { // added by Ioan - button event handler
    boolean canUsePieRule = !pieRuleUsedOrExpired &&
        game.getMoveCount() == 1 &&
        !game.isBlack();

    if (!canUsePieRule) {
      return;
    }

    pieRuleUsedOrExpired = true;

    // TODO: apply actual pie rule logic here
    // This usually means player 2 takes over player 1's first move / swaps sides.
    game.setBot(new Bot(game.getBot().getStrategy()));
    game.getBot().setBlack(false);
    botMove();

    updateTurnUI();
  }

  // added by Osama
  private void setPlayerTurnText(String text) {
    Color indicatorColor;
    if (game.isBlack()) {
      turnLabel.setText("Black's Turn");
      indicatorColor = Color.BLACK;
    } else {
      turnLabel.setText("White's Turn");
      indicatorColor = Color.WHITE;
    }
    promptOct.setFill(indicatorColor);
    promptRhombus.setFill(indicatorColor);
  }

  String getTurnText() {
    return game.isBlack() ? "Black's Turn" : "White's Turn";
  }

  private void placeCell(Polygon polygon) {
    if (!game.isGameOver()) {
      Color color = (game.isBlack()) ? Color.BLACK : Color.WHITE;

      // Get gui id
      String id = polygon.getId();
      // Get cell indices
      Boolean isRhombic = (id.charAt(0) == 'O') ? false : true;
      String[] tockens = id.substring(1).split("_");
      // Query and update Model
      Boolean success = game.placeCell(isRhombic, Integer.valueOf(tockens[0]), Integer.valueOf(tockens[1]));
      // Update View
      if (success) {
        polygon.setFill(color);

        if (changedColour != null && changedColour.contains(polygon)) {
          int index = changedColour.indexOf(polygon);
          changedColour.remove(index);
          oldColour.remove(index);
        }

        // If more than 1 move has been played, pie rule is no longer available. - added
        // by Ioan
        if (game.getMoveCount() > 1) {
          pieRuleUsedOrExpired = true;
        }
        updateTurnUI();

        if (game.isGameOver()) {
          displayWinner();
        } else {
          setPlayerTurnText(null);
        }
      } else {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Cell already occupied", ButtonType.OK);
        alert.showAndWait();
      }
    }
  }

  private void botMove() {
//    if (highlightedBotCell != null) {
//      if (Color.LIGHTGREEN.equals(highlightedBotCell.getFill())) {
//        highlightedBotCell.setFill(Color.web("#d0a60e"));
//      }
//      highlightedBotCell = null;
//    }
    if (showStrategy) {
      hidePaths();
    }
    Bot bot = game.getBot();
    bot.calculatePaths(game.getState());
    String id = bot.getNextMove().getAssociatedCellID();
    placeCell( (Polygon) getNodeWithID(id));
    bot.setLastMove(game.getBoardCellWithID(bot.getNextMove().getAssociatedCellID()));
    if (showStrategy) {
      showPaths();
    }
  }

  private Node getNodeWithID(String id) {
    for (Node o : O0_0.getParent().getChildrenUnmodifiable()) {
      if (o.getId() != null && o.getId().equals(id)){
        return o;
      }
    }
    return null;
  }

  private void displayWinner() {
    String winner = game.isBlackWins() ? "Black" : "White";
    turnLabel.setText(winner + " Wins!");

    ButtonType restartButton = new ButtonType("Restart");
    Alert alert = new Alert(Alert.AlertType.WARNING, winner + " Wins!", restartButton, ButtonType.OK);
    alert.setHeaderText("Game Over");
    alert.getDialogPane().setPrefSize(400, 200);
    alert.initModality(Modality.NONE);

    alert.setOnShown(e -> Platform.runLater(() -> {
      Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
      Rectangle2D screen = Screen.getPrimary().getVisualBounds();
      stage.setX((screen.getWidth() - stage.getWidth()) / 2);
      stage.setY((screen.getHeight() - stage.getHeight()) / 2);

      alert.getDialogPane().lookupButton(restartButton)
          .addEventFilter(MouseEvent.MOUSE_RELEASED, ev -> restartGame());
    }));

    alert.show();
  }

  private void restartGame() {
    resetColour();
    pieRuleUsedOrExpired = false;
    startGame();
  }

  private void resetColour() {
    if (showStrategy) {
      showBotStrategyButton();
    }
    changedColour = new ArrayList<Polygon>();
    oldColour = new ArrayList<Paint>();

    Pane pane = (Pane) O0_0.getParent();
    for (Node node : pane.getChildren()) {
      if (node instanceof Polygon) {
        Polygon p = (Polygon) node;
        String id = p.getId();
        if (id != null && (id.startsWith("O") || id.startsWith("R"))) {
          p.setFill(Color.web("#d0a60e"));
        }
      }
    }
  }

  @FXML
  public void initialize() {
    addBoardLabels();
    startGame();
  }

  private void startGame() {
    game = new Game();
    botMove();
    setPlayerTurnText(null);
  }

  private void addBoardLabels() {
    Pane pane = (Pane) O0_0.getParent();
    String[] letters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K" };

    // Board pixel boundaries (derived from polygon positions + scale)
    double boardLeft = 25;
    double boardRight = 995;
    double boardTop = 23;
    double boardBottom = 993;
    double frameEnd = 1025; // right/bottom edge of the visible border frame

    // Background border rectangles
    // Brown strips on left and right (behind the row numbers)
    int startX = (int) O0_0.getLayoutX() + 30;
    int startY = (int) O0_0.getLayoutY() + 30;
    Rectangle brownLeft = new Rectangle(startX, startY, boardLeft, frameEnd);
    brownLeft.setFill(Color.web("#8B4513"));

    Rectangle brownRight = new Rectangle(startX + boardRight, startY, frameEnd - boardRight, frameEnd);
    brownRight.setFill(Color.web("#8B4513"));

    // Black strips on top and bottom (behind the column labels, on top of brown at
    // corners)
    Rectangle blackTop = new Rectangle(startX, startY, frameEnd, boardTop);
    blackTop.setFill(Color.BLACK);

    Rectangle blackBottom = new Rectangle(startX, startY + boardBottom, frameEnd, frameEnd - boardBottom);
    blackBottom.setFill(Color.BLACK);

    // Insert at index 0 in reverse layering order so final order is:
    // [brownLeft, brownRight, blackTop, blackBottom, ...polygons...]
    // → brown renders first (back), black in front, polygons on top
    int numChildren = pane.getChildren().size();
    pane.getChildren().add(numChildren, blackBottom);
    pane.getChildren().add(numChildren, blackTop);
    pane.getChildren().add(numChildren, brownRight);
    pane.getChildren().add(numChildren, brownLeft);

    // --- Labels (added last so they render on top of everything) ---
    for (int col = 0; col < 11; col++) {
      double x = startX + 60 + col * 88;

      // Top row: A-K (white text — visible on black background)
      Text topLabel = new Text(x, startY + 15, letters[col]);
      topLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");
      topLabel.setFill(Color.WHITE);

      // Bottom row: A-K (white text — visible on black background)
      Text bottomLabel = new Text(x, startY + 1010, letters[col]);
      bottomLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");
      bottomLabel.setFill(Color.WHITE);

      pane.getChildren().addAll(topLabel, bottomLabel);
    }

    for (int row = 0; row < 11; row++) {
      double y = startY + 68 + row * 88;
      int number = 11 - row;

      // Left numbers (black text on brown background)
      Text leftLabel = new Text(startX + 5, y + 5, String.valueOf(number));
      leftLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");

      // Right numbers (black text on brown background)
      Text rightLabel = new Text(startX + 1000, y + 5, String.valueOf(number));
      rightLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");

      pane.getChildren().addAll(leftLabel, rightLabel);
    }
  }

  // added by Ioan - checks if the pie rule button should be shown on screen
  boolean shouldShowPieRuleButton() {
    return !pieRuleUsedOrExpired && game.getMoveCount() == 1 && !game.isBlack();
  }

  // added by Ioan - during Initialization it offers the option to activate the
  // pie rule to the first player
  private void updateTurnUI() {
    if (game.isBlack()) {
      turnLabel.setText("Black's Turn");
    } else {
      turnLabel.setText("White's Turn");
    }

    boolean showPieRule = shouldShowPieRuleButton();

    pieRuleButton.setVisible(showPieRule);
    pieRuleButton.setManaged(showPieRule);
  }
  @FXML
  private void showBotStrategyButton() {
    showStrategy = !showStrategy;
    if (showStrategy) {
      showBotStrategyButton.setText("Hide Bot Strategy");
      showBotStrategy();
    }else {
      showBotStrategyButton.setText("Show Bot Strategy");
      hidePaths();
    }
    textualStrategyPane.setVisible(!textualStrategyPane.isVisible());

  }

  private void showBotStrategy() {
    showPaths();
  }

  private void showPaths() {
    Bot bot = game.getBot();
    ArrayList<SearchNode> chosenPath = bot.getChosenPath();

    changedColour = new ArrayList<>();
    oldColour = new ArrayList<>();


    for (SearchNode node : chosenPath) {
      Polygon polygon = (Polygon) getNodeWithID(node.getAssociatedCellID());
      oldColour.add(polygon.getFill());
      changedColour.add(polygon);
      if (node.equals(chosenPath.getFirst())) {
        polygon.setFill(Color.DARKBLUE);
      }else {
        polygon.setFill(Color.LIGHTGREEN);
      }
    }

    ArrayList<ArrayList<SearchNode>> paths = bot.getPaths();
    paths.remove(chosenPath);

    Random random = new Random();
    for (ArrayList<SearchNode> currpath : paths) {

      Color color = Color.BLACK;
      do {
      color = Color.rgb(random.nextInt(100, 256), random.nextInt(100, 256)
              , random.nextInt(100, 256));
      }while (color.equals(Color.BLACK) || color.equals(Color.WHITE) || color.equals(Color.LIGHTGREEN));

      for (SearchNode node : currpath) {
        Polygon polygon = (Polygon) getNodeWithID(node.getAssociatedCellID());
        if (changedColour.contains(polygon)) {
          continue;
        }
        oldColour.add(polygon.getFill());
        changedColour.add(polygon);
        polygon.setFill(color);

      }
    }

  }

  private void hidePaths() {

    while (!changedColour.isEmpty()) {
      Polygon polygon = changedColour.removeFirst();
      polygon.setFill(oldColour.removeFirst());
    }

  }
/*
  public  Polygon getHighlightedBotCell(){
    return highlightedBotCell;
  }

 */

}
