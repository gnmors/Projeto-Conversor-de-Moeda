package br.com.alura.conversor;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MinhaAPI {
    public static MoedaResponse getMoedaResponse(String moeda1) throws Exception {

    String apiUrl = "https://v6.exchangerate-api.com/v6/76bafad47950f991dc9a2b32/latest/" + moeda1;
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(String.valueOf(new URI(apiUrl))))
            .GET()
            .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

     if (response.statusCode() == 200) {
        Gson gson = new Gson();
        return gson.fromJson(response.body(), MoedaResponse.class);
    } else {
        throw new Exception("Falha ao obter a resposta da API. Código de status: " + response.statusCode());
    }
    }
}
