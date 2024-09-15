package com.challenge.models;

/**
 * La clase `Converter` se encarga de realizar la conversión de una cantidad de dinero de una moneda a otra utilizando las tasas de conversión entre las monedas.
 */
public class Converter implements Conversion {

    private Currency origin;
    private Currency destiny;

    /**
     * Constructor que inicializa el objeto `Converter` con las monedas de origen y destino.
     *
     * @param origin La moneda de origen desde la cual se convertirá la cantidad.
     * @param destiny La moneda de destino a la cual se convertirá la cantidad.
     */
    public Converter(Currency origin, Currency destiny) {
        this.origin = origin;
        this.destiny = destiny;
    }

    /**
     * Convierte una cantidad de dinero de la moneda de origen a la moneda de destino.
     *
     * @param mountToConvert La cantidad de dinero en la moneda de origen que se desea convertir.
     * @return La cantidad convertida en la moneda de destino.
     */
    @Override
    public double convertMount(double mountToConvert) {
        return this.origin.getConversionRates().get(destiny.getCodeCurrency()) * mountToConvert;
    }
}
