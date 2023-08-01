package com.example.conversordemonedaalurachallengesone;

public interface Temperature {
    static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5.0) + 32;
    }

    static double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    static double fahrenheitToCelsiuc(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9.0;
    }

    static double fahrenheitToKelvin(double fahrenheit) {
        return fahrenheitToCelsiuc(fahrenheit) + 273.15;
    }

    static double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    static double kelvinToFahrenheit(double kelvin) {
        return celsiusToFahrenheit(kelvinToCelsius(kelvin));
    }
}
