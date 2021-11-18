package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
/**
 * Se crean los archivos de testeo para Ciudad,
 * Aqui probamos el modelo mediante el archivo sql para
 *  pruebas (pruebas.sql)
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest {

    @Autowired
    private CiudadRepo ciudadRepo;//repositorio

    @Test
    public void registrarTest(){//Se crea la entidad para guardarla en el repositorio y verificar el registro

        Ciudad ciudad = new Ciudad("Manizales",6567);

        Ciudad ciudadGuardado= ciudadRepo.save(ciudad);
        System.out.println(ciudadGuardado);
        Assertions.assertNotNull(ciudadGuardado);
    }


    @Test
    @Sql("classpath:pruebas.sql")//Archivo sql
    public void eliminarTest(){//Se elimina una entidad del repositorio mediante su llave primaria

        ciudadRepo.deleteById(123);

        Ciudad ciudadBuscado= ciudadRepo.findById(123).orElse(null);

        Assertions.assertNull(ciudadBuscado);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void actualizarTest(){//se actualiza una entidad del repositorio

        Ciudad guardado = ciudadRepo.findById(123).orElse(null);
        guardado.setNombre("Cali");
        ciudadRepo.save(guardado);
        Ciudad ciudadBuscado= ciudadRepo.findById(123).orElse(null);
        Assertions.assertEquals("Cali", ciudadBuscado.getNombre());
    }
    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarTest(){//Se listan las entidades creadas en pruebas.sql
        List<Ciudad> ciudades= ciudadRepo.findAll();
        ciudades.forEach(ciudad -> System.out.println(ciudad));
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarUsuariosCiudadTest(){//Se listan las entidades creadas en pruebas.sql
        List<Usuario> usuarios = ciudadRepo.listarUsuarios("Armenia");
        usuarios.forEach(System.out::println);
        Assertions.assertEquals(3, usuarios.size());
    }
}
