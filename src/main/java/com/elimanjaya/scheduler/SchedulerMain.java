package com.elimanjaya.scheduler;

import DAO.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SchedulerMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SchedulerMain.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        DBConnection.openConnection();
    }

    public static void main(String[] args) {
        launch();
    }
}