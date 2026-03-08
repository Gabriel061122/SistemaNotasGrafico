package com.sistemanotasgrafico.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaInicial {

    private JFrame ventana;
    private JPanel panelPrincipal;
    private JButton botonIniciar;
    private JButton botonRegistrar;
    private JButton botonSalir;

    public VentanaInicial(){
        ventana = new JFrame("Inicio de Sesión");
        ventana.setSize(500,500);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

        botonIniciar = new JButton("Iniciar Sesión");
        botonRegistrar = new JButton("Registrar Usuario");
        botonSalir = new JButton("Salir");

        JLabel labelTitulo = new JLabel("Sistema de Notas");
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel labelInstruccion = new JLabel("Selecciona una opción:");
        labelInstruccion.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));
        panelBotones.add(botonIniciar);
        panelBotones.add(botonRegistrar);
        panelBotones.add(botonSalir);

        panelPrincipal.add(Box.createVerticalStrut(20));
        panelPrincipal.add(labelTitulo);
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(labelInstruccion);
        panelPrincipal.add(Box.createVerticalStrut(20));
        panelPrincipal.add(panelBotones);
        ventana.add(panelPrincipal);

    }

    public void iniciar(){
        ventana.setVisible(true);
    }

    public void cerrarVentana() {
        ventana.setVisible(false);
    }

    public void  aniadirEventoIniciar(ActionListener l){
        botonIniciar.addActionListener(l);
    }

    public void  aniadirEventoRegistrar(ActionListener l){
        botonRegistrar.addActionListener(l);
    }

    public void  aniadirEventoSalir(ActionListener l){
        botonSalir.addActionListener(l);
    }

    public JFrame getVentana() {
        return ventana;
    }

    public void setVentana(JFrame ventana) {
        this.ventana = ventana;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JButton getBotonIniciar() {
        return botonIniciar;
    }

    public void setBotonIniciar(JButton botonIniciar) {
        this.botonIniciar = botonIniciar;
    }

    public JButton getBotonRegistrar() {
        return botonRegistrar;
    }

    public void setBotonRegistrar(JButton botonRegistrar) {
        this.botonRegistrar = botonRegistrar;
    }

    public JButton getBotonSalir() {
        return botonSalir;
    }

    public void setBotonSalir(JButton botonSalir) {
        this.botonSalir = botonSalir;
    }
}
