package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTest {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    public void  registrarTest(){

        //Para anexar la ciudad tendriamos que tener una anteriormente creada
        Map<String, String> telefonos= new HashMap<>();
        telefonos.put("Casa", "3104327744");
        telefonos.put("Celular", "3214161");
        Usuario u = new Usuario("57908","Carlos", "prueba2@Gmail", "password123",telefonos, null,"pruebaUsu" );

        try {
            Usuario respuesta= usuarioServicio.registrarUsuario(u);
            System.out.println(respuesta);
            Assertions.assertNotNull(respuesta);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }
    @Test
    public void eliminarTest(){
        try {
            usuarioServicio.eliminarUsuario("5790");
            Assertions.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }
    @Test
    public void listarTest() throws Exception {
        Map<String, String> telefonos= new HashMap<>();
        telefonos.put("Casa", "3104327744");
        telefonos.put("Celular", "3214161");
        Usuario u = new Usuario("5790238","Mario", "prueba23@Gmail", "password123",telefonos, null,"pruebaUsuar" );
        usuarioServicio.registrarUsuario(u);
        List<Usuario> list= usuarioServicio.listarUsuarios();

        list.forEach(System.out::println);
    }

    @Test
    public  void  actualizarTest(){
        try {
            Usuario u= usuarioServicio.obtenerUsuario("5790");
            u.setPassword("nuevaContraseña");
            usuarioServicio.actualizarUsuario(u);
            Usuario modificado= usuarioServicio.obtenerUsuario("5790");
            Assertions.assertEquals("nuevaContraseña", modificado.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public  void  loginTest() {

        try {
            Usuario usuario =usuarioServicio.iniciarSesion("prueba2@Gmail", "password123");
            Assertions.assertNotNull(usuario);
        } catch (Exception e) {
            Assertions.assertTrue(false, e.getMessage());
        }

    }
}
