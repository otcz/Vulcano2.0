package com.company.front;

import com.company.control.DatosTiro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListadoDatosfrm extends JFrame implements ActionListener {
    private static final long serialVersionUID = 7675022490591504183L;

    private final JTable tabla;
    private final JScrollPane barras;
    private final DefaultTableModel modelo;
    private final JButton boton;
    private final String[] columnas = new String[]{"Blanco", "Arma", "OA", "Distancia", "Carga"};

    public ListadoDatosfrm() {
        // Inicializamos los elementos de la interfaz
        tabla = new JTable();
        barras = new JScrollPane(tabla);
        modelo = new DefaultTableModel();
        boton = new JButton("Imprimir datos");
        setSize(750, 750);
        setResizable(false);
        setVisible(true);
        setLocation(450, 200);

        // Ponemos el nombre de las columnas en el modelo
        modelo.setColumnIdentifiers(columnas);

        // Establecemos el modelo de la tabla
        tabla.setModel(modelo);

        // Agregamos datos a la tabla
        this.agregarDatos();

        // Configuramos la ventana principal
        this.prepararVentana();

        // Agregamos elementos a la interfaz
        this.agregarElementos();

        // Agregamos el manejador de evento al boton
        boton.addActionListener(this);
    }

    /**
     * Agregamos los elementos a la interfaz grafica
     */
    private void agregarElementos() {
        this.getContentPane().add(barras, BorderLayout.CENTER);
        this.getContentPane().add(boton, BorderLayout.SOUTH);
    }

    /**
     * Configuramos la ventana
     */
    private void prepararVentana() {
        this.setTitle("Listado de Datos");
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
    }

    /**
     * Agreamos datos a la tabla
     */
    private void agregarDatos() {

        for (Integer i = 0; i < 100; i++) {
            String mensaje = i + " ";
            // Creamos un nuevo renglon para la tabla
            String[] datos = {i.toString(), mensaje};
            // Agregamos los datos a la tabla
            modelo.addRow(new String[]{mensaje});


        }

    }

    /**
     * Manejador del evento del boton, al activarse obtiene un dato de la tabla
     * y lo imprime en la consola
     *
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        // Obtenemos el primer dato del renglon seleccionado
      /*  if (tabla.getSelectedRow() != -1) {
            String codigo = (String) modelo.getValueAt(tabla.getSelectedRow(), 0);

            // Lo imprimimos en pantalla
            System.out.println(codigo);
        } else {
            System.out.println("Seleccione un renglon primero");
        }*/
    }

}