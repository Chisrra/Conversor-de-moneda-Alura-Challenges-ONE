package com.example.conversordemonedaalurachallengesone.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

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
        String divisaIn;
        String divisaOut;
        String dineroText;

        try {
            divisaIn = BoxDivisaIn.getValue().toLowerCase();
            divisaOut = BoxDivisaOut.getValue().toLowerCase();
        }catch(NullPointerException ex) {
            outTextDinero.setText("Seleccione su divisa y a la que desea convertir");
            return;
        }

        dineroText = inTextDinero.getText();
        try {
            double dinero = Double.parseDouble(dineroText);
            updateOut(dinero, divisaIn, divisaOut);
        }catch (NumberFormatException ex) {
            outTextDinero.setText("Ingrese una cifra válida");
        }
    }

    private void updateOut(double dinero, String divisaIn, String divisaOut) {


        double conversionRate = 0.05;
        double resultado = dinero * conversionRate;
        outTextDinero.setText(String.format("%.2f", resultado));
    }



}
