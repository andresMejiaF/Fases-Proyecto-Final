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
/**
 * Se crean los archivos de testeo para Producto,
 * Aqui probamos el modelo mediante el archivo sql para
 *  pruebas (pruebas.sql)
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductoTest {

    @Autowired
    private ProductoRepo productoRepo; //Repositorio
    @Autowired
    private CiudadRepo ciudadRepo;//Repo auxiliar
    @Autowired
    private UsuarioRepo usuarioRepo;//Repo auxiliar

    @Test
    @Sql("classpath:pruebas.sql")
    public void regirtrarTest(){ //Se crea la entidad para guardarla en el repositorio y verificar el registro

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
    @Sql("classpath:pruebas.sql")//Archivo sql
    public void eliminarTest(){ //Se elimina una entidad del repositorio mediante su llave primaria

        productoRepo.deleteById("9090");

        Producto productoBuscado= productoRepo.findById("9090").orElse(null);

        Assertions.assertNull(productoBuscado);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void actualizarTest(){ //se actualiza una entidad del repositorio

        Producto guardado = productoRepo.findById("9090").orElse(null);
        guardado.setNombre("Play 5");
        productoRepo.save(guardado);
        Producto productoBuscado= productoRepo.findById("9090").orElse(null);
        Assertions.assertEquals("Play 5", productoBuscado.getNombre());
    }
    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarTest(){//Se listan las entidades creadas en pruebas.sql
        List<Producto> productos= productoRepo.findAll();
        productos.forEach(producto -> System.out.println(producto));
    }

}
