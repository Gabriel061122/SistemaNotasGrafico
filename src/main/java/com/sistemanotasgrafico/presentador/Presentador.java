package com.sistemanotasgrafico.presentador;

import com.sistemanotasgrafico.vista.VentanaInicial;
import com.sistemanotasgrafico.vista.VentanaPrincipal;

public class Presentador {

    private VentanaInicial ventanaInicial;
    private VentanaPrincipal ventanaPrincipal;

    public Presentador() {
        this.ventanaPrincipal = new VentanaPrincipal();
        this.ventanaInicial = new VentanaInicial();
    }

    public void iniciar() {
        ventanaInicial.aniadirEventoIniciar(e -> {

        });
    }

}
