package com.example.conversordemonedaalurachallengesone;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TabMonedasController implements Initializable {
    @FXML
    private TextField inDinero;
    @FXML
    private TextField outDinero;
    @FXML
    private ComboBox<String> inDivisa;
    @FXML
    private ComboBox<String> outDivisa;

    private final String[] divisas = {"MXN", "USD", "CAD", "BRL", "EUR", "JPY"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inDivisa.getItems().addAll(divisas);
        outDivisa.getItems().addAll(divisas);
    }

    @FXML
    protected void checkOut() {
        String dineroText = inDinero.getText();
        try {
            double dinero = Double.parseDouble(dineroText);
            updateOut(dinero);
        }catch (NumberFormatException ex) {
            outDinero.setText("Ingrese una cifra válida");
        }
    }

    private void updateOut(double dinero) {
        double conversionRate = 0.05;
        double resultado = dinero * conversionRate;
        outDinero.setText(String.format("%.2f", resultado));
    }



}
