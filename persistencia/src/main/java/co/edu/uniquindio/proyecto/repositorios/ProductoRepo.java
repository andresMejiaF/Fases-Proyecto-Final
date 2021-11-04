package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Se crea el Repositorio para  Producto
 * con su Entidad y tipo de dato de su llave primaria
 */
public interface ProductoRepo extends JpaRepository<Producto, String> {

    Page<Producto> findAll(Pageable paginador);
}
