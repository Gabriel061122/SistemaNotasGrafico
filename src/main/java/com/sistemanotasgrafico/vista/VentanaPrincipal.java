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
    private JPanel panelBusqueda;

    private JTextArea textoContenido;
    private JTextField textoTitulo;
    private JTextField textoBuscar;


    private JList<String> listaNota;
    private JButton botonEliminarNota;
    private JButton botonLimpiarCampos;
    private JButton botonBorrarNotas;
    private JButton botonActualizar;
    private JButton botonCerrarSesion;

    public VentanaPrincipal() {

        ventanaPrincipal = new JFrame("Ventana Principal");
        ventanaPrincipal.setSize(900, 500);
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        listaNota = new JList<>();
        textoContenido = new JTextArea();
        textoTitulo = new JTextField();
        textoBuscar = new JTextField();

        botonEliminarNota = new JButton("Eliminar Nota");
        botonLimpiarCampos = new JButton("Limpiar Campos");
        botonBorrarNotas = new JButton("Borrar");
        botonActualizar = new JButton("Actualizar");
        botonCerrarSesion = new JButton("Cerrar Sesion");


        panelNotas = new JPanel();
        panelAcciones = new JPanel();
        panelContenido = new JPanel();
        panelTitulo = new JPanel(new BorderLayout(10, 10));
        panelBusqueda = new JPanel(new BorderLayout(10, 10));

        panelNotas.setLayout(new BorderLayout(5, 5));
        panelContenido.setLayout(new BorderLayout(5, 5));

        panelTitulo.add(new JLabel("Titulo de la nota:"), BorderLayout.WEST);
        panelTitulo.add(textoTitulo, BorderLayout.CENTER);
        panelBusqueda.add(new JLabel("Buscar:"), BorderLayout.WEST);
        panelBusqueda.add(textoBuscar, BorderLayout.CENTER);
        panelTitulo.add(panelBusqueda, BorderLayout.SOUTH);

        JScrollPane scrollPane = new JScrollPane(listaNota);
        scrollPane.setPreferredSize(new Dimension(250, 375));
        panelNotas.add(new JLabel("Nota:"), BorderLayout.NORTH);
        panelNotas.add(scrollPane, BorderLayout.CENTER);
        panelContenido.add(new JLabel("Contenido:"), BorderLayout.NORTH);
        panelContenido.add(new JScrollPane(textoContenido), BorderLayout.CENTER);
        JPanel panelBotonesContenido = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotonesContenido.add(botonActualizar);
        panelBotonesContenido.add(botonEliminarNota);
        panelContenido.add(panelBotonesContenido, BorderLayout.SOUTH);
        panelAcciones.add(botonLimpiarCampos);
        panelAcciones.add(botonBorrarNotas);
        panelAcciones.add(botonCerrarSesion);
        ventanaPrincipal.add(panelTitulo, BorderLayout.NORTH);
        ventanaPrincipal.add(panelNotas, BorderLayout.WEST);
        ventanaPrincipal.add(panelAcciones, BorderLayout.SOUTH);
        ventanaPrincipal.add(panelContenido, BorderLayout.EAST);

    }

    public void mostrarVentana(){
        ventanaPrincipal.setVisible(true);
    }

    public void cerrarVentana() {
        ventanaPrincipal.setVisible(false);
    }

    public void aniadirEventoEliminarNota(ActionListener l){
        botonEliminarNota.addActionListener(l);
    }

    public void aniadirEventoLimpiarCampos(ActionListener l){
        botonLimpiarCampos.addActionListener(l);
    }

    public void aniadirEventoBorrarNotas(ActionListener l){
        botonBorrarNotas.addActionListener(l);
    }

    public void aniadirEventoActualizar(ActionListener l){
        botonActualizar.addActionListener(l);
    }

    public void aniadirEventoCerrarSesion(ActionListener l){
        botonCerrarSesion.addActionListener(l);
    }

    public JFrame getVentanaPrincipal() {
        return ventanaPrincipal;
    }

    public void setVentanaPrincipal(JFrame ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }

    public JPanel getPanelNotas() {
        return panelNotas;
    }

    public void setPanelNotas(JPanel panelNotas) {
        this.panelNotas = panelNotas;
    }

    public JPanel getPanelAcciones() {
        return panelAcciones;
    }

    public void setPanelAcciones(JPanel panelAcciones) {
        this.panelAcciones = panelAcciones;
    }

    public JPanel getPanelContenido() {
        return panelContenido;
    }

    public void setPanelContenido(JPanel panelContenido) {
        this.panelContenido = panelContenido;
    }

    public JPanel getPanelTitulo() {
        return panelTitulo;
    }

    public void setPanelTitulo(JPanel panelTitulo) {
        this.panelTitulo = panelTitulo;
    }

    public JTextArea getTextoContenido() {
        return textoContenido;
    }

    public void setTextoContenido(JTextArea textoContenido) {
        this.textoContenido = textoContenido;
    }

    public JTextField getTextoTitulo() {
        return textoTitulo;
    }

    public void setTextoTitulo(JTextField textoTitulo) {
        this.textoTitulo = textoTitulo;
    }

    public JList<String> getListaNota() {
        return listaNota;
    }

    public void setListaNota(JList<String> listaNota) {
        this.listaNota = listaNota;
    }

    public JButton getBotonEliminarNota() {
        return botonEliminarNota;
    }

    public void setBotonEliminarNota(JButton botonEliminarNota) {
        this.botonEliminarNota = botonEliminarNota;
    }

    public JButton getBotonLimpiarCampos() {
        return botonLimpiarCampos;
    }

    public void setBotonLimpiarCampos(JButton botonLimpiarCampos) {
        this.botonLimpiarCampos = botonLimpiarCampos;
    }

    public JTextField getTextoTituloNotas() {
        return textoBuscar;
    }

    public void setTextoTituloNotas(JTextField textoTitulo) {
        this.textoBuscar = textoTitulo;
    }

    public JButton getBotonBorrarNotas() {
        return botonBorrarNotas;
    }

    public void setBotonBorrarNotas(JButton botonBorrarNotas) {
        this.botonBorrarNotas = botonBorrarNotas;
    }

    public JButton getBotonActualizar() {
        return botonActualizar;
    }

    public void setBotonActualizar(JButton botonActualizar) {
        this.botonActualizar = botonActualizar;
    }

    public JTextField getTextoBuscar() {
        return textoBuscar;
    }

    public JButton getBotonCerrarSesion() {
        return botonCerrarSesion;
    }

    public void setBotonCerrarSesion(JButton botonCerrarSesion) {
        this.botonCerrarSesion = botonCerrarSesion;
    }

}
