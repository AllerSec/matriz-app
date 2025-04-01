package com.matriz.gui;

import com.matriz.modelo.Matriz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Ventana principal de la aplicación.
 * Responsabilidad única: interfaz gráfica (Principio S de SOLID).
 */
public class MatrizFrame extends JFrame {
    private Matriz matriz;
    private MatrizPanel panelMatriz;
    private MatrizPanel panelTranspuesta;
    private JTextArea txtMatriz;
    private JTextArea txtTranspuesta;
    private JButton btnCrearMatriz;
    private JButton btnTransponer;

    /**
     * Constructor que inicializa la ventana con una matriz por defecto.
     */
    public MatrizFrame() {
        // Matriz por defecto
        matriz = new Matriz(new int[][]{{1, 2}, {3, 4}});

        // Configuración de la ventana
        setTitle("Aplicación de Matrices");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel de entrada de datos
        JPanel panelEntrada = new JPanel(new BorderLayout());
        panelEntrada.setBorder(BorderFactory.createTitledBorder("Entrada de Matriz"));

        txtMatriz = new JTextArea(5, 20);
        txtMatriz.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtMatriz.setText("1 2\n3 4");
        panelEntrada.add(new JScrollPane(txtMatriz), BorderLayout.CENTER);

        btnCrearMatriz = new JButton("Crear Matriz");
        btnCrearMatriz.addActionListener(e -> crearMatriz());
        panelEntrada.add(btnCrearMatriz, BorderLayout.SOUTH);

        // Panel de visualización
        JPanel panelVisualizacion = new JPanel(new GridLayout(1, 2, 10, 10));
        panelVisualizacion.setBorder(BorderFactory.createTitledBorder("Visualización"));

        // Panel para la matriz original
        JPanel panelOriginal = new JPanel(new BorderLayout());
        panelOriginal.setBorder(BorderFactory.createTitledBorder("Matriz Original"));
        panelMatriz = new MatrizPanel(matriz);
        panelOriginal.add(panelMatriz, BorderLayout.CENTER);

        // Panel para la matriz transpuesta
        JPanel panelTrans = new JPanel(new BorderLayout());
        panelTrans.setBorder(BorderFactory.createTitledBorder("Matriz Transpuesta"));
        panelTranspuesta = new MatrizPanel(matriz.transpuesta());
        panelTrans.add(panelTranspuesta, BorderLayout.CENTER);

        panelVisualizacion.add(panelOriginal);
        panelVisualizacion.add(panelTrans);

        // Panel de resultados textuales
        JPanel panelResultados = new JPanel(new GridLayout(1, 2, 10, 10));
        panelResultados.setBorder(BorderFactory.createTitledBorder("Resultados"));

        // Área de texto para matriz original
        JPanel panelTextOriginal = new JPanel(new BorderLayout());
        panelTextOriginal.setBorder(BorderFactory.createTitledBorder("Representación Textual"));
        txtMatriz = new JTextArea(5, 20);
        txtMatriz.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtMatriz.setEditable(false);
        txtMatriz.setText(matriz.imprimir());
        panelTextOriginal.add(new JScrollPane(txtMatriz), BorderLayout.CENTER);

        // Área de texto para matriz transpuesta
        JPanel panelTextTrans = new JPanel(new BorderLayout());
        panelTextTrans.setBorder(BorderFactory.createTitledBorder("Representación Textual"));
        txtTranspuesta = new JTextArea(5, 20);
        txtTranspuesta.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtTranspuesta.setEditable(false);
        txtTranspuesta.setText(matriz.transpuesta().imprimir());
        panelTextTrans.add(new JScrollPane(txtTranspuesta), BorderLayout.CENTER);

        panelResultados.add(panelTextOriginal);
        panelResultados.add(panelTextTrans);

        // Panel de operaciones
        JPanel panelOperaciones = new JPanel();
        panelOperaciones.setBorder(BorderFactory.createTitledBorder("Operaciones"));

        btnTransponer = new JButton("Transponer");
        btnTransponer.addActionListener(e -> transponerMatriz());
        panelOperaciones.add(btnTransponer);

        // Añadir todo a la ventana
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelPrincipal.add(panelEntrada, BorderLayout.NORTH);
        panelPrincipal.add(panelVisualizacion, BorderLayout.CENTER);
        panelPrincipal.add(panelResultados, BorderLayout.SOUTH);

        add(panelPrincipal, BorderLayout.CENTER);
        add(panelOperaciones, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Crea una matriz a partir del texto ingresado.
     */
    private void crearMatriz() {
        try {
            String[] filas = txtMatriz.getText().trim().split("\n");
            int numFilas = filas.length;

            // Determinar número de columnas
            String[] primeraFila = filas[0].trim().split("\\s+");
            int numColumnas = primeraFila.length;

            // Crear matriz
            int[][] elementos = new int[numFilas][numColumnas];

            for (int i = 0; i < numFilas; i++) {
                String[] valores = filas[i].trim().split("\\s+");
                if (valores.length != numColumnas) {
                    throw new IllegalArgumentException("Todas las filas deben tener el mismo número de columnas");
                }
                for (int j = 0; j < numColumnas; j++) {
                    elementos[i][j] = Integer.parseInt(valores[j]);
                }
            }

            matriz = new Matriz(elementos);
            actualizarVista();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al crear la matriz: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Transpone la matriz actual y actualiza la vista.
     */
    private void transponerMatriz() {
        matriz = matriz.transpuesta();
        actualizarVista();
    }

    /**
     * Actualiza todos los elementos visuales con la matriz actual.
     */
    private void actualizarVista() {
        // Actualizar paneles gráficos
        panelMatriz.setMatriz(matriz);
        panelTranspuesta.setMatriz(matriz.transpuesta());

        // Actualizar áreas de texto
        txtMatriz.setText(matriz.imprimir());
        txtTranspuesta.setText(matriz.transpuesta().imprimir());

        // Redimensionar ventana
        pack();
        setLocationRelativeTo(null);
    }
}