package br.com.malu;

import java.net.http.*;
import java.net.URI;
import java.net.http.HttpResponse.BodyHandlers;

/**
 * ==========================================================
 *  Projeto: Atividade 4 - Computação em Nuvem e Serviços Cognitivos
 *  Disciplina: Computação em Nuvem e Serviços Cognitivos - PUC Minas
 *  Autora: Maria Luiza
 *  Descrição:
 *      Este programa realiza uma análise de sentimento utilizando
 *      o serviço Azure Cognitive Services (Language - Text Analytics).
 *      O código envia um texto em português para a API do Azure,
 *      que identifica se o sentimento predominante é positivo,
 *      neutro ou negativo. O resultado é exibido no console.
 *
 *  Etapas do funcionamento:
 *   1. Define o endpoint e a chave de autenticação do Azure.
 *   2. Envia o texto em formato JSON para o endpoint da API.
 *   3. Recebe uma resposta JSON com o resultado da análise.
 *   4. Exibe o sentimento detectado no console.
 * ==========================================================
 */

public class AnaliseSentimento {
    public static void main(String[] args) throws Exception {
     
        String endpoint = "https://analisesentimentomalu.cognitiveservices.azure.com/";
        String key = "************"; // chave removida por segurança


        // URL da API de análise de sentimento
        String url = endpoint + "text/analytics/v3.0/sentiment";

        // Texto que será analisado (compatível com Java 11)
        String texto = "{ \"documents\": [ { \"id\": \"1\", \"language\": \"pt\", \"text\": \"Hoje o dia está maravilhoso, estou muito feliz!\" } ] }";

        // Monta a requisição HTTP
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Content-Type", "application/json")
            .header("Ocp-Apim-Subscription-Key", key)
            .POST(HttpRequest.BodyPublishers.ofString(texto))
            .build();

        // Envia a requisição e recebe a resposta
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        // Exibe o resultado da IA com formatação visual
        System.out.println("\n===============================================");
        System.out.println("💡  ANÁLISE DE SENTIMENTO - AZURE COGNITIVE API");
        System.out.println("===============================================");
        System.out.println("📤 Texto enviado: \"Hoje o dia está maravilhoso, estou muito feliz!\"\n");
        System.out.println("📥 Resposta da API:");
        System.out.println(response.body());
        System.out.println("===============================================\n");
    }
}
