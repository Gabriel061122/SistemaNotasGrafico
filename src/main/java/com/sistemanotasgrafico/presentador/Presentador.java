package com.sistemanotasgrafico.presentador;

import com.sistemanotasgrafico.excepciones.NotaYaExistenteException;
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

    DefaultListModel<String> listaEnPantalla = new DefaultListModel<>();

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

        ventanaInicial.aniadirEventoRegistrar(e->{
            ventanaRegistrar.mostrarVentana();
            ventanaInicial.cerrarVentana();
        });
    }

    public void prepararVentanaIniciarSesion() {
        ventanaIniciarSesion.aniadirEventoIniciarSesion(e -> {
            String email = ventanaIniciarSesion.getTextoEmail().getText();
            String contrasenia = ventanaIniciarSesion.getTextoContrasenia().getText();
            try {
                archn = ArchivoNota.iniciarSesion(email, contrasenia);
                ventanaIniciarSesion.getVentanaInicioSesion().setVisible(false);
                ventanaPrincipal.mostrarVentana();
                busquedaPorDefecto(listaEnPantalla);
            } catch (IOException ex) {
                showMensaje(ex.getMessage());
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

            try {
                Usuario usuario = new Usuario(nombre, email);
                ArchivoNota.registrarUsuario(usuario, contrasenia);
                archn = ArchivoNota.iniciarSesion(email, contrasenia);
                ventanaRegistrar.cerrarVentana();
                ventanaPrincipal.mostrarVentana();
                busquedaPorDefecto(listaEnPantalla);
            } catch (IllegalArgumentException ex) {
                showMensaje(ex.getMessage());
            } catch (IOException ex) {
                showMensaje(ex.getMessage());
            }
        });

        ventanaRegistrar.aniadirEventoIrAInicioSesion(e->{
            ventanaIniciarSesion.mostrarVentana();
            ventanaRegistrar.cerrarVentana();
        });
    }

    public void prepararVentanaPrincipal() {

       try{

           //List<String> titulos = archn.listaNotas();

           ventanaPrincipal.getListaNota().addListSelectionListener(e->{
               if (!e.getValueIsAdjusting()) {
                   StringBuilder contenido = new StringBuilder();
                   int index = ventanaPrincipal.getListaNota().getSelectedIndex();
                   if (index < 0) {
                       return;
                   }
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
                       showMensaje(ex.getMessage());
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
                   try {
                       archn.aniadirRegistroNota(nota);
                   } catch (NotaYaExistenteException ignored) {
                   }
                   busquedaPorDefecto( listaEnPantalla);
               } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                   cerrarPrograma();
               }
           });

           ventanaPrincipal.getTextoBuscar().getDocument().addDocumentListener(new DocumentListener() {
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
               if (titulo == null || titulo.isBlank()) {
                   return;
               }
               try {
                   archn.eliminarNota(titulo);
                   busquedaPorDefecto(listaEnPantalla);
                   ventanaPrincipal.getTextoTitulo().setText("");
                   ventanaPrincipal.getTextoContenido().setText("");
               } catch (IOException ex) {
                   System.out.println(ex.getMessage());
                   cerrarPrograma();
               }
           });

           ventanaPrincipal.getBotonLimpiarCampos().addActionListener(e -> {
               ventanaPrincipal.getTextoTitulo().setText("");
               ventanaPrincipal.getTextoContenido().setText("");
           });

           ventanaPrincipal.getBotonBorrarNotas().addActionListener(e -> {
               if (JOptionPane.showConfirmDialog(
                       ventanaPrincipal.getVentanaPrincipal(),
                       "¿Seguro que quieres borrar la nota?",
                       "Confirmación",
                       JOptionPane.YES_NO_OPTION
               ) == JOptionPane.YES_OPTION){
                    try {
                        List<String> nombresNotas= archn.listaNotas();

                        for (String linea : nombresNotas) {
                            if (linea == null || linea.isBlank()) {
                                continue;
                            }
                            archn.eliminarNota(linea);
                        }
                        busquedaPorDefecto(listaEnPantalla);
                        ventanaPrincipal.getTextoTitulo().setText("");
                        ventanaPrincipal.getTextoContenido().setText("");
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                        cerrarPrograma();
                    }
                }

           });

           ventanaPrincipal.getBotonCerrarSesion().addActionListener(e -> {
               ventanaPrincipal.cerrarVentana();
               ventanaIniciarSesion.mostrarVentana();
           });

       } catch (Exception e) {
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
            boolean seEncuentraEnTitulo = notas.get(i).getTitulo().contains(filtro);
            boolean seEncuentraEnNotas = false;

            for (String linea : notas.get(i).getLineas()) {
                if (linea.contains(filtro)) {
                    seEncuentraEnNotas = true;
                    break;
                }
            }
            if (seEncuentraEnTitulo || seEncuentraEnNotas) {
                listaEnPantalla.addElement(listaNombresNotas.get(i));
            }
        }
    }

    public void busquedaPorDefecto( DefaultListModel<String> listaEnPantalla) throws IOException {
        listaEnPantalla.clear();
        List<String> notas = archn.listaNotas();
        for(String titulo : notas){
            listaEnPantalla.addElement(titulo);
        }
        this.ventanaPrincipal.getListaNota().setModel(listaEnPantalla);
    }

    public void busqueda(DefaultListModel<String> listaEnPantalla) {
        String cadenaBusqueda = ventanaPrincipal.getTextoTituloNotas().getText();
        try {
            filtrarNota(cadenaBusqueda, listaEnPantalla);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void iniciar(){
        prepararVentanaInicial();
        prepararVentanaIniciarSesion();
        prepararVentanaRegistrar();
        prepararVentanaPrincipal();

        ventanaInicial.mostrarVentana();
    }

    public void showMensaje(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }


}
