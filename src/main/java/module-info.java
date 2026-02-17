module hio.qss {
    requires javafx.controls;
    requires javafx.fxml;


    opens hio.qss to javafx.fxml;
    exports hio.qss;
}