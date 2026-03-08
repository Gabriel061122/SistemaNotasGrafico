package com.sistemanotasgrafico.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaPrincipal {

    private JFrame ventanaPrincipal;

    private JPanel panelNotas;
    private JPanel panelAcciones;
    private JPanel panelContenido;
    private JPanel panelTitulo;

    private JTextArea textoContenido;
    private JTextField textoTitulo;
    private JTextArea textoNota;
    private JButton botonCrearNota;
    private JButton botonEditarNota;
    private JButton botonEliminarNota;
    private JButton botonLimpiarCampos;
    private JButton botonBuscar;
    private JButton botonBorrarNotas;
    private JButton botonActualizar;

    public VentanaPrincipal() {
        ventanaPrincipal = new JFrame("Ventana Principal");
        ventanaPrincipal.setSize(900, 500);
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textoNota = new JTextArea();
        textoContenido = new JTextArea();
        textoTitulo = new JTextField();

        botonCrearNota = new JButton("Crear Nota");
        botonEditarNota = new JButton("Editar Nota");
        botonEliminarNota = new JButton("Eliminar Nota");
        botonLimpiarCampos = new JButton("Limpiar Campos");
        botonBuscar = new JButton("Buscar");
        botonBorrarNotas = new JButton("Borrar");
        botonActualizar = new JButton("Actualizar");


        panelNotas = new JPanel();
        panelAcciones = new JPanel();
        panelContenido = new JPanel();
        panelTitulo = new JPanel(new BorderLayout(10, 10));

        panelNotas.setLayout(new BorderLayout(5, 5));
        panelContenido.setLayout(new BorderLayout(5, 5));

        panelTitulo.add(new JLabel("Titulo de la nota:"), BorderLayout.WEST);
        panelTitulo.add(textoTitulo, BorderLayout.CENTER);

        panelNotas.add(new JLabel("Nota:"), BorderLayout.NORTH);
        panelNotas.add(new JScrollPane(textoNota), BorderLayout.CENTER);
        panelContenido.add(new JLabel("Contenido:"), BorderLayout.NORTH);
        panelContenido.add(new JScrollPane(textoContenido), BorderLayout.CENTER);
        panelAcciones.add(botonCrearNota);
        panelAcciones.add(botonEditarNota);
        panelAcciones.add(botonEliminarNota);
        panelAcciones.add(botonLimpiarCampos);
        panelAcciones.add(botonBuscar);
        panelAcciones.add(botonBorrarNotas);
        panelAcciones.add(botonActualizar);
        ventanaPrincipal.add(panelTitulo, BorderLayout.NORTH);
        ventanaPrincipal.add(panelNotas, BorderLayout.WEST);
        ventanaPrincipal.add(panelAcciones, BorderLayout.SOUTH);
        ventanaPrincipal.add(panelContenido, BorderLayout.EAST);

    }

    public void mostrarVentana(){
        ventanaPrincipal.setVisible(true);
    }

    public void aniadirEventoCrearNota(ActionListener l){
        botonCrearNota.addActionListener(l);
    }

    public void aniadirEventoEditarNota(ActionListener l){
        botonEditarNota.addActionListener(l);
    }

    public void aniadirEventoEliminarNota(ActionListener l){
        botonEliminarNota.addActionListener(l);
    }

    public void aniadirEventoLimpiarCampos(ActionListener l){
        botonLimpiarCampos.addActionListener(l);
    }

    public void aniadirEventoBuscar(ActionListener l){
        botonBuscar.addActionListener(l);
    }

    public void aniadirEventoBorrarNotas(ActionListener l){
        botonBorrarNotas.addActionListener(l);
    }

    public void aniadirEventoActualizar(ActionListener l){
        botonActualizar.addActionListener(l);
    }
}
