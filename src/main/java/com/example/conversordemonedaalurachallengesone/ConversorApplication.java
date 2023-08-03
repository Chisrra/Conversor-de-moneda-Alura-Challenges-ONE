package com.example.conversordemonedaalurachallengesone;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main class of the currency and temperature converter application.
 * This class extends {@link javafx.application.Application} and serves as the entry point for the JavaFX application.
 */
public class ConversorApplication extends Application {

    /**
     * Starts the JavaFX application by loading the user interface and displaying the main stage.
     *
     * @param stage The primary stage for this application, to be set by the JavaFX runtime.
     * @throws IOException If an I/O error occurs during the loading of the user interface FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ConversorApplication.class.getResource("interfaz-conversor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        stage.setTitle("Conversor de monedas y temperatura");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args The command-line arguments. They are not used in this application.
     */
    public static void main(String[] args) {
        launch();
    }
}