package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.ProductoValido;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Se crea el Repositorio para  Producto
 * con su Entidad y tipo de dato de su llave primaria
 */
public interface ProductoRepo extends JpaRepository<Producto, String> {

    Page<Producto> findAll(Pageable paginador);
    @Query("select p.vendedor.nombre from Producto p where  p.codigo=:id")
    String obtenerNombreVendedor(String id);

    //Trae los comentarios de un producto dado su codigo
    @Query("select c from Producto p join p.comentarios c where  p.codigo=:codigo")
    //@Query("select  c from Comentario c where  c.producto.codigo=:codigo") //esta es mas corta dependiendo del modelo ya que e comentario hay un producto y en producto hay varios comentarios
    List<Comentario> obtenerComentarios(String codigo);
    //todos los productos y todos los comentarios
    @Query("select p.nombre, c from Producto p left join p.comentarios c ")
    List<Object[]> listarProductosYComentarios();

    @Query("select distinct c.usuario from Producto p left join p.comentarios c where p.codigo= :id")
    List<Usuario> listarUsuariosComentarios(String id);

    @Query("select  p.nombre, p.descripcion, p.precio, p.ciudad.nombre from Producto p where  :fechaActual < p.fechaLimite")
    List<Object[]> listarProductosValidos(LocalDate fechaActual);

    @Query("select new co.edu.uniquindio.proyecto.dto.ProductoValido( p.nombre, p.descripcion, p.precio, p.ciudad) from Producto p where  :fechaActual < p.fechaLimite")
    List<ProductoValido> listarProductosValidos2(LocalDate fechaActual);
}
