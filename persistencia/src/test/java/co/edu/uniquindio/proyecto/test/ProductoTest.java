package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.ProductoValido;
import co.edu.uniquindio.proyecto.dto.ProductosPorUsuario;
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
import java.time.LocalDateTime;
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

    @Test
    @Sql("classpath:pruebas.sql")
    public  void obtenerNombreVendedorTest(){//Se listan las entidades creadas en pruebas.sql
       String nombre= productoRepo.obtenerNombreVendedor("22324");
       Assertions.assertEquals("julio jaramillo", nombre);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarUsuariosProductosTest(){//Se listan las entidades creadas en pruebas.sql
        List<Object[]> respuesta = usuarioRepo.listarUsuariosYProductos();
        //respuesta.forEach(System.out::println);
        for (Object[] objecto: respuesta){
            System.out.println(objecto[0]+"----"+objecto[1]+"----"+objecto[2]);
        }
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarProductosYcomentariosTest(){//Se listan las entidades creadas en pruebas.sql
        List<Object[]> respuesta = productoRepo.listarProductosYComentarios();
        //respuesta.forEach(System.out::println);
       respuesta.forEach(object -> System.out.println(object[0]+"---"+object[1]));
        //Falta lo del Assertions con el .size xd
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarUsuariosComentariosTest(){//Se listan las entidades creadas en pruebas.sql
        List<Usuario> usuarios = productoRepo.listarUsuariosComentarios("9090");
        usuarios.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarProductosValidosTest(){//Se listan las entidades creadas en pruebas.sql
        List<Object[]> productos = productoRepo.listarProductosValidos(LocalDate.now());
        productos.forEach(object -> System.out.println(object[0]+"---"+object[1]+"---"+object[2]+"----"+object[3]));
    }
    //Solucion con dto
    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarProductosValidosTest2(){//Se listan las entidades creadas en pruebas.sql
        List<ProductoValido> productos = productoRepo.listarProductosValidos2(LocalDate.now());
        productos.forEach(System.out::println);
    }
/*
    @Test
    @Sql("classpath:pruebas.sql")  solo funciona agregando la intermedia pero eso daña lo otro xd
    public  void listarProductosCategoriasTest(){//Se listan las entidades creadas en pruebas.sql
        List<Object[]> respuesta = productoRepo.obtenerTotalProductosPorCategoria();
        respuesta.forEach(resp -> System.out.println(resp[0]+"---"+resp[1]));
    }
  */
    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarProductosSinCOmentarios(){//Se listan las entidades creadas en pruebas.sql
        List<Producto> productos = productoRepo.obtenerProductosSinComentarios();
        productos.forEach(System.out::println);
    }


    @Test
    @Sql("classpath:pruebas.sql")
    public  void obtenerNombreTest(){//Se listan las entidades creadas en pruebas.sql
        List<Producto> productos = productoRepo.buscarProductoNombre("one");
        productos.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public  void obtenerProductosEnVenta(){//Se listan las entidades creadas en pruebas.sql
        List<ProductosPorUsuario> productos = productoRepo.obteneProductosEnVenta();
        productos.forEach(System.out::println);
    }
}
