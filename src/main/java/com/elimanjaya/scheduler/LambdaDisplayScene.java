package com.elimanjaya.scheduler;

import javafx.event.ActionEvent;

/**
 * This interface provides the abstract method to display another scene.
 */
public interface LambdaDisplayScene {
    /**
     * This abstract method is used to display another scene.
     * @param s fxml file name for the next scene.
     * @param ae ActionEvent
     * @param w Width of the next scene.
     * @param h Height of the next scene.
     */
    void goToNewScene(String s, ActionEvent ae, int w, int h);
}
