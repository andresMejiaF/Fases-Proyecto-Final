package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class ProductoServicioTest {

    @Autowired
    private ProductoServicio productoServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Test
    public void obtenerProductoTest(){
        try {
            Usuario vendedor = usuarioServicio.obtenerUsuario("94285");
            Producto producto = new Producto();
            LocalDate ldt = LocalDate.of(2021, 11, 25);

            producto.setCodigo("1234");
            producto.setDescripcion("Buen estadp");
            producto.setDescuento(0.2);
            producto.setFechaLimite(ldt);
            producto.setNombre("Cama base");
            producto.setPrecio(50.000);
            producto.setUnidades(12);
            producto.setVendedor(vendedor);

           Producto producto1= productoServicio.publicarProducto(producto);
           Assertions.assertNotNull(producto1);
        }catch (Exception e){
            Assertions.assertTrue(false, e.getMessage());
        }
    }

    @Test
    public void listarTest() throws Exception {

        List<Producto> list= productoServicio.listarProductos("94285");

        list.forEach(System.out::println);
    }
}
