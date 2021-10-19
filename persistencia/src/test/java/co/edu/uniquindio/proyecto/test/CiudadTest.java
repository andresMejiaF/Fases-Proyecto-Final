package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest {

    @Autowired
    private CiudadRepo ciudadRepo;

    @Test
    public void registrarTest(){

        Ciudad ciudad = new Ciudad("Manizales",6567);

        Ciudad ciudadGuardado= ciudadRepo.save(ciudad);
        System.out.println(ciudadGuardado);
        Assertions.assertNotNull(ciudadGuardado);
    }


    @Test
    @Sql("classpath:pruebas.sql")
    public void eliminarTest(){

        ciudadRepo.deleteById(123);

        Ciudad ciudadBuscado= ciudadRepo.findById(123).orElse(null);

        Assertions.assertNull(ciudadBuscado);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void actualizarTest(){

        Ciudad guardado = ciudadRepo.findById(123).orElse(null);
        guardado.setNombre("Cali");
        ciudadRepo.save(guardado);
        Ciudad ciudadBuscado= ciudadRepo.findById(123).orElse(null);
        Assertions.assertEquals("Cali", ciudadBuscado.getNombre());
    }
    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarTest(){
        List<Ciudad> ciudades= ciudadRepo.findAll();
        ciudades.forEach(ciudad -> System.out.println(ciudad));
    }
}
