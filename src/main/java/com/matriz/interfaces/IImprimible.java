package com.matriz.interfaces;

/**
 * Interfaz que define las operaciones de impresión de una matriz.
 * Siguiendo el Principio de Segregación de Interfaces (I de SOLID).
 */
public interface IImprimible {
    /**
     * Imprime la matriz en formato legible.
     * @return Representación en cadena de texto de la matriz.
     */
    String imprimir();
}
