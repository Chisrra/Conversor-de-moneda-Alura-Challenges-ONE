package com.example.conversordemonedaalurachallengesone;

/**
 * Interface providing methods to perform temperature conversions between different scales.
 */
public interface Temperature {

    /**
     * Converts a temperature in degrees Celsius to degrees Fahrenheit.
     *
     * @param celsius The temperature in degrees Celsius.
     * @return The temperature converted to degrees Fahrenheit
     */
    static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5.0) + 32;
    }

    /**
     * Convert a temperature in degrees Celsius to degrees Kelvin.
     *
     * @param celsius The temperature in degrees Celsius.
     * @return The temperature converted to degrees Kelvin.
     */
    static double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    /**
     * Convert a temperature in degrees Fahrenheit to degrees Celsius.
     *
     * @param fahrenheit The temperature in degrees fahrenheit.
     * @return The temperature converted to degrees Celsius.
     */
    static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9.0;
    }

    /**
     * Convert a temperature in degrees Fahrenheit to degrees Kelvin.
     *
     * @param fahrenheit The temperature in degrees Fahrenheit.
     * @return The temperature converted to degrees Kelvin.
     */
    static double fahrenheitToKelvin(double fahrenheit) {
        return fahrenheitToCelsius(fahrenheit) + 273.15;
    }

    /**
     * Convert a temperature in degrees Kelvin to degrees Celsius.
     *
     * @param kelvin The temperature in degrees Kelvin.
     * @return The temperature converted to Celsius.
     */
    static double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    /**
     * Convert the temperature in degrees Kelvin to degrees Fahrenheit.
     *
     * @param kelvin The temperature in degrees Kelvin.
     * @return The temperature converted to degrees Fahrenheit.
     */
    static double kelvinToFahrenheit(double kelvin) {
        return celsiusToFahrenheit(kelvinToCelsius(kelvin));
    }
}
