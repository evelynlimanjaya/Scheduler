module com.elimanjaya.scheduler {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.elimanjaya.scheduler to javafx.fxml;
    opens controller to javafx.fxml ;
    exports com.elimanjaya.scheduler;
    exports controller;
    exports model;
    opens model to javafx.fxml;
}