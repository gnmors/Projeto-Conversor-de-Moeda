import br.com.alura.conversor.MinhaAPI;
import br.com.alura.conversor.MoedaResponse;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String continuar;

        System.out.println("Bem-Vindo ao conversor de moedas!" +
        "*********************************\n" +
        "Esse conversor aceita:\n" +
        "Reais (BRL)\n" +
        "Dólares Americanos (USD)\n" +
        "Dólares Canadenses (CAD)\n" +
        "Euro (EUR)\n" +
        "Iene japonês (JPY)\n" +
        "Pesos Argentinos (ARS)\n" +
        "*********************************\n");

        do {
            System.out.print("Digite a moeda de origem (ex: USD): ");
            String moeda1 = scanner.nextLine().toUpperCase();
            System.out.print("Digite a moeda para conversão (ex: BRL): ");
            String moeda2 = scanner.nextLine().toUpperCase();
            System.out.print("Digite o valor que deseja fazer a conversão: ");
            double valorParaConverter = scanner.nextDouble();
            scanner.nextLine();

            try {
                MoedaResponse moedaResponse = MinhaAPI.getMoedaResponse(moeda1);
                double taxaConversao = moedaResponse.getConversion_rates().getRateFor(moeda2);
                double valorConvertido = valorParaConverter * taxaConversao;
                System.out.println("A taxa de conversão de " + moeda1 + " para " + moeda2 + " é: " + taxaConversao + "\nO valor convertido é: " + valorConvertido);

            } catch (NoSuchFieldException e) {
                System.out.println("Moeda " + moeda2 + " não encontrada.");
            } catch (IllegalAccessException e) {
                System.out.println("Erro ao acessar a taxa de conversão: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro ao processar a requisição: " + e.getMessage());
            }
            System.out.println("Deseja realizar outra conversão? Digite sim ou nao:");
            continuar = scanner.nextLine().trim().toLowerCase();

        } while (continuar.equals("sim"));

        System.out.println("Obrigado! Aplicação concluída.");
        scanner.close();
    }
}
