package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Se crean los archivos de testeo para Usuario,
 * Aqui probamos el modelo mediante el archivo sql para
 *  pruebas (pruebas.sql)
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //contexto de la app
public class UsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;//Repositorio

    @Autowired
    private CiudadRepo ciudadRepo;//Repositorio auxiliar

    @Test
    @Sql("classpath:pruebas.sql")
    public void registrarTest(){ //Se crea la entidad para guardarla en el repositorio y verificar el registro
        Ciudad ciudad= ciudadRepo.findById(123).orElse(null);
        Map<String, String> telefonos= new HashMap<>();
        telefonos.put("Casa", "3104327744");
        telefonos.put("Celular", "3214161");
        Usuario usuario= new Usuario("123", "Andres mejia", "Andress@email", "mejia01", telefonos, ciudad);
        Usuario usuarioGuardado= usuarioRepo.save(usuario);
        System.out.println(usuarioGuardado);
        Assertions.assertNotNull(usuarioGuardado);

    }
    @Test
    @Sql("classpath:pruebas.sql")//Archivo sql
    public void eliminarTest(){ //Se elimina una entidad del repositorio mediante su llave primaria

        usuarioRepo.deleteById("456");

        Usuario usuarioBuscado= usuarioRepo.findById("456").orElse(null);

        Assertions.assertNull(usuarioBuscado);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void actualizarTest(){//se actualiza una entidad del repositorio


        Usuario guardado = usuarioRepo.findById("456").orElse(null);

        guardado.setEmail("nuevoEmail@email");
        //guardo el usuario
        usuarioRepo.save(guardado);

        Usuario usuariobuscado= usuarioRepo.findById("456").orElse(null);

        Assertions.assertEquals("nuevoEmail@email", usuariobuscado.getEmail());

    }

    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarTest(){//Se listan las entidades creadas en pruebas.sql

        List<Usuario> usuarios= usuarioRepo.findAll();

        usuarios.forEach(usuario -> System.out.println(usuario));
    }
    @Test
    @Sql("classpath:pruebas.sql")
    public void filtrarNombreTest(){
       // List <Usuario> lista = usuarioRepo.findAllByNombreContains("santiago Mejia");
        Optional<Usuario> usuario = usuarioRepo.findAllByEmail("santiago@12");

        // lista.forEach(usuario -> System.out.println(usuario));
       // lista.forEach( System.out::println);
        if(usuario.isPresent()){
            System.out.println(usuario.get());

        }else {
            System.out.println("eso no existe ole");
        }

    }
    @Test
    @Sql("classpath:pruebas.sql")
    public void paginarListaTest(){
        Pageable paginador =  PageRequest.of(0, 2);
        Page<Usuario> lista = usuarioRepo.findAll(paginador);
        System.out.println(lista.stream().collect(Collectors.toList()));
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void ordenarListaTest(){
        List<Usuario> lista = usuarioRepo.findAll(Sort.by("nombre"));
        System.out.println(lista);
    }
}
