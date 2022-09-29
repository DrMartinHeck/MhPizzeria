module heck.mhpizzaria {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.mariadb.jdbc;



    opens heck.mhpizzeria to javafx.fxml;
    exports heck.mhpizzeria;
    exports heck.mhpizzeria.gui;
    opens heck.mhpizzeria.gui to javafx.fxml;
    //-----------------------------------
    opens heck.mhpizzeria.model to javafx.base;
    exports heck.mhpizzeria.logic;
    opens heck.mhpizzeria.logic to javafx.fxml;
}