package com.matriz.interfaces;

/**
 * Interfaz que define la operación de transpuesta.
 * Siguiendo el Principio de Segregación de Interfaces (I de SOLID).
 */
public interface ITransponible<T> {
    /**
     * Calcula la transpuesta de la matriz.
     * @return Una nueva instancia que representa la transpuesta.
     */
    T transpuesta();
}