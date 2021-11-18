package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.ProductoValido;
import co.edu.uniquindio.proyecto.dto.ProductosPorUsuario;
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

    //Numero de productos que hay por cada tipo de categoria
    @Query("select c.nombre,  count(p) from  Producto  p join p.categorias c group by c")
    List<Object[]> obtenerTotalProductosPorCategoria();

    //Que productos no tienen comentarios
    @Query("select p from Producto p where p.comentarios is empty ")
    List<Producto> obtenerProductosSinComentarios();

    //filtrar por nombre, busqueda
    @Query("select p from Producto p where p.nombre like concat('%', :nombre, '%') ")
    List<Producto> buscarProductoNombre(String nombre);

    //cuantos productosha publicado a la venta cada usuario
    @Query("select new co.edu.uniquindio.proyecto.dto.ProductosPorUsuario(p.vendedor.codigo, p.vendedor.email, count (p)) from Producto p group by p.vendedor")
    List<ProductosPorUsuario> obteneProductosEnVenta();

    /*
    obtener promedio de calificaciones
    @Query("select avg(c.calificacion) from Producto p join p.comentarios c where p.codigo= :codigo")
    Float obtenerPromedioCalificaciones(String codigo);

    no se puede hacer porque tenemos pendiente cambiar el tipo de dato de las calificaciones de un producto
    (String ----> float)
     */



}
