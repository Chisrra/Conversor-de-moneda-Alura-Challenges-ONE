package com.example.conversordemonedaalurachallengesone;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Pending
 */
public interface Finance {

    static @Nullable CurrencyData convertCurrency(String from, String to) throws NullPointerException{
        try{
            String url = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/"+from+"/"+to+".json";
            String jsonResponse = sendGETRequest(new URI(url));
            Map<String, String> response = parseJson(jsonResponse);
            return new CurrencyData(response.get("date"), from, to, Double.parseDouble(response.get(to)));


        }catch (IOException | URISyntaxException | InterruptedException ex) {
            System.err.println("Unable to connect to the server");
            ex.printStackTrace();
        }

        return null;
    }

    private static String sendGETRequest(URI uri) throws IOException, InterruptedException  {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private static Map<String, String> parseJson(String jsonResponse) {
        Gson gson = new Gson();
        // Utilizar un TypeToken para indicar que el resultado es un Map<String, Double>
        return gson.fromJson(jsonResponse, new TypeToken<Map<String, String>>() {}.getType());
    }
}
