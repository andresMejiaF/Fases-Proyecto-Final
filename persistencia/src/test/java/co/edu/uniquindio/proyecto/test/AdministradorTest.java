package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdministradorTest {

    @Autowired
    private AdministradorRepo administradorRepo;
    @Test
    public void regirtrarTest(){

        Administrador administrador = new Administrador("11928", "mejiaAndres", "andress.mejiaf@", "andresM201");

        Administrador administradorGuardado= administradorRepo.save(administrador);
        System.out.println(administradorGuardado);
        Assertions.assertNotNull(administradorGuardado);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void eliminarTest(){
        administradorRepo.deleteById("119");
        Administrador administradorBuscado = administradorRepo.findById("119").orElse(null);
        Assertions.assertNull(administradorBuscado);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void actualizarTest(){

        Administrador guardado = administradorRepo.findById("119").orElse(null);
        guardado.setEmail("nuevoEmail@email");
        administradorRepo.save(guardado);
        Administrador administradorBuscado= administradorRepo.findById("119").orElse(null);
        Assertions.assertEquals("nuevoEmail@email", administradorBuscado.getEmail());
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarTest(){
        List<Administrador> administradores= administradorRepo.findAll();
        administradores.forEach(administrador -> System.out.println(administrador));
    }
}
