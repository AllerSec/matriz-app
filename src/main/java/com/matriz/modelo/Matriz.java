package com.matriz.modelo;

import com.matriz.interfaces.IImprimible;
import com.matriz.interfaces.ITransponible;

/**
 * Clase que representa una matriz de enteros.
 * Implementa los principios SOLID:
 * - S: Tiene una única responsabilidad (representar una matriz)
 * - O: Está abierta a extensión (se pueden agregar nuevas operaciones)
 * - L: Puede ser sustituida por subclases sin alterar comportamiento
 * - I: Implementa interfaces segregadas según funcionalidad
 * - D: Depende de abstracciones (interfaces) no de implementaciones concretas
 */
public class Matriz implements IImprimible, ITransponible<Matriz> {
    private final int[][] elementos;
    private final int filas;
    private final int columnas;

    /**
     * Constructor que inicializa la matriz con un arreglo bidimensional.
     * @param elementos Arreglo bidimensional de enteros.
     */
    public Matriz(int[][] elementos) {
        // Validación de matriz válida
        if (elementos == null || elementos.length == 0) {
            throw new IllegalArgumentException("La matriz no puede ser nula o vacía");
        }

        this.filas = elementos.length;
        this.columnas = elementos[0].length;

        // Validación de matriz rectangular
        for (int[] fila : elementos) {
            if (fila.length != this.columnas) {
                throw new IllegalArgumentException("Todas las filas deben tener el mismo número de columnas");
            }
        }

        // Copia profunda para evitar modificaciones externas
        this.elementos = new int[filas][columnas];
        for (int i = 0; i < filas; i++) {
            System.arraycopy(elementos[i], 0, this.elementos[i], 0, columnas);
        }
    }

    /**
     * Obtiene el elemento en la posición especificada.
     * @param fila Índice de la fila.
     * @param columna Índice de la columna.
     * @return El valor en la posición especificada.
     */
    public int getElemento(int fila, int columna) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            throw new IndexOutOfBoundsException("Índices fuera de rango");
        }
        return elementos[fila][columna];
    }

    /**
     * Obtiene el número de filas de la matriz.
     * @return Número de filas.
     */
    public int getFilas() {
        return filas;
    }

    /**
     * Obtiene el número de columnas de la matriz.
     * @return Número de columnas.
     */
    public int getColumnas() {
        return columnas;
    }

    @Override
    public String imprimir() {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                resultado.append(elementos[i][j]);
                if (j < columnas - 1) {
                    resultado.append(" ");
                }
            }
            if (i < filas - 1) {
                resultado.append("\n");
            }
        }
        return resultado.toString();
    }

    @Override
    public Matriz transpuesta() {
        int[][] nuevaMatriz = new int[columnas][filas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                nuevaMatriz[j][i] = elementos[i][j];
            }
        }
        return new Matriz(nuevaMatriz);
    }
}