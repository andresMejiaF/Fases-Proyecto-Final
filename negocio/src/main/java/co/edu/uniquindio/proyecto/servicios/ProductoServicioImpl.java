package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ProductoNoEncontradoException;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    @Autowired
    private ProductoRepo productoRepo;
    @Autowired
    private DetalleCompraRepo detalleCompraRepo;
    @Autowired
    private CategoriaRepo categoriaRepo;
    @Autowired
    private ComentarioRepo comentarioRepo;
    @Autowired
    private CompraRepo compraRepo;



    @Override
    public Producto publicarProducto(Producto producto) throws Exception {
        try {
            return productoRepo.save(producto);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void actualizarProducto(Producto p) throws Exception {

        productoRepo.save(p);

    }

    @Override
    public void eliminarProducto(String codigo) throws Exception {
        Optional<Producto> producto = productoRepo.findById(codigo);
        if (producto.isEmpty()) {
            throw new Exception("El codigo del producto no existe");
        }

        productoRepo.deleteById(codigo);
    }

    @Override
    public Producto obtenerProducto(String codigo) throws ProductoNoEncontradoException {
        return productoRepo.findById(codigo).orElseThrow(() -> new ProductoNoEncontradoException("El codigo del producto no es valido"));
    }

    @Override
    public List<Producto> listarProducto(Categoria categoria) {
        return productoRepo.listarPorCategoria(categoria);
    }

    @Override
    public void comentarProducto(Comentario comentario) throws Exception {
        comentario.setFechaComentario(LocalDate.now());
        comentarioRepo.save(comentario);
    }

    @Override
    public void guardarProductoEnFavoritos(Producto producto, Usuario usuario) throws Exception {

    }

    @Override
    public void eliminarProductoFavoritos(Producto producto, Usuario usuario) throws Exception {

    }

    @Override
    public void comprarProductos(Compra compra) throws Exception {

    }

    @Override
    public List<Producto> buscarProductos(String nombreProducto, String[] filtros) {
        return productoRepo.buscarProductoNombre(nombreProducto);
    }

    @Override
    public List<Producto> listarProductos(String codigoUsuario) throws Exception {
        return productoRepo.listarPorCodUsuario(codigoUsuario);
    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepo.findAll();
    }

    @Override
    public Categoria obtenerCategoria(String codigo) throws Exception {
        return categoriaRepo.findById(codigo).orElseThrow(() -> new Exception("El codigo no corresponde a ninguna categoria"));
    }

    @Override
    public List<Producto> listarTodosLosProductos() {
        return productoRepo.findAll();
    }

    @Override
    public List<Producto> listarProductoComprado(String codigo) {
        return compraRepo.obtenerListaProductosComprados(codigo);
    }

    @Override
    public Compra comprarProductos(Usuario usuario, ArrayList<ProductoCarrito> productos, String medioPago) throws Exception {
       try {
           Compra compra = new Compra();
           compra.setFechaCompra(LocalDate.now(ZoneId.of("America/Bogota")));
           compra.setUsuario(usuario);
           compra.setMedioPago(medioPago);

           Compra compraGuardada = compraRepo.save(compra);

           DetalleCompra dc;
           for (ProductoCarrito p : productos) {
               dc = new DetalleCompra();
               dc.setCompra(compraGuardada);
               dc.setPrecioProducto(p.getPrecio());
               dc.setUnidades(p.getUnidades());
               dc.setProducto(productoRepo.findById(p.getCodigo()).get());
               //TODO verificar que las unidades esten disponibles
               detalleCompraRepo.save(dc);

           }

           return compraGuardada;
       }catch (Exception e){
           throw  new Exception(e.getMessage());
       }
    }

    @Override
    public Float obtenerPromedioCalificacion(String codigoProducto) {

        if(productoRepo.obtenerPromedioCalificaciones(codigoProducto)!= null){
            return productoRepo.obtenerPromedioCalificaciones(codigoProducto);
        }
        return 0F;
    }

    @Override
    public List<Producto> buscarProductoVarios(String cadena) {
        return productoRepo.buscarProductos(cadena);
    }

    @Override
    public List<Producto> productoPorCategoria(String nombre) {


        return productoRepo.productoCategoria(nombre);
    }

}
