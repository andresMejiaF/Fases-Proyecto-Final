package co.edu.uniquindio.proyecto.repositorios;


import co.edu.uniquindio.proyecto.entidades.Subasta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

/**
 * Se crea el Repositorio para  Subasta
 * con su Entidad y tipo de dato de su llave primaria
 */
@Repository
public interface SubastaRepo extends JpaRepository<Subasta, String>  {

    //oferta mas alta para una subasta
    @Query("select  max(d.valor) from  Subasta s join  s.subastaUsuarios d where s.codigo= :codigo")
    double obtenerValorMasAltoDeUnaSubasta(String codigo);

    //subastas que aun estan disponibles
    @Query("select s from Subasta s where current_timestamp < s.fechaSubasta")
    List<Subasta> listarSubastasDisponibles();
}
