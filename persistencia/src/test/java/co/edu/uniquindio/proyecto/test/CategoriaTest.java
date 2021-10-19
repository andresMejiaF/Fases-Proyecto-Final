package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class CategoriaTest {

    @Autowired
    private CategoriaRepo categoriaRepo;

    @Test
    public void registrarTest(){

        Categoria categoria = new Categoria();
        categoria.setCodigo("3672");
        categoria.setNombre("Electrodomesticos");

        Categoria categoriaGuardado= categoriaRepo.save(categoria);
        System.out.println(categoriaGuardado);
        Assertions.assertNotNull(categoriaGuardado);

    }


    @Test
    @Sql("classpath:pruebas.sql")
    public void eliminarTest(){

        categoriaRepo.deleteById("3672");

        Categoria categoriaBuscado= categoriaRepo.findById("3672").orElse(null);

        Assertions.assertNull(categoriaBuscado);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void actualizarTest(){



        Categoria guardado = categoriaRepo.findById("3672").orElse(null);
        guardado.setNombre("Deportes extremos");
        categoriaRepo.save(guardado);
        Categoria categoriaBuscado= categoriaRepo.findById("3672").orElse(null);
        Assertions.assertEquals("Deportes extremos", categoriaBuscado.getNombre());
    }
    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarTest(){
        List<Categoria> categorias= categoriaRepo.findAll();
        categorias.forEach(categoria -> System.out.println(categoria));
    }
}
