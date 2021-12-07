package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ProductoNoEncontradoException;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
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
    @Autowired
    private UsuarioRepo usuarioRepo;



    @Override
    public Producto publicarProducto(Producto producto) throws Exception {
        Optional<Producto> buscado = productoRepo.findById(producto.getCodigo());
        if(buscado.isPresent()){
            throw new Exception("El codigo del producto ya existe");
        }
            return productoRepo.save(producto);

    }

    @Override
    public void actualizarProducto(Producto p) throws Exception {


        Optional<Producto> buscado = productoRepo.findById(p.getCodigo());
        if(buscado.isEmpty()){
            throw new Exception("El codigo no existe");
        }
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
    public void eliminarComentario(Comentario comentario) {
        comentarioRepo.delete(comentario);
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
           Producto prod;
           Integer unidades;
           for (ProductoCarrito p : productos) {
               dc = new DetalleCompra();
               dc.setCompra(compraGuardada);
               dc.setPrecioProducto(p.getPrecio());
               dc.setUnidades(p.getUnidades());
               dc.setProducto(productoRepo.findById(p.getCodigo()).get());
               //TODO verificar que las unidades esten disponibles
               detalleCompraRepo.save(dc);
               prod=obtenerProducto(p.getCodigo());
               unidades=prod.getUnidades();
               unidades=unidades -p.getUnidades();
               prod.setUnidades(unidades);
               actualizarProducto(prod);
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

    @Override
    public List<Producto> productoFavorito(String codigo) {

        return productoRepo.productosFavoritos(codigo);
    }

    @Override
    public List<Producto> productosPorRango(double valor1, double valor2) {
        return productoRepo.productoRangoPrecio(valor1, valor2);
    }

    @Override
    public List<Producto> productoPorUnidades(int unidades) {
        return productoRepo.productoUnidadesMayor(unidades);
    }

    @Override
    public List<Producto> productoPropietario(String codigo) throws Exception{

        Optional<Usuario> buscado = usuarioRepo.findById(codigo);

        if(buscado.isEmpty()){
            throw new Exception("El codigo no existe");
        }
        return productoRepo.productoPropietario(codigo);
    }


}
