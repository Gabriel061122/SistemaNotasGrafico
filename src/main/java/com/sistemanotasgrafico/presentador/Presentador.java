package com.sistemanotasgrafico.presentador;

import com.sistemanotasgrafico.modelo.Nota;
import com.sistemanotasgrafico.modelo.Usuario;
import com.sistemanotasgrafico.persistencia.ArchivoNota;
import com.sistemanotasgrafico.vista.VentanaInicial;
import com.sistemanotasgrafico.vista.VentanaInicioSesion;
import com.sistemanotasgrafico.vista.VentanaPrincipal;
import com.sistemanotasgrafico.vista.VentanaRegistro;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
           DefaultListModel<String> listaEnPantalla = new DefaultListModel<>();
           //List<String> titulos = archn.listaNotas();

           busquedaPorDefecto( listaEnPantalla);

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
                       if (ventanaPrincipal.getListaNota().getSelectedIndex() != -1) {
                           ventanaPrincipal.getBotonEliminarNota().setSelected(true);
                       } else {
                           ventanaPrincipal.getBotonEliminarNota().setSelected(true);
                       }
                   } catch (IOException ex) {
                       throw new RuntimeException(ex);
                   }
               }
           });

           ventanaPrincipal.getBotonActualizar().addActionListener(e->{
               String titulo = ventanaPrincipal.getTextoTitulo().getText();
               String contenido = ventanaPrincipal.getTextoContenido().getText();
               List<String> contenidoLineas = Arrays.asList(contenido.split("\n"));
               Nota nota = new Nota(titulo);
               nota.setLineas(contenidoLineas);
               try {
                   archn.archivarNota(nota);
                   busquedaPorDefecto( listaEnPantalla);
               } catch (IOException ex) {
                   cerrarPrograma();
               }
           });

           ventanaPrincipal.getTextoTituloNotas().getDocument().addDocumentListener(new DocumentListener() {
               @Override
               public void insertUpdate(DocumentEvent e) {
                   busqueda(listaEnPantalla);
               }

               @Override
               public void removeUpdate(DocumentEvent e) {
                   busqueda(listaEnPantalla);
               }

               @Override
               public void changedUpdate(DocumentEvent e) {
                   busqueda(listaEnPantalla);
               }
           });

           ventanaPrincipal.getBotonEliminarNota().addActionListener(e->{
               String titulo = ventanaPrincipal.getListaNota().getSelectedValue();
               try {
                   archn.eliminarNota(titulo);
               } catch (IOException ex) {
                   cerrarPrograma();
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

    public void filtrarNota(String filtro, DefaultListModel<String> listaEnPantalla) throws IOException {

        if (filtro.equals("")) {
            busquedaPorDefecto(listaEnPantalla);
            return;
        }

        List<String> listaNombresNotas = archn.listaNotas();
        List<Nota> notas = new ArrayList<>();
        for (int i = 0; i < listaNombresNotas.size(); i++) {
            notas.add(archn.getNota(i));
        }

        listaEnPantalla.clear();

        for (int i = 0; i < listaNombresNotas.size(); i++) {
            if (notas.get(i).getTitulo().toLowerCase().contains(filtro.toLowerCase()) || notas.get(i).getLineas().contains(filtro.toLowerCase())) {
                listaEnPantalla.addElement(listaNombresNotas.get(i));
            }
        }
    }

    public void busquedaPorDefecto( DefaultListModel<String> listaEnPantalla) throws IOException {
        List<String> notas = archn.listaNotas();
        for(String titulo : notas){
            listaEnPantalla.addElement(titulo);
        }
        this.ventanaPrincipal.setListaNota(new JList<>(listaEnPantalla));
    }

    public void busqueda(DefaultListModel<String> listaEnPantalla) {
        String cadenaBusqueda = ventanaPrincipal.getTextoTituloNotas().getText();
        try {
            filtrarNota(cadenaBusqueda, listaEnPantalla);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


}