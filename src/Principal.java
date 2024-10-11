import br.com.alura.conversor.MinhaAPI;
import br.com.alura.conversor.MoedaResponse;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String idioma;
        String continuar;

        System.out.println("Escolha o idioma / Choose your language:\n" +
                "1 - Português (PT-BR)\n"+
                "2 - English (ENG)");
        idioma = scanner.nextInt() == 1 ? "PT-BR" : "ENG";
        scanner.nextLine();

        System.out.println(idioma.equals("PT-BR") ?
                """
                
                Bem-Vindo ao conversor de moedas!
                *********************************
                Esse conversor aceita:
                Reais (BRL)
                Dólares Americanos (USD)
                Dólares Canadenses (CAD)
                Euro (EUR)
                Iene japonês (JPY)
                Pesos Argentinos (ARS)
                *********************************
                """
                :
                """
                
                Welcome to the currency converter!
                *********************************
                This converter accepts:
                Brazilian Reais (BRL)
                US Dollars (USD)
                Canadian Dollars (CAD)
                Euros (EUR)
                Japanese Yen (JPY)
                Argentinian Pesos (ARS)
                *********************************
                """
        );


        do {
            System.out.print(idioma.equals("PT-BR") ? "Digite a moeda de origem (ex: USD): " : "Enter the base currency (e.g., USD): ");
            String moeda1 = scanner.nextLine().toUpperCase();
            System.out.print(idioma.equals("PT-BR") ? "Digite a moeda para conversão (ex: BRL): " : "Enter the target currency (e.g., BRL): ");
            String moeda2 = scanner.nextLine().toUpperCase();
            System.out.print(idioma.equals("PT-BR") ? "Digite o valor que deseja converter: " : "Enter the amount you want to convert: ");
            double valorParaConverter = scanner.nextDouble();
            scanner.nextLine();

            try {
                MoedaResponse moedaResponse = MinhaAPI.getMoedaResponse(moeda1);
                double taxaConversao = moedaResponse.getConversion_rates().getRateFor(moeda2);
                double valorConvertido = valorParaConverter * taxaConversao;
                System.out.println(idioma.equals("PT-BR") ?
                        "A taxa de conversão de " + moeda1 + " para " + moeda2 + " é: " + taxaConversao + "\nO valor convertido é: " + valorConvertido
                        :
                        "The conversion rate from " + moeda1 + " to " + moeda2 + " is: " + taxaConversao + "\nThe converted amount is: " + valorConvertido);

            } catch (NoSuchFieldException e) {
                System.out.println(idioma.equals("PT-BR") ?
                        "Moeda " + moeda2 + " não encontrada."
                        :
                        "Currency " + moeda2 + " not found.");
            } catch (IllegalAccessException e) {
                System.out.println(idioma.equals("PT-BR") ?
                        "Erro ao acessar a taxa de conversão: " + e.getMessage()
                        :
                        "Error accessing the conversion rate: " + e.getMessage());
            } catch (Exception e) {
                System.out.println(idioma.equals("PT-BR") ?
                        "Erro ao processar a requisição: " + e.getMessage()
                        :
                        "Error processing the request: " + e.getMessage());}
            System.out.println(idioma.equals("PT-BR") ? "Deseja realizar outra conversão? Digite sim ou nao:" : "Do you want to perform another conversion? Type yes or no:");
            continuar = scanner.nextLine().trim().toLowerCase();

        }
        while (continuar.equals(idioma.equals("PT-BR") ? "sim" : "yes"));

        System.out.println(idioma.equals("PT-BR") ? "Obrigado! Aplicação concluída." : "Thank you! Application finished.");
        scanner.close();
    }
}

