package com.challenge.models;

import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

/**
 * Representa una moneda y proporciona métodos para obtener tasas de conversión desde una API externa.
 */
public class Currency {

    private String codeCurrency;
    private HashMap<String, Double> conversionRates;

    /**
     * Constructor que inicializa el objeto Currency con el código de una moneda específica.
     * Luego, carga las tasas de conversión desde una API externa.
     *
     * @param codeCurrency El código de la moneda (por ejemplo, "USD" para el dólar estadounidense).
     */
    public Currency(String codeCurrency) {
        this.codeCurrency = codeCurrency;
        this.conversionRates = this.getConversionRatesFromCurrency().conversion_rates();
    }

    /**
     * Obtiene el código de la moneda.
     *
     * @return El código de la moneda como un String.
     */
    public String getCodeCurrency() {
        return codeCurrency;
    }

    /**
     * Obtiene las tasas de conversión para la moneda actual.
     *
     * @return Un HashMap con las tasas de conversión, donde la clave es el código de la moneda
     *         y el valor es la tasa de conversión correspondiente.
     */
    public HashMap<String, Double> getConversionRates() {
        return conversionRates;
    }

    /**
     * Obtiene las tasas de conversión desde una API externa utilizando el código de la moneda actual.
     * Realiza una solicitud HTTP a la API y procesa la respuesta JSON para extraer las tasas de conversión.
     *
     * @return Un objeto ConversionRate que contiene las tasas de conversión para la moneda especificada.
     * @throws RuntimeException Si ocurre un error al obtener o procesar la respuesta de la API.
     */
    private ConversionRate getConversionRatesFromCurrency() {
        Dotenv dotenv = Dotenv.load();
        String url = dotenv.get("API_URL") + "/" + dotenv.get("API_KEY") + "/latest/" + this.codeCurrency;
        URI direction = URI.create(url);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direction)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return new Gson().fromJson(response.body(), ConversionRate.class);
        } catch (Exception e) {
            throw new RuntimeException("No se encontraron las tasas de cambio.");
        }
    }
}
