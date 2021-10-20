package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
/**
 * Se crean los archivos de testeo para Chat,
 * Aqui probamos el modelo mediante el archivo sql para
 *  pruebas (pruebas.sql)
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //contexto de la app
public class ChatTest {

    @Autowired
    private ChatRepo chatRepo;//repositorio
    @Autowired
    private UsuarioRepo usuarioRepo;//repositorio auxiliar

    @Test
    @Sql("classpath:pruebas.sql")//Archivos .sql
    public void registrarTest(){//Se crea la entidad para guardarla en el repositorio y verificar el registro

        Usuario usuario = usuarioRepo.findById("456").orElse(null);
        Chat chat=new Chat("100",usuario );

        Chat chatGuardado= chatRepo.save(chat);
        System.out.println(chatGuardado);
        Assertions.assertNotNull(chatGuardado);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void eliminarTest(){//Se elimina una entidad del repositorio mediante su llave primaria

        chatRepo.deleteById("100");

        Chat chatBuscado= chatRepo.findById("100").orElse(null);

        Assertions.assertNull(chatBuscado);
    }



    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarTest(){//Se listan las entidades creadas en pruebas.sql

        List<Chat> chats= chatRepo.findAll();

        chats.forEach(chat -> System.out.println(chat));
    }

}
