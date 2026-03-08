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

    public VentanaInicioSesion() {
        ventanaInicioSesion = new JFrame("Ventana Inicio de Sesion");
        ventanaInicioSesion.setSize(500, 300);
        ventanaInicioSesion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelFormulario = new JPanel(new GridLayout(3, 2, 10, 10));

        textoEmail = new JTextField();
        textoContrasenia = new JPasswordField();
        botonIniciarSesion = new JButton("Iniciar Sesion");

        panelFormulario.add(new JLabel("Email:"));
        panelFormulario.add(textoEmail);
        panelFormulario.add(new JLabel("Contrasenia:"));
        panelFormulario.add(textoContrasenia);
        panelFormulario.add(new JLabel());
        panelFormulario.add(botonIniciarSesion);

        ventanaInicioSesion.add(panelFormulario);
    }

    public void mostrarVentana() {
        ventanaInicioSesion.setVisible(true);
    }

    public void aniadirEventoIniciarSesion(ActionListener l) {
        botonIniciarSesion.addActionListener(l);
    }
}
