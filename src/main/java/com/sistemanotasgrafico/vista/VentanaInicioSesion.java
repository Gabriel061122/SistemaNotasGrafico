package com.sistemanotasgrafico.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaInicioSesion {

    private JFrame ventanaInicioSesion;
    private JPanel panelFormulario;
    private JTextField textoEmail;
    private JPasswordField textoContrasenia;
    private JButton botonIniciarSesion;
    private JButton botonIrARegistro;

    public VentanaInicioSesion() {
        ventanaInicioSesion = new JFrame("Ventana Inicio de Sesion");
        ventanaInicioSesion.setSize(500, 300);
        ventanaInicioSesion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelFormulario = new JPanel(new GridLayout(3, 2, 10, 10));

        textoEmail = new JTextField();
        textoContrasenia = new JPasswordField();
        botonIniciarSesion = new JButton("Iniciar Sesion");
        botonIrARegistro = new JButton("Ir a Registro");

        panelFormulario.add(new JLabel("Email:"));
        panelFormulario.add(textoEmail);
        panelFormulario.add(new JLabel("Contrasenia:"));
        panelFormulario.add(textoContrasenia);
        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 10, 0));
        panelBotones.add(botonIniciarSesion);
        panelBotones.add(botonIrARegistro);

        panelFormulario.add(new JLabel(""));
        panelFormulario.add(panelBotones);

        ventanaInicioSesion.add(panelFormulario);
    }

    public void mostrarVentana() {
        ventanaInicioSesion.setVisible(true);
    }

    public void cerrarVentana() {
        ventanaInicioSesion.setVisible(false);;
    }

    public void aniadirEventoIniciarSesion(ActionListener l) {
        botonIniciarSesion.addActionListener(l);
    }

    public void aniadirEventoRegistrar(ActionListener l) {
        botonIrARegistro.addActionListener(l);
    }

    public JFrame getVentanaInicioSesion() {
        return ventanaInicioSesion;
    }

    public void setVentanaInicioSesion(JFrame ventanaInicioSesion) {
        this.ventanaInicioSesion = ventanaInicioSesion;
    }

    public JPanel getPanelFormulario() {
        return panelFormulario;
    }

    public void setPanelFormulario(JPanel panelFormulario) {
        this.panelFormulario = panelFormulario;
    }

    public JTextField getTextoEmail() {
        return textoEmail;
    }

    public void setTextoEmail(JTextField textoEmail) {
        this.textoEmail = textoEmail;
    }

    public JPasswordField getTextoContrasenia() {
        return textoContrasenia;
    }

    public void setTextoContrasenia(JPasswordField textoContrasenia) {
        this.textoContrasenia = textoContrasenia;
    }

    public JButton getBotonIniciarSesion() {
        return botonIniciarSesion;
    }

    public void setBotonIniciarSesion(JButton botonIniciarSesion) {
        this.botonIniciarSesion = botonIniciarSesion;
    }

    public JButton getBotonIrARegistro() {
        return botonIrARegistro;
    }

    public void setBotonIrARegistro(JButton botonIrARegistro) {
        this.botonIrARegistro = botonIrARegistro;
    }
}
