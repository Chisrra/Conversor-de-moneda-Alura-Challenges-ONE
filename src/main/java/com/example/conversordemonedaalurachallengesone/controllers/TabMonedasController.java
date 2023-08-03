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
 * Pending
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BoxDivisaIn.getItems().addAll(divisas);
        BoxDivisaOut.getItems().addAll(divisas);
    }

    @FXML
    protected void checkOut() {
        if(inTextDinero.getText().isEmpty()) return;

        String divisaIn;
        String divisaOut;

        try {
            divisaIn = BoxDivisaIn.getValue().toLowerCase();
            divisaOut = BoxDivisaOut.getValue().toLowerCase();
        }catch(NullPointerException ex) {
            outTextDinero.setText("Seleccione su divisa y a la que desea convertir");
            return;
        }

        try {
            double dinero = Double.parseDouble(inTextDinero.getText());
            updateOutText(dinero, divisaIn, divisaOut);
        }catch (NumberFormatException ex) {
            outTextDinero.setText("Ingrese una cifra válida");
            ex.printStackTrace();
        }
    }

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
            double resultado = dinero * balance.currency();
            outTextDinero.setText(String.format("%.6f", resultado));
        } catch (AssertionError ex) {
            outTextDinero.setText("Hay problemas para hacer la conversión");
            ex.printStackTrace();
        }
    }



}
