package co.edu.uniquindio.proyecto.repositorios;


import co.edu.uniquindio.proyecto.entidades.Subasta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Se crea el Repositorio para  Subasta
 * con su Entidad y tipo de dato de su llave primaria
 */
@Repository
public interface SubastaRepo extends JpaRepository<Subasta, String>  {
}
