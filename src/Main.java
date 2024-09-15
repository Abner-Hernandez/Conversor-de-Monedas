import com.challenge.models.Converter;
import com.challenge.models.Currency;
import com.challenge.models.CurrencyCode;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static CurrencyCode selectedOriginCurrency;
    private static CurrencyCode selectedDestCurrency;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Seleccionar moneda de origen");
            System.out.println("2. Seleccionar moneda de destino");
            System.out.println("3. Convertir moneda");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int option = readInteger(scanner);

            switch (option) {
                case 1:
                    selectCurrency("origen", scanner);
                    break;

                case 2:
                    selectCurrency("destino", scanner);
                    break;

                case 3:
                    convertCurrency(scanner);
                    break;

                case 4:
                    System.out.println("Saliendo...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    /**
     * Permite seleccionar una moneda de origen o destino.
     * @param type "origen" o "destino" para especificar el tipo de moneda a seleccionar
     * @param scanner Objeto Scanner para leer la entrada del usuario
     */
    private static void selectCurrency(String type, Scanner scanner) {
        System.out.println("\n--- Seleccionar Moneda de " + (type.equals("origen") ? "Origen" : "Destino") + " ---");

        CurrencyCode[] selectedCurrencies = null;

        while (selectedCurrencies == null || selectedCurrencies.length == 0) {
            System.out.println("1. Buscar moneda por código");
            System.out.println("2. Buscar moneda por país");
            System.out.println("3. Buscar moneda por código que contenga letras");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int option = readInteger(scanner);

            // Limpiar el buffer del scanner para asegurar que se pueda leer el texto correctamente después del número
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Ingrese el código de la moneda: ");
                    String code = scanner.nextLine();
                    selectedCurrencies = searchByCode(code);
                    break;

                case 2:
                    System.out.print("Ingrese el país: ");
                    String country = scanner.nextLine();
                    selectedCurrencies = searchByCountry(country);
                    break;

                case 3:
                    System.out.print("Ingrese las letras para buscar en el código: ");
                    String letters = scanner.nextLine();
                    selectedCurrencies = searchByLetters(letters);
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }

            if (selectedCurrencies.length == 1) {
                // Selección automática si solo hay una coincidencia
                if (type.equals("origen")) {
                    selectedOriginCurrency = selectedCurrencies[0];
                } else {
                    selectedDestCurrency = selectedCurrencies[0];
                }
                System.out.println("Moneda " + (type.equals("origen") ? "de origen" : "de destino") + " seleccionada: " + selectedCurrencies[0].name() + " - " + selectedCurrencies[0].getDescription() + " (" + selectedCurrencies[0].getCountry() + ")");
                return;
            } else if (selectedCurrencies.length > 1) {
                // Solicitar al usuario que elija una de las opciones
                System.out.println("Seleccione una moneda de la lista:");
                for (int i = 0; i < selectedCurrencies.length; i++) {
                    System.out.println((i + 1) + ". " + selectedCurrencies[i].name() + " - " + selectedCurrencies[i].getDescription() + " (" + selectedCurrencies[i].getCountry() + ")");
                }
                System.out.print("Ingrese el número de la opción: ");
                int choice = readInteger(scanner);

                // Limpiar el buffer del scanner para asegurar que se pueda leer el texto correctamente después del número
                scanner.nextLine();

                if (choice >= 1 && choice <= selectedCurrencies.length) {
                    CurrencyCode chosenCurrency = selectedCurrencies[choice - 1];
                    if (type.equals("origen")) {
                        selectedOriginCurrency = chosenCurrency;
                    } else {
                        selectedDestCurrency = chosenCurrency;
                    }
                    System.out.println("Moneda " + (type.equals("origen") ? "de origen" : "de destino") + " seleccionada: " + chosenCurrency.name() + " - " + chosenCurrency.getDescription() + " (" + chosenCurrency.getCountry() + ")");
                } else {
                    System.out.println("Opción no válida. Intente nuevamente.");
                }
            } else {
                System.out.println("No se encontraron monedas con los criterios especificados. Intente nuevamente.");
            }
        }
    }

    /**
     * Realiza la conversión de moneda entre la moneda de origen y la moneda de destino seleccionadas.
     * @param scanner Objeto Scanner para leer la entrada del usuario
     */
    private static void convertCurrency(Scanner scanner) {
        if (selectedOriginCurrency == null || selectedDestCurrency == null) {
            System.out.println("Debe seleccionar ambas monedas (origen y destino) antes de realizar la conversión.");
            return;
        }

        System.out.println("\n--- Conversión ---");
        System.out.println("Va a realizar una conversión de " + selectedOriginCurrency.getDescription() + " a " + selectedDestCurrency.getDescription());

        System.out.print("Ingrese la cantidad en " + selectedOriginCurrency.getDescription() + ": ");
        double amount = readDouble(scanner);

        Currency origin = new Currency(selectedOriginCurrency.name());
        Currency destiny = new Currency(selectedDestCurrency.name());
        Converter converter = new Converter(origin, destiny);
        double convertedAmount = converter.convertMount(amount);

        System.out.printf("%.2f %s es igual a %.2f %s%n",
                amount, selectedOriginCurrency.getDescription(),
                convertedAmount, selectedDestCurrency.getDescription());
    }

    /**
     * Lee un número entero del usuario. Maneja entradas inválidas.
     * @param scanner Objeto Scanner para leer la entrada del usuario
     * @return El número entero ingresado por el usuario
     */
    private static int readInteger(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                scanner.next(); // Limpiar el buffer del scanner
            }
        }
    }

    /**
     * Lee un número decimal del usuario. Maneja entradas inválidas.
     * @param scanner Objeto Scanner para leer la entrada del usuario
     * @return El número decimal ingresado por el usuario
     */
    private static double readDouble(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número decimal.");
                scanner.next(); // Limpiar el buffer del scanner
            }
        }
    }

    /**
     * Busca monedas por código.
     * @param code Código de la moneda a buscar
     * @return Un arreglo de monedas que coinciden con el código ingresado
     */
    public static CurrencyCode[] searchByCode(String code) {
        return Arrays.stream(CurrencyCode.values())
                .filter(currency -> currency.name().equalsIgnoreCase(code))
                .toArray(CurrencyCode[]::new);
    }

    /**
     * Busca monedas por país.
     * @param country Nombre del país a buscar
     * @return Un arreglo de monedas que coinciden con el país ingresado
     */
    public static CurrencyCode[] searchByCountry(String country) {
        return Arrays.stream(CurrencyCode.values())
                .filter(currency -> currency.getCountry().equalsIgnoreCase(country))
                .toArray(CurrencyCode[]::new);
    }

    /**
     * Busca monedas cuyos códigos contengan las letras especificadas.
     * @param letters Letras que deben estar en el código de la moneda
     * @return Un arreglo de monedas cuyos códigos contienen las letras ingresadas
     */
    public static CurrencyCode[] searchByLetters(String letters) {
        return Arrays.stream(CurrencyCode.values())
                .filter(currency -> currency.name().contains(letters.toUpperCase()))
                .toArray(CurrencyCode[]::new);
    }
}
