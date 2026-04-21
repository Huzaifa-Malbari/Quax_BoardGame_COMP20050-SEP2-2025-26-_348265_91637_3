module hio.qss {
  requires javafx.controls;
  requires javafx.fxml;
    requires java.desktop;
    requires jdk.compiler;

    opens hio.qss to javafx.fxml;

  exports hio.qss;
}
