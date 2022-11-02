package com.elimanjaya.scheduler;

import DAO.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class implements an application for a scheduler system.
 */
public class SchedulerMain extends Application {
    /**
     * This method is used to begin the execution of thread.
     * @param stage This is the current stage.
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SchedulerMain.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();

        DBConnection.openConnection();
    }

    /**
     * This method is the starting point for JVM to start execution of a Java program.
     * The javadoc folder is located at the root level of this project.
     * @param args This is the command line argument.
     */
    public static void main(String[] args) {
        launch();
    }
}