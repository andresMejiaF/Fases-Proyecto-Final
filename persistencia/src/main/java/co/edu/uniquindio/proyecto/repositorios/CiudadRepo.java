package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Se crea el Repositorio para  Ciudad
 * con su Entidad y tipo de dato de su llave primaria
 */
@Repository
public interface CiudadRepo extends JpaRepository<Ciudad, Integer> {

    Optional <Ciudad> findByNombre(String nombreCiudad);
}
