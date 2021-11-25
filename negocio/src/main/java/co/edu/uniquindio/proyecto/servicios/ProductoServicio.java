package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ProductoNoEncontradoException;

import java.util.List;

public interface ProductoServicio {

    Producto publicarProducto(Producto producto)throws Exception;

    void actualizarProducto(Producto p)throws Exception;

    void eliminarProducto(String codigo)throws Exception;

    Producto obtenerProducto(String codigo)throws ProductoNoEncontradoException;

    List<Producto> listarProducto(Categoria categoria);

    void comentarProducto(String mensaje, double calificacion, Usuario usuario, Producto producto) throws Exception;

    void guardarProductoEnFavoritos(Producto producto, Usuario usuario)throws Exception;

    void eliminarProductoFavoritos(Producto producto, Usuario usuario)throws Exception;

    void comprarProductos(Compra compra)throws Exception;

    List<Producto> buscarProductos(String nombreProducto, String[] filtros);

    List<Producto> listarProductos(String codigoUsuario)throws Exception;

    List<Categoria> listarCategorias();

    Categoria obtenerCategoria(String codigo) throws Exception;

}
