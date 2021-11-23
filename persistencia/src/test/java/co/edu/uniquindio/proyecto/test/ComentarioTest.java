package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
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
 * Se crean los archivos de testeo para Comentario,
 * Aqui probamos el modelo mediante el archivo sql para
 *  pruebas (pruebas.sql)
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ComentarioTest {

    @Autowired
    ComentarioRepo comentarioRepo;//Repositorio
    @Autowired
    ProductoRepo productoRepo; //Repositorio Auxiliar
    @Autowired
    UsuarioRepo usuarioRepo;//Repositorio Auxiliar

    @Test
    @Sql("classpath:pruebas.sql")
    public void regirtrarTest(){//Se crea la entidad para guardarla en el repositorio y verificar el registro

        Producto producto=productoRepo.findById("9090").orElse(null);
        Usuario usuario=usuarioRepo.findById("456").orElse(null);

        Comentario comentario=new Comentario(1, "Buen producto", "Gracias", LocalDate.now(), 3.0, usuario, producto);
        Comentario comentarioGuardado= comentarioRepo.save(comentario);
        System.out.println(comentarioGuardado);
        Assertions.assertNotNull(comentarioGuardado);
    }

    @Test
    @Sql("classpath:pruebas.sql")//Archivo.sql
    public void eliminarTest(){//Se elimina una entidad del repositorio mediante su llave primaria
        comentarioRepo.deleteById(934);
        Comentario comentarioBuscado = comentarioRepo.findById(934).orElse(null);
        Assertions.assertNull(comentarioBuscado);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void actualizarTest(){//se actualiza una entidad del repositorio

        Comentario comentario = comentarioRepo.findById(934).orElse(null);
        Comentario guardado = comentarioRepo.findById(934).orElse(null);
        guardado.setRespuesta("Maravilloso");
        comentarioRepo.save(guardado);
        Comentario comentarioBuscado= comentarioRepo.findById(934).orElse(null);
        Assertions.assertEquals("Maravilloso", comentarioBuscado.getRespuesta());
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarTest(){//Se listan las entidades creadas en pruebas.sql
        List<Comentario> comentarios= comentarioRepo.findAll();
        comentarios.forEach(comentario -> System.out.println(comentario));
    }

}
