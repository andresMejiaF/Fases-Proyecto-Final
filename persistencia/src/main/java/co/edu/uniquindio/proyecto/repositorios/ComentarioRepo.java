package co.edu.uniquindio.proyecto.repositorios;


import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Se crea el Repositorio para  Comentario
 * con su Entidad y tipo de dato de su llave primaria
 */
@Repository
public interface ComentarioRepo extends JpaRepository<Comentario, String> {
}
