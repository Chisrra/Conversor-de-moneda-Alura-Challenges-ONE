package com.example.conversordemonedaalurachallengesone.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TabTemperaturaController implements Initializable {
    @FXML
    private TextField inTemperatura;
    @FXML
    private TextField outTemperatura;
    @FXML
    private ComboBox<String> BoxGradosIn;
    @FXML
    private ComboBox<String> BoxGradosOut;

    private final String[] grados = {"°C", "°F", "°K"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BoxGradosIn.getItems().addAll(grados);
        BoxGradosOut.getItems().addAll(grados);
    }

    @FXML
    protected void checkOut() {
        String gradosIn;
        String gradosOut;

        try {
            gradosIn = BoxGradosIn.getValue().toLowerCase();
            gradosOut = BoxGradosOut.getValue().toLowerCase();
        } catch (NullPointerException ex) {
            outTemperatura.setText("Seleccione la temperatura de entrada y la de salida");
            return;
        }

        try {
            double grados = Double.parseDouble(inTemperatura.getText());
            upDateOutText(grados, gradosIn, gradosOut);
        } catch (NumberFormatException ex) {
            outTemperatura.setText("Ingrese una cifra valida");
            System.err.println(ex);
            ex.printStackTrace();
        }
    }

    private void upDateOutText(double grados, String gradosIn, String gradosOut) {

    }

}
