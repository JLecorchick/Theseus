module RoomCycleProject {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;

    opens projfxmldemo to javafx.fxml;
    opens projfxmldemo.controllers to javafx.fxml;

    exports projfxmldemo;
    exports projfxmldemo.controllers;
}