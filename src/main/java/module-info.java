module hu.petrik.varszegiadam_javafxrestclientdolgozat {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens hu.petrik.varszegiadam_javafxrestclientdolgozat to javafx.fxml, com.google.gson;
    exports hu.petrik.varszegiadam_javafxrestclientdolgozat;
}