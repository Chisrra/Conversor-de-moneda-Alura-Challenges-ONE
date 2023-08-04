package com.example.conversordemonedaalurachallengesone.controllers;

import com.example.conversordemonedaalurachallengesone.CurrencyData;
import com.example.conversordemonedaalurachallengesone.Finance;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the "TabMonedas" tab in the currency and temperature converter application.
 * This controller handles user interaction and updates the view accordingly for currency conversion.
 * It allows users to input amount in one currency and select another for conversion.
 * <p></p>
 * The implemented currencies are: "MXN", "USD", "CAD", "BRL", "EUR", "JPY"
 *
 * @author Chisrra
 * @version 1.0
 */
public class TabMonedasController implements Initializable {
    @FXML
    private TextField inTextDinero;
    @FXML
    private TextField outTextDinero;
    @FXML
    private ComboBox<String> BoxDivisaIn;
    @FXML
    private ComboBox<String> BoxDivisaOut;

    private final String[] divisas = {"MXN", "USD", "CAD", "BRL", "EUR", "JPY"};

    /**
     * Initializes the controller by populating the {@code ComboBox<String>} with the currency options
     *
     * @param url            The location use to resolve relatives paths for the root object, o {@code null} if the locations is unknown.
     * @param resourceBundle The resources used to localize the root object, o {@code null} if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BoxDivisaIn.getItems().addAll(divisas);
        BoxDivisaOut.getItems().addAll(divisas);
    }

    /**
     * Handles the "Convert" key typed action when the user types.
     * Check that all necessary information has been obtained and that is in a correct format, if not, notify the  user.
     * <p></p>
     * If all information is correct, it retrieves the selected currencies and the amount input to proceed with the currency conversion by calling the private method {@link #updateOutText(double, String, String)}
     */
    @FXML
    protected void checkOut() {
        if (inTextDinero.getText().isEmpty()) return;

        String divisaIn;
        String divisaOut;

        try {
            divisaIn = BoxDivisaIn.getValue().toLowerCase();
            divisaOut = BoxDivisaOut.getValue().toLowerCase();
        } catch (NullPointerException ex) {
            outTextDinero.setText("Seleccione su divisa y a la que desea convertir");
            return;
        }

        try {
            double dinero = Double.parseDouble(inTextDinero.getText());
            updateOutText(dinero, divisaIn, divisaOut);
        } catch (NumberFormatException ex) {
            outTextDinero.setText("Ingrese una cifra válida");
            ex.printStackTrace();
        }
    }

    /**
     * Perform the currency conversion and updates the output TextField with the converted amount.
     *
     * @param dinero    The input amount in the original currency
     * @param divisaIn  The original currency code (e.g. "mxn")
     * @param divisaOut The target currency code (e.g. "cad")
     * @throws RuntimeException If an error occurs during the currency conversion.
     * @see CurrencyData
     * @see Finance#convertCurrency(String, String)
     */
    private void updateOutText(double dinero, String divisaIn, String divisaOut) {
        CurrencyData balance;
        try {
            balance = Finance.convertCurrency(divisaIn, divisaOut);
        } catch (InterruptedException | IOException e) {
            System.err.println(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        try {
            double resultant = dinero * balance.currency();
            outTextDinero.setText(String.format("%.6f", resultant));
        } catch (AssertionError ex) {
            outTextDinero.setText("Hay problemas para hacer la conversión");
            ex.printStackTrace();
        }
    }


}
