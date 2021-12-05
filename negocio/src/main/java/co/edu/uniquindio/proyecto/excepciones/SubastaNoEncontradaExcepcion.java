package co.edu.uniquindio.proyecto.excepciones;

import java.util.NoSuchElementException;

public class SubastaNoEncontradaExcepcion extends NoSuchElementException {

    public SubastaNoEncontradaExcepcion(String error) {
        super(error);
    }
}

