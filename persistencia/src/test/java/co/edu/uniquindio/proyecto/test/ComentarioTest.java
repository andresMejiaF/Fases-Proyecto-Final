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

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ComentarioTest {

    @Autowired
    ComentarioRepo comentarioRepo;
    @Autowired
    ProductoRepo productoRepo;
    @Autowired
    UsuarioRepo usuarioRepo;

    @Test
    @Sql("classpath:pruebas.sql")
    public void regirtrarTest(){

        Producto producto=productoRepo.findById("9090").orElse(null);
        Usuario usuario=usuarioRepo.findById("456").orElse(null);

        Comentario comentario=new Comentario("1234", "Buen producto", "Gracias", LocalDate.now(), 3.0, usuario, producto);
        Comentario comentarioGuardado= comentarioRepo.save(comentario);
        System.out.println(comentarioGuardado);
        Assertions.assertNotNull(comentarioGuardado);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void eliminarTest(){
        comentarioRepo.deleteById("934");
        Comentario comentarioBuscado = comentarioRepo.findById("934").orElse(null);
        Assertions.assertNull(comentarioBuscado);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void actualizarTest(){

        Comentario comentario = comentarioRepo.findById("934").orElse(null);
        Comentario guardado = comentarioRepo.findById("934").orElse(null);
        guardado.setRespuesta("Maravilloso");
        comentarioRepo.save(guardado);
        Comentario comentarioBuscado= comentarioRepo.findById("934").orElse(null);
        Assertions.assertEquals("Maravilloso", comentarioBuscado.getRespuesta());
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarTest(){
        List<Comentario> comentarios= comentarioRepo.findAll();
        comentarios.forEach(comentario -> System.out.println(comentario));
    }

}
