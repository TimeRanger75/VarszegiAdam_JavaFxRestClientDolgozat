module hu.petrik.varszegiadam_javafxrestclientdolgozat {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens hu.petrik.varszegiadam_javafxrestclientdolgozat to javafx.fxml;
    exports hu.petrik.varszegiadam_javafxrestclientdolgozat;
}