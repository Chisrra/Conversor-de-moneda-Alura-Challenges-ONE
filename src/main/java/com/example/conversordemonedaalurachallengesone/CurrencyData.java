package com.example.conversordemonedaalurachallengesone;

/**
 * Record representing the data of a currency conversion transaction.
 * Data includes the date of the transaction, the source and destination exchange rates, and  the amount of currency converted.
 *
 * @param date The date of the transaction in string format.
 * @param exchangeRateFrom The currency source  exchange in string format.
 * @param exchangeRateTo The target exchange currency in string format.
 * @param currency The amount of the currency converted in decimal number format.
 */
public record CurrencyData(String date, String exchangeRateFrom, String exchangeRateTo, double currency) {

}
