package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;
/**
 * Se crean los archivos de testeo para Subasta,
 * Aqui probamos el modelo mediante el archivo sql para
 *  pruebas (pruebas.sql)
 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class SubastaTest {

    @Autowired
    private SubastaRepo subastaRepo;//Repositorio
    @Autowired
    private ProductoRepo productoRepo; //Repositorio Auxiliar

    @Test
    @Sql("classpath:pruebas.sql")
    public void registrarTest(){ //Se crea la entidad para guardarla en el repositorio y verificar el registro

        Producto producto = productoRepo.findById("9090").orElse(null);

        Subasta subasta= new Subasta("234", LocalDate.now(), producto );

        Subasta subastaGuardado= subastaRepo.save(subasta);
        System.out.println(subastaGuardado);
        Assertions.assertNotNull(subastaGuardado);

    }



    @Test
    @Sql("classpath:pruebas.sql")//Archivo sql
    public void eliminarTest(){ //Se elimina una entidad del repositorio mediante su llave primaria
        subastaRepo.deleteById("8787");
        Subasta subastaBuscado = subastaRepo.findById("8787").orElse(null);
        Assertions.assertNull(subastaBuscado);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void actualizarTest(){//se actualiza una entidad del repositorio

        Producto producto = productoRepo.findById("4546").orElse(null);
        Subasta guardado = subastaRepo.findById("8787").orElse(null);
        guardado.setProducto(producto);
        subastaRepo.save(guardado);
        Subasta subastaBuscado= subastaRepo.findById("8787").orElse(null);
        Assertions.assertEquals(producto, subastaBuscado.getProducto());
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarTest(){//Se listan las entidades creadas en pruebas.sql
        List<Subasta> subastas= subastaRepo.findAll();
        subastas.forEach(subasta -> System.out.println(subasta));
    }


}
