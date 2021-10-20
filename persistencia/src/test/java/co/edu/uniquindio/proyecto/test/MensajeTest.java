package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
import co.edu.uniquindio.proyecto.repositorios.MensajeRepo;
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
 * Se crean los archivos de testeo para Mensaje,
 * Aqui probamos el modelo mediante el archivo sql para
 *  pruebas (pruebas.sql)
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MensajeTest {

    @Autowired
    private MensajeRepo mensajeRepo;//Repositorio

    @Autowired
    private ChatRepo chatRepo;//RepoAuxiliar

    @Test
    @Sql("classpath:pruebas.sql")
    public void registrarTest(){  //Se crea la entidad para guardarla en el repositorio y verificar el registro

        Chat chat= chatRepo.findById("100").orElse(null);

        Mensaje mensaje= new Mensaje("3312","Hola como estas?","Juan carlos", LocalDate.now(), chat );
        Mensaje mensajeGuardado= mensajeRepo.save(mensaje);
        System.out.println(mensajeGuardado);
        //regresaun valor diferente de null, es decir; la prueba paso
        Assertions.assertNotNull(mensajeGuardado);

    }

    @Test
    @Sql("classpath:pruebas.sql")//archivo.sql
    public void eliminarTest(){//Se elimina una entidad del repositorio mediante su llave primaria

        mensajeRepo.deleteById("999");

        Mensaje mensajeBucado= mensajeRepo.findById("999").orElse(null);

        Assertions.assertNull(mensajeBucado);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void actualizarTest(){//se actualiza una entidad del repositorio


        Mensaje guardado = mensajeRepo.findById("999").orElse(null);

        guardado.setMensaje("estudiaste ?");

        //guardo el usuario
        mensajeRepo.save(guardado);

        Mensaje mensajeBuscado= mensajeRepo.findById("999").orElse(null);

        Assertions.assertEquals("estudiaste ?", mensajeBuscado.getMensaje());

    }

    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarTest(){ //Se listan las entidades creadas en pruebas.sql

        List<Mensaje> mensajes= mensajeRepo.findAll();

        mensajes.forEach(mensaje -> System.out.println(mensaje));
    }
}
