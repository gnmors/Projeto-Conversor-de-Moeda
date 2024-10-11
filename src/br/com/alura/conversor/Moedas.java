package br.com.alura.conversor;

public record Moedas(double USD, double ARS, double BRL, double EUR, double CAD, double JPY) {

    public double getRateFor(String currency) throws NoSuchFieldException, IllegalAccessException {
        return this.getClass().getDeclaredField(currency).getDouble(this);
    }
}
