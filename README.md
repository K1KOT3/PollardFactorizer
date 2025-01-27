# Pollard Factorizer

Este proyecto implementa el algoritmo rho de Pollard para la factorización de números grandes, una técnica clave en criptografía y ciberseguridad. También genera una gráfica que muestra cómo varía el tiempo de ejecución del algoritmo según el tamaño del número.

## Descripción del Proyecto

El objetivo principal de este proyecto es:
1. **Factorizar números grandes** utilizando el algoritmo rho de Pollard.
2. **Analizar el rendimiento del algoritmo** al medir el tiempo de ejecución para números de diferentes tamaños.
3. **Visualizar los resultados** en una gráfica generada automáticamente.

La implementación incluye números de hasta 48 dígitos para pruebas, aunque puede ser extendida a números más grandes según sea necesario.

## Tecnologías Utilizadas

- **Java:** Lenguaje principal para la implementación.
- **JFreeChart:** Biblioteca para la generación de gráficas.
- **BigInteger:** Para manejar números extremadamente grandes.

## Ejecución del Proyecto

### Requisitos
- **Java 8** o superior instalado.
- **Maven** configurado (opcional para compilación).

### Pasos
1. Clona el repositorio:
   ```bash
   git clone https://github.com/K1KOT3/PollardFactorizer.git
   cd PollardFactorizer
   ```
2. Compila y ejecuta el proyecto:
   ```bash
   mvn clean compile
   mvn exec:java -Dexec.mainClass="com.mycompany.cripto.Cripto"
   ```
3. Observa los resultados en la consola: Factores encontrados y tiempo de ejecución para cada número.
4. Una gráfica llamada grafica_tiempo_vs_tamano.png será generada en la carpeta del proyecto.

### Ejemplo de Salida
```bash
Factorizando el número: 100000000000001300000000000004209
Factor encontrado: 10000000000000061
Otro factor: 10000000000000049
Tiempo de ejecución: 120 ms
---------------------------------------------------
```
### Contribuciones
Si deseas contribuir, crea un pull request o abre un issue en GitHub.


