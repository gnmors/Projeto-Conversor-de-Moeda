package br.com.alura.conversor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class MinhaAPI {
    


    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://v6.exchangerate-api.com/v6/76bafad47950f991dc9a2b32/latest/"))
            .build();
}
