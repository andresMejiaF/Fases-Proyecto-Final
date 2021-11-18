package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Optional;
import java.util.List;

/**
 * Se crea el Repositorio para  Ciudad
 * con su Entidad y tipo de dato de su llave primaria
 */
@Repository
public interface CiudadRepo extends JpaRepository<Ciudad, Integer> {

    Optional <Ciudad> findByNombre(String nombreCiudad);
    //segun el nombre de la ciudad trae quienes viven en ella
    @Query("select u from Ciudad  c join c.usuarios u where  c.nombre = :nombre")
    List<Usuario> listarUsuarios( String nombre);
}
