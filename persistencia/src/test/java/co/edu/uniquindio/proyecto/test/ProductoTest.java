package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductoTest {

    @Autowired
    private ProductoRepo productoRepo;
    @Autowired
    private CiudadRepo ciudadRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;

    @Test
    @Sql("classpath:pruebas.sql")
    public void regirtrarTest(){

        Ciudad ciudad = ciudadRepo.findById(123).orElse(null);
        Usuario usuario = usuarioRepo.findById("456").orElse(null);
        Producto producto= new Producto();
        producto.setCodigo("4456");
        producto.setDescripcion("Un producto bonito");
        producto.setDescuento(10.000);
        producto.setFechaLimite(LocalDate.now());
        producto.setNombre("Xbox");
        producto.setPrecio(800.000);
        producto.setUnidades(12);
        producto.setCiudad(ciudad);
        producto.setVendedor(usuario);

        Producto productoGuardado= productoRepo.save(producto);
        System.out.println(productoGuardado);
        Assertions.assertNotNull(productoGuardado);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void eliminarTest(){

        productoRepo.deleteById("9090");

        Producto productoBuscado= productoRepo.findById("9090").orElse(null);

        Assertions.assertNull(productoBuscado);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void actualizarTest(){

        Producto guardado = productoRepo.findById("9090").orElse(null);
        guardado.setNombre("Play 5");
        productoRepo.save(guardado);
        Producto productoBuscado= productoRepo.findById("9090").orElse(null);
        Assertions.assertEquals("Play 5", productoBuscado.getNombre());
    }
    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarTest(){
        List<Producto> productos= productoRepo.findAll();
        productos.forEach(producto -> System.out.println(producto));
    }

}
