# Conversor de Monedas

Esta es una aplicación de consola en Java que permite convertir entre diferentes monedas. Utiliza la API de ExchangeRate-API para obtener tasas de cambio actualizadas.

## Requisitos

- **Java 11 o superior**
- **Librerías necesarias**:
    - Gson
    - Dotenv Java

## Librerías Necesarias

Debes descargar las siguientes librerías `JAR` y agregarlas manualmente al classpath de tu proyecto:

1. **Gson**: Para el manejo de JSON.
    - [Descargar Gson JAR](https://mvnrepository.com/artifact/com.google.code.gson/gson/2.8.8)
2. **Dotenv Java**: Para la gestión de variables de entorno.
    - [Descargar Dotenv Java JAR](https://mvnrepository.com/artifact/io.github.cdimascio/dotenv-java/2.2.1)

Coloca estos archivos `JAR` en el directorio `lib` de tu proyecto o cualquier otro directorio que uses para almacenar dependencias externas, y asegúrate de que estén incluidos en el classpath al compilar y ejecutar la aplicación.

## Configuración de Variables de Entorno

La aplicación utiliza variables de entorno para acceder a la API de tasas de cambio. Crea un archivo `.env` en la raíz del proyecto con el siguiente contenido:


Reemplaza `API_URL_VALUE` y `API_KEY_VALUE` con los valores proporcionados por [ExchangeRate-API](https://www.exchangerate-api.com).

## Uso de la Aplicación

1. **Inicia la Aplicación:**
   Ejecuta la clase `Main` para iniciar la aplicación en la consola.

2. **Menú Principal:**
    - **1. Seleccionar moneda de origen**: Elige la moneda desde la que se convertirá.
    - **2. Seleccionar moneda de destino**: Elige la moneda a la que se convertirá.
    - **3. Convertir moneda**: Realiza la conversión entre las monedas seleccionadas.
    - **4. Salir**: Termina la ejecución de la aplicación.

3. **Buscar Moneda:**
    - Puedes buscar monedas por código, país o por letras contenidas en el código de la moneda.

4. **Conversión de Moneda:**
    - Una vez seleccionadas ambas monedas, ingresa la cantidad a convertir y obtén el resultado de la conversión.

## Ejemplo de Uso

```bash
--- Menú Principal ---
1. Seleccionar moneda de origen
2. Seleccionar moneda de destino
3. Convertir moneda
4. Salir
   Seleccione una opción: 1

--- Seleccionar Moneda de Origen ---
1. Buscar moneda por código
2. Buscar moneda por país
3. Buscar moneda por código que contenga letras
4. Volver al menú principal
   Seleccione una opción: 1
   Ingrese el código de la moneda: usd
   Moneda de origen seleccionada: USD - United States Dollar (United States)

--- Menú Principal ---
1. Seleccionar moneda de origen
2. Seleccionar moneda de destino
3. Convertir moneda
4. Salir
   Seleccione una opción: 2

--- Seleccionar Moneda de Destino ---
1. Buscar moneda por código
2. Buscar moneda por país
3. Buscar moneda por código que contenga letras
4. Volver al menú principal
   Seleccione una opción: 2
   Ingrese el país: guatemala
   Moneda de destino seleccionada: GTQ - Guatemalan Quetzal (Guatemala)

--- Menú Principal ---
1. Seleccionar moneda de origen
2. Seleccionar moneda de destino
3. Convertir moneda
4. Salir
   Seleccione una opción: 3

--- Conversión ---
Va a realizar una conversión de United States Dollar a Guatemalan Quetzal
Ingrese la cantidad en United States Dollar: 2
2.00 United States Dollar es igual a 15.47 Guatemalan Quetzal
```

## Notas

- Asegúrate de que el archivo `.env` esté correctamente configurado antes de ejecutar la aplicación.
- Las tasas de cambio se obtienen de la API de [ExchangeRate-API](https://www.exchangerate-api.com).

## Contacto

Para cualquier duda o problema, puedes contactar al autor del proyecto en [tu_email@example.com](mailto:tu_email@example.com).
