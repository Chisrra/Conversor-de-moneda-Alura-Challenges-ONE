package com.example.conversordemonedaalurachallengesone.controllers;

import com.example.conversordemonedaalurachallengesone.Temperature;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the "TabTemperaruta" tab in the currency and temperature convert application.
 * This controller handles the user interactions and updates the view accordingly for the temperature conversion.
 * It allows user input a temperature values in one unit and select another unit for conversion.
 * The supported temperature units are: Celsius(°C), Fahrenheit(°F) and Kelvin(°K).
 *
 * @author Chisrra
 * @version 1.0
 */
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

    /**
     * Initialize the controller by populating the {@code ComboBox<String, String> with temperature unit options.*
     * @param url            The location used to resolve relative paths for the root object, or {@code null} if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or {@code null} if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BoxDegreesIn.getItems().addAll(degreesOptions);
        BoxDegreesOut.getItems().addAll(degreesOptions);
    }

    /**
     * Handle the "Convert" key typed action when the user types.
     * Check all information necessary has been obtained and that is in the correct format, if not, notify the user.
     * <p></p>
     * If all the information is correct, it retrieves the selected currencies and the amount input to proceed with the currency conversion by the calling private method {@link #upDateOutText(double, String, String)}.
     */
    @FXML
    protected void checkOut() {
        if (inTemperature.getText().isEmpty()) return;

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

    /**
     * Perform the temperature conversion and update the output TextField with the converted temperature.
     *
     * @param degrees The input temperature value.
     * @param degreesIn The input temperature unit (e.g. "°C").
     * @param degreesOut The target temperature unit (e.g. "°F").
     * @see Temperature
     */
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

    /**
     * Format the temperature value to {@code String} with a precision of six decimals places.
     *
     * @param degrees The temperature value to format
     * @return The formatted temperature value as a {@code String}.
     */
    private String formatResult(double degrees) {
        return String.format("%.6f", degrees);
    }

}
