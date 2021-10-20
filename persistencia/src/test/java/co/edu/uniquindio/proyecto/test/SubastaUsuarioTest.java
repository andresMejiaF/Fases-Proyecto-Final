package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
import co.edu.uniquindio.proyecto.repositorios.SubastaUsuarioRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Se crean los archivos de testeo para SubastaUsuario,
 * Aqui probamos el modelo mediante el archivo sql para
 *  pruebas (pruebas.sql)
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubastaUsuarioTest {
    @Autowired
    SubastaUsuarioRepo subastaUsuarioRepo;//Repositorio
    @Autowired
    SubastaRepo subastaRepo; //Repositorio auxiliar
    @Autowired
    UsuarioRepo usuarioRepo; //Repositorio Auxiliar



    @Test
    @Sql("classpath:pruebas.sql")
    public void registrarTest(){
        Subasta subasta=subastaRepo.findById("8787").orElse(null);
        Usuario usuario=usuarioRepo.findById("456").orElse(null);

        SubastaUsuario subastaUsuario =new SubastaUsuario("1234", 30000, LocalDate.now(), usuario, subasta);

        SubastaUsuario subastaUsuarioGuardado= subastaUsuarioRepo.save(subastaUsuario);
        System.out.println(subastaUsuarioGuardado);
        Assertions.assertNotNull(subastaUsuarioGuardado);
    }

    @Test
    @Sql("classpath:pruebas.sql")//Archivo sql
    public void eliminarTest(){ //Se elimina una entidad del repositorio mediante su llave primaria
        subastaUsuarioRepo.deleteById("4567");
        SubastaUsuario subastaUsuarioBuscado = subastaUsuarioRepo.findById("4567").orElse(null);
        Assertions.assertNull(subastaUsuarioBuscado);
    }


    @Test
    @Sql("classpath:pruebas.sql")
    public void actualizarTest(){//se actualiza una entidad del repositorio

        SubastaUsuario guardado = subastaUsuarioRepo.findById("4567").orElse(null);
        guardado.setValor(50000);
        subastaUsuarioRepo.save(guardado);
        SubastaUsuario subastaUsuarioBuscado= subastaUsuarioRepo.findById("4567").orElse(null);
        Assertions.assertEquals(50000, subastaUsuarioBuscado.getValor());
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarTest(){//Se listan las entidades creadas en pruebas.sql
        List<SubastaUsuario> subastaUsuarios= subastaUsuarioRepo.findAll();
        subastaUsuarios.forEach(subastaUsuario -> System.out.println(subastaUsuario));
    }
}
