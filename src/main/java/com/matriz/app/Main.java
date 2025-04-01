package com.matriz.app;

import com.matriz.gui.MatrizFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Clase principal que inicia la aplicación.
 * Responsabilidad única: punto de entrada de la aplicación (Principio S de SOLID).
 */
public class Main {
    /**
     * Metodo principal de la aplicación.
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Intentar establecer el look and feel nativo del sistema
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Crear y mostrar la ventana principal
            MatrizFrame frame = new MatrizFrame();
            frame.setVisible(true);
        });
    }
}