package com.sistemanotasgrafico.vista;

import com.sistemanotasgrafico.modelo.Usuario;

import javax.swing.*;
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

        botonIniciar = new JButton("Iniciar Sesión");
        botonRegistrar = new JButton("Registrar Usuario");
        botonSalir = new JButton("Salir");

        panelPrincipal.add(botonIniciar);
        panelPrincipal.add(botonRegistrar);
        ventana.add(panelPrincipal);

    }

    public void iniciar(){
        ventana.setVisible(true);
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
}
