package com.sistemanotasgrafico.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaRegistro {

    private JFrame ventanaRegistro;
    private JPanel panelFormulario;
    private JTextField textoNombre;
    private JTextField textoEmail;
    private JPasswordField textoContrasenia;
    private JButton botonRegistrar;
    private JButton botonIrAInicioSesion;

    public VentanaRegistro() {
        ventanaRegistro = new JFrame("Ventana Registro");
        ventanaRegistro.setSize(500, 350);
        ventanaRegistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));

        textoNombre = new JTextField();
        textoEmail = new JTextField();
        textoContrasenia = new JPasswordField();
        botonRegistrar = new JButton("Registrarse");
        botonIrAInicioSesion = new JButton("Ir a Inicio de Sesion");

        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(textoNombre);
        panelFormulario.add(new JLabel("Email:"));
        panelFormulario.add(textoEmail);
        panelFormulario.add(new JLabel("Contrasenia:"));
        panelFormulario.add(textoContrasenia);
        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 10, 0));
        panelBotones.add(botonRegistrar);
        panelBotones.add(botonIrAInicioSesion);

        panelFormulario.add(new JLabel(""));
        panelFormulario.add(panelBotones);

        ventanaRegistro.add(panelFormulario);
    }

    public void mostrarVentana() {
        ventanaRegistro.setVisible(true);
    }

    public void aniadirEventoRegistrar(ActionListener l) {
        botonRegistrar.addActionListener(l);
    }

    public void aniadirEventoIrAInicioSesion(ActionListener l) {
        botonIrAInicioSesion.addActionListener(l);
    }
}
