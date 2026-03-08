package com.sistemanotasgrafico.vista;

import javax.swing.*;
import java.awt.event.ActionListener;

public class VentanaPrincipal {

    private JFrame ventanaPrincipal;

    private JPanel panelNotas;
    private JPanel panelAcciones;
    private JPanel panelContenido;

    private JTextArea textoContenido;
    private JTextArea textoNota;
    private JButton botonCrearNota;
    private JButton botonEditarNota;
    private JButton botonEliminarNota;
    private JButton botonLimpiarCampos;
    private JButton botonBuscar;
    private JButton botonBorrarNotas;

    public VentanaPrincipal() {
        ventanaPrincipal = new JFrame("Ventana Principal");
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textoNota = new JTextArea();
        textoContenido = new JTextArea();


        botonCrearNota = new JButton("Crear Nota");
        botonEditarNota = new JButton("Editar Nota");
        botonEliminarNota = new JButton("Eliminar Nota");
        botonLimpiarCampos = new JButton("Limpiar Campos");
        botonBuscar = new JButton("Buscar");
        botonBorrarNotas = new JButton("Borrar");

        panelNotas = new JPanel();
        panelAcciones = new JPanel();
        panelContenido = new JPanel();

        panelNotas.add(textoNota);
        panelContenido.add(textoContenido);
        panelAcciones.add(botonCrearNota);
        panelAcciones.add(botonEditarNota);
        panelAcciones.add(botonEliminarNota);
        panelAcciones.add(botonLimpiarCampos);
        panelAcciones.add(botonBuscar);
        panelAcciones.add(botonBorrarNotas);
        ventanaPrincipal.add(panelNotas);
        ventanaPrincipal.add(panelAcciones);
        ventanaPrincipal.add(panelContenido);

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
}
