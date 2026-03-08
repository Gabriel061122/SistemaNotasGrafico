package com.sistemanotasgrafico.presentador;

import com.sistemanotasgrafico.modelo.Nota;
import com.sistemanotasgrafico.modelo.Usuario;
import com.sistemanotasgrafico.persistencia.ArchivoNota;
import com.sistemanotasgrafico.vista.VentanaInicial;
import com.sistemanotasgrafico.vista.VentanaInicioSesion;
import com.sistemanotasgrafico.vista.VentanaPrincipal;
import com.sistemanotasgrafico.vista.VentanaRegistro;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

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
                cerrarPrograma();
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
                cerrarPrograma();
            }
        });

        ventanaRegistrar.aniadirEventoIrAInicioSesion(e->{
            ventanaIniciarSesion.mostrarVentana();
            ventanaRegistrar.mostrarVentana();
        });
    }

    public void prepararVentanaPrincipal() {

       try{
           DefaultListModel<String> model = new DefaultListModel<>();
           List<String> lineas = archn.listaNotas();

           for(String linea : lineas){
               model.addElement(linea);
           }
           ventanaPrincipal.setListaNota(new JList<>(model));

           ventanaPrincipal.getListaNota().addListSelectionListener(e->{
               if (!e.getValueIsAdjusting()) {
                   StringBuilder contenido = new StringBuilder();
                    int index = ventanaPrincipal.getListaNota().getSelectedIndex();
                   try {
                       Nota nota = archn.getNota(index);
                       for (String linea : nota.getLineas()) {
                           contenido.append(linea);
                       }
                       ventanaPrincipal.getTextoContenido().setText(contenido.toString());
                       ventanaPrincipal.getTextoTitulo().setText(nota.getTitulo());
                   } catch (IOException ex) {
                       throw new RuntimeException(ex);
                   }
               }
           });
       } catch (IOException e) {
           cerrarPrograma();

       }

    }

    public void cerrarPrograma(){
        JOptionPane.showMessageDialog(ventanaIniciarSesion.getVentanaInicioSesion(), "Ha habido un error. se cerrará el programa");
        ventanaIniciarSesion.getVentanaInicioSesion().dispose();
        ventanaRegistrar.getVentanaRegistro().dispose();
        ventanaPrincipal.getVentanaPrincipal().dispose();
        ventanaInicial.getVentana().dispose();

    }


}