module hio.qss {
  requires javafx.controls;
  requires javafx.fxml;
    requires java.desktop;

    opens hio.qss to javafx.fxml;

  exports hio.qss;
}
