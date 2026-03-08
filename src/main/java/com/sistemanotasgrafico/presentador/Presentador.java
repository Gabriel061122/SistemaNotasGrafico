package com.sistemanotasgrafico.presentador;

import com.sistemanotasgrafico.modelo.Usuario;
import com.sistemanotasgrafico.persistencia.ArchivoNota;
import com.sistemanotasgrafico.vista.VentanaInicial;
import com.sistemanotasgrafico.vista.VentanaInicioSesion;
import com.sistemanotasgrafico.vista.VentanaPrincipal;
import com.sistemanotasgrafico.vista.VentanaRegistro;

import javax.swing.*;
import java.io.IOException;

public class Presentador {

    private ArchivoNota archn;

    private VentanaInicial ventanaInicial;
    private VentanaPrincipal ventanaPrincipal;
    private VentanaInicioSesion ventanaIniciarSesion;
    private VentanaRegistro ventanaRegistrar;

    public Presentador() {
        this.ventanaPrincipal = new VentanaPrincipal();
        this.ventanaInicial = new VentanaInicial();
        this.ventanaIniciarSesion = new VentanaInicioSesion();
        this.ventanaRegistrar = new VentanaRegistro();
    }

    public void prepararVentanaInicial() {
        ventanaInicial.aniadirEventoIniciar(e -> {
            ventanaInicial.cerrarVentana();
            ventanaIniciarSesion.mostrarVentana();
        });
    }

    public void prepararVentanaIniciarSesion() {
        ventanaIniciarSesion.aniadirEventoIniciarSesion(e -> {
            String email = ventanaIniciarSesion.getTextoEmail().getText();
            String contrasenia = ventanaIniciarSesion.getTextoContrasenia().getText();
            try {
                archn = ArchivoNota.iniciarSesion(email, contrasenia);
                ventanaIniciarSesion.getVentanaInicioSesion().setVisible(false);
                prepararVentanaPrincipal();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(ventanaIniciarSesion.getVentanaInicioSesion(), "Ha habido un error. se cerrará el programa");
                throw new RuntimeException(ex);
            }
        });
        ventanaIniciarSesion.aniadirEventoRegistrar(e-> {
            ventanaRegistrar.mostrarVentana();
            ventanaIniciarSesion.cerrarVentana();
        });
    }

    public void prepararVentanaRegistrar() {
        ventanaRegistrar.aniadirEventoRegistrar(e -> {
            String nombre = ventanaRegistrar.getTextoNombre().getText();
            String email = ventanaRegistrar.getTextoEmail().getText();
            String contrasenia = ventanaRegistrar.getTextoContrasenia().getText();
            Usuario usuario = new Usuario(email, contrasenia);

            try {
                ArchivoNota.registrarUsuario(usuario, contrasenia);
                archn = ArchivoNota.iniciarSesion(usuario.getNombre(), usuario.getEmail());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        ventanaRegistrar.aniadirEventoIrAInicioSesion(e->{
            ventanaIniciarSesion.mostrarVentana();
            ventanaRegistrar.mostrarVentana();
        });
    }

    public void prepararVentanaPrincipal() {
        
    }


}