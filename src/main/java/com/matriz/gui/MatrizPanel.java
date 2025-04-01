package com.matriz.gui;

import com.matriz.modelo.Matriz;

import javax.swing.*;
import java.awt.*;

/**
 * Panel personalizado para mostrar visualmente una matriz.
 * Responsabilidad única: visualizar una matriz (Principio S de SOLID).
 */
public class MatrizPanel extends JPanel {
    private Matriz matriz;
    private static final int CELDA_TAMANO = 40;
    private static final int PADDING = 10;
    private static final Font FUENTE = new Font("Monospaced", Font.BOLD, 16);

    /**
     * Constructor que inicializa el panel con una matriz.
     * @param matriz La matriz a visualizar.
     */
    public MatrizPanel(Matriz matriz) {
        this.matriz = matriz;
        setPreferredSize(new Dimension(
                matriz.getColumnas() * CELDA_TAMANO + PADDING * 2,
                matriz.getFilas() * CELDA_TAMANO + PADDING * 2
        ));
    }

    /**
     * Actualiza la matriz a visualizar.
     * @param matriz La nueva matriz.
     */
    public void setMatriz(Matriz matriz) {
        this.matriz = matriz;
        setPreferredSize(new Dimension(
                matriz.getColumnas() * CELDA_TAMANO + PADDING * 2,
                matriz.getFilas() * CELDA_TAMANO + PADDING * 2
        ));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Mejorar calidad de renderizado
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(FUENTE);

        // Dibujar celdas de la matriz
        for (int i = 0; i < matriz.getFilas(); i++) {
            for (int j = 0; j < matriz.getColumnas(); j++) {
                int x = j * CELDA_TAMANO + PADDING;
                int y = i * CELDA_TAMANO + PADDING;

                // Dibujar borde de celda
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.fillRect(x, y, CELDA_TAMANO, CELDA_TAMANO);
                g2d.setColor(Color.DARK_GRAY);
                g2d.drawRect(x, y, CELDA_TAMANO, CELDA_TAMANO);

                // Dibujar valor de la celda
                g2d.setColor(Color.BLACK);
                String valor = String.valueOf(matriz.getElemento(i, j));
                FontMetrics fm = g2d.getFontMetrics();
                int textX = x + (CELDA_TAMANO - fm.stringWidth(valor)) / 2;
                int textY = y + (CELDA_TAMANO + fm.getAscent() - fm.getDescent()) / 2;
                g2d.drawString(valor, textX, textY);
            }
        }
    }
}
