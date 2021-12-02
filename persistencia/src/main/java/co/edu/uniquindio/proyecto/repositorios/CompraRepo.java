package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Se crea el Repositorio para  Compra
 * con su Entidad y tipo de dato de su llave primaria
 */
@Repository
public interface CompraRepo  extends JpaRepository<Compra, String> {

    //lista de productos que ha comprado un usuario
    @Query("select d.producto from Compra  c join c.detalleCompras d where  c.usuario.codigo= :codigo")
    List<Producto> obtenerListaProductosComprados(String codigo);


    //Devolver numero de productos queha comprado un usuario
    @Query("select  count( distinct d.producto) from Compra  c join c.detalleCompras d where  c.usuario.codigo= :codigo")
    Long obtenerListaProductosCompradosU(String codigo);

    //valor total que ha ganado un usuario por vendersus productos
    @Query("select sum(d.precioProducto * d.unidades) from DetalleCompra d where  d.producto.vendedor.codigo= :codigo")
    Long calcularTotalVentas(String codigo);
    //Cuanto ha gastado un usuario en compras
    @Query("select sum(d.precioProducto * d.unidades) from Compra c join c.detalleCompras d where  c.usuario.codigo= :codigo")
    Long calcularGastoCompra(String codigo);

    //tipo producto con mas registros
    @Query("select c, count (p) as total  from Producto p join p.categorias c group by c  order by  total desc")
    List<Object[]> obtenerCategoriaMasUsada();
    //regresar compras y detalle compras de un usuario
    @Query("select c, d from Compra  c join c.detalleCompras d where c.usuario.codigo = :codigo")
    List<Object[]> obtenerComprasUsuario(String codigo);

}
