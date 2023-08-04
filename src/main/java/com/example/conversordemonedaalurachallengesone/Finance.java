package com.example.conversordemonedaalurachallengesone;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * This interface provides methods to perform currency conversions using exchange rate web service that is update once a day.
 *
 * @author Chisrra
 */
public interface Finance {

    /**
     * Converts a source currency to a target currency.
     *
     * @param from The source currency.
     * @param to   The target currency.
     * @return A {@code CurrencyData} object containing tha conversion information.
     * @throws InterruptedException If the execution of the application is interrupted.
     * @throws IOException          If an input/output error occurs during the HTTP request.
     */
    static CurrencyData convertCurrency(String from, String to) throws InterruptedException, IOException {
        if (from == null || to == null) {
            throw new NullPointerException("The parameters 'from' and 'to' cannot be null.");
        }

        try {
            String url = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/" + from + "/" + to + ".json";
            String jsonResponse = sendGETRequest(new URI(url));
            Map<String, String> response = parseJson(jsonResponse);
            return new CurrencyData(response.get("date"), from, to, Double.parseDouble(response.get(to)));
        } catch (IOException ex) {
            System.err.println("Input/output error during HTTP request: " + ex.getMessage());
            throw ex;
        } catch (URISyntaxException ex) {
            System.err.println("invalid URI: " + ex.getMessage());
            throw new InterruptedException();
        } catch (InterruptedException ex) {
            System.err.println("The execution of the request was interrupted: " + ex.getMessage());
            throw ex;
        }
    }

    /**
     * Sends a GET request to the URL provided and return the response in JSON format.
     *
     * @param uri The {@code URI} of the request.
     * @return The response of the request in JSON format.
     * @throws IOException          If an input/output error occurs during the request.
     * @throws InterruptedException If the execution of the request is interrupted.
     */
    private static String sendGETRequest(URI uri) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /**
     * Parses the JSON response and returns a map with the extracted data.
     *
     * @param jsonResponse The JSON response as a string
     * @return A {@code Map<String, String>} containing the data extracted from the JSON.
     */
    private static Map<String, String> parseJson(String jsonResponse) {
        Gson gson = new Gson();
        // Use a TypeToken to indicate that the result is a Map<String, String>.
        return gson.fromJson(jsonResponse, new TypeToken<Map<String, String>>() {
        }.getType());
    }
}
