module com.elimanjaya.scheduler {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.elimanjaya.scheduler to javafx.fxml;
    exports com.elimanjaya.scheduler;
}