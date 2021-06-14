module MurderMysteryHelper.main {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires afterburner.fx;
    requires opencsv;


    opens driver to javafx.fxml;

    exports driver;
}