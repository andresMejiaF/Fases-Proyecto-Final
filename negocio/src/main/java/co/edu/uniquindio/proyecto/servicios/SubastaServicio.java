package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.excepciones.SubastaNoEncontradaExcepcion;

import java.util.List;

public interface SubastaServicio {

    public Subasta publicarSubasta(Subasta subasta) throws Exception;

    public List<Subasta> listarSubastas(String codigoUsuario) throws Exception;

    public Subasta obtenerSubasta(String codigoSubasta) throws SubastaNoEncontradaExcepcion;

    public List<Subasta> listarSubastasVigentes();


}
