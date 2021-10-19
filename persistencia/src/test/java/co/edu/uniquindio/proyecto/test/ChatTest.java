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

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //contexto de la app
public class ChatTest {

    @Autowired
    private ChatRepo chatRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;

    @Test
    @Sql("classpath:pruebas.sql")
    public void registrarTest(){

        Usuario usuario = usuarioRepo.findById("456").orElse(null);
       // Administrador administrador = new Administrador("11928", "mejiaAndres", "andress.mejiaf@", "andresM201");
        Chat chat=new Chat("100",usuario );

        Chat chatGuardado= chatRepo.save(chat);
        System.out.println(chatGuardado);
        Assertions.assertNotNull(chatGuardado);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void eliminarTest(){

        chatRepo.deleteById("100");

        Chat chatBuscado= chatRepo.findById("100").orElse(null);

        Assertions.assertNull(chatBuscado);
    }



    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarTest(){

        List<Chat> chats= chatRepo.findAll();

        chats.forEach(chat -> System.out.println(chat));
    }

}
