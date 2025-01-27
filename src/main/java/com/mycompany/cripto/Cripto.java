
package com.mycompany.cripto;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.math.BigInteger;
import java.util.Random;

public class Cripto {

    public static void main(String[] args) {
        // Números grandes para probar
        // Las gráficas están en la carpeta del proyecto en documentos
        BigInteger[] numeros = {
            //new BigInteger("730750818665451459101878079669820141388620103979"),
            new BigInteger("170141183460469232386546718332573188473"),
            new BigInteger("5070602400913188947864361895043"),
            new BigInteger("2475880078575440071286063989"),
            new BigInteger("36893488349282566399")
        };

        // Dataset para la gráfica
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Factorización de cada número y medición del tiempo
        for (BigInteger numero : numeros) {
            System.out.println("Factorizando el numero: " + numero);

            long tiempoInicio = System.nanoTime();
            BigInteger factor = algoritmoRhoPollard(numero);
            long tiempoFin = System.nanoTime();

            long tiempoEjecucion = (tiempoFin - tiempoInicio) / 1_000_000; // Tiempo en ms
            System.out.println("Tiempo de ejecucion: " + tiempoEjecucion + " ms");

            // Validar y agregar datos al dataset
            if (tiempoEjecucion > 0) {
                dataset.addValue(tiempoEjecucion, "Tiempo de ejecucióo", numero.toString().length() + " dígitos");
            }

            // Mostrar factores encontrados
            if (factor != null) {
                System.out.println("Factor encontrado: " + factor);
                System.out.println("Otro factor: " + numero.divide(factor));
            } else {
                System.out.println("No se encontro un factor.");
            }
            System.out.println("---------------------------------------------------");
        }

        // Crear la gráfica
        JFreeChart chart = ChartFactory.createLineChart(
                "Tiempo de ejecucion vs Tamaño del numero",
                "Tamaño del numero (digitos)",
                "Tiempo de ejecucion (ms)",
                dataset
        );

        // Guardar la gráfica como imagen
        try {
            ChartUtils.saveChartAsPNG(new File("grafica_tiempo_vs_tamano.png"), chart, 800, 600);
            System.out.println("Grafica generada: grafica_tiempo_vs_tamano.png");
        } catch (Exception e) {
            System.out.println("Error al guardar la grafica: " + e.getMessage());
        }
    }

    // Implementación del algoritmo rho de Pollard
    public static BigInteger algoritmoRhoPollard(BigInteger numero) {
        if (numero.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return BigInteger.TWO; // Si es divisible por 2
        }

        BigInteger x = numeroAleatorio(numero); // Valor inicial aleatorio para x
        BigInteger y = x; // Valor inicial para y
        BigInteger constante = numeroAleatorio(numero); // Constante aleatoria
        BigInteger divisor = BigInteger.ONE;

        while (divisor.equals(BigInteger.ONE)) {
            x = funcionF(x, constante, numero); // x = (x^2 + constante) % numero
            y = funcionF(funcionF(y, constante, numero), constante, numero); // y = f(f(y))
            divisor = x.subtract(y).abs().gcd(numero); // divisor = gcd(|x-y|, numero)

            if (divisor.equals(numero)) {
                return null; // No se encontró factor
            }
        }

        return divisor; // Factor encontrado
    }

    // Función f(x) = (x^2 + constante) % numero
    private static BigInteger funcionF(BigInteger x, BigInteger constante, BigInteger numero) {
        return x.multiply(x).add(constante).mod(numero);
    }

    // Generador de un BigInteger aleatorio menor que numero
    private static BigInteger numeroAleatorio(BigInteger numero) {
        Random aleatorio = new Random();
        BigInteger resultado;
        do {
            resultado = new BigInteger(numero.bitLength(), aleatorio);
        } while (resultado.compareTo(BigInteger.ZERO) <= 0 || resultado.compareTo(numero) >= 0);
        return resultado;
    }
}
