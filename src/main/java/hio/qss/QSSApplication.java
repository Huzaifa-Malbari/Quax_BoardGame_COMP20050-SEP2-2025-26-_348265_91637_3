
package hio.qss;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class QSSApplication extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(QSSApplication.class.getResource("board.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 1209, 1170);
    stage.setTitle("Hello!");
    stage.setScene(scene);
    stage.setMaximized(true);
    stage.show();
  }
}
