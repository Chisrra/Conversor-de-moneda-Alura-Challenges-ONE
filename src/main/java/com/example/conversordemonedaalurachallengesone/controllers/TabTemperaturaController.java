package com.example.conversordemonedaalurachallengesone.controllers;

import com.example.conversordemonedaalurachallengesone.Temperature;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TabTemperaturaController implements Initializable {
    @FXML
    private TextField inTemperature;
    @FXML
    private TextField outTemperature;
    @FXML
    private ComboBox<String> BoxDegreesIn;
    @FXML
    private ComboBox<String> BoxDegreesOut;

    private final String[] degreesOptions = {"°C", "°F", "°K"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BoxDegreesIn.getItems().addAll(degreesOptions);
        BoxDegreesOut.getItems().addAll(degreesOptions);
    }

    @FXML
    protected void checkOut() {
        if(inTemperature.getText().isEmpty()) return;

        String degreesIn;
        String degreesOut;

        try {
            degreesIn = BoxDegreesIn.getValue().toUpperCase();
            degreesOut = BoxDegreesOut.getValue().toUpperCase();
        } catch (NullPointerException ex) {
            outTemperature.setText("Seleccione la temperatura de entrada y la de salida");
            return;
        }

        try {
            double grados = Double.parseDouble(inTemperature.getText());
            upDateOutText(grados, degreesIn, degreesOut);
        } catch (NumberFormatException ex) {
            outTemperature.setText("Ingrese una cifra valida");
            ex.printStackTrace();
        }
    }

    private void upDateOutText(double degrees, String degreesIn, String degreesOut) {

        if (degreesIn.equals("°C") && degreesOut.equals("°F")) {
            outTemperature.setText(formatResult(Temperature.celsiusToFahrenheit(degrees)));
            return;
        }
        if (degreesIn.equals("°C") && degreesOut.equals("°K")) {
            outTemperature.setText(formatResult(Temperature.celsiusToKelvin(degrees)));
            return;
        }
        if (degreesIn.equals("°F") && degreesOut.equals("°C")) {
            outTemperature.setText(formatResult(Temperature.fahrenheitToCelsius(degrees)));
            return;
        }
        if (degreesIn.equals("°F") && degreesOut.equals("°K")) {
            outTemperature.setText(formatResult(Temperature.fahrenheitToKelvin(degrees)));
            return;
        }
        if (degreesIn.equals("°K") && degreesOut.equals("°C")) {
            outTemperature.setText(formatResult(Temperature.kelvinToCelsius(degrees)));
            return;
        }
        if (degreesIn.equals("°K") && degreesOut.equals("°F")) {
            outTemperature.setText(formatResult(Temperature.kelvinToFahrenheit(degrees)));
            return;
        }

        outTemperature.setText(formatResult(degrees));

    }

    private String formatResult(double degrees) {
        return String.format("%.6f", degrees);
    }

}
