package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class CompraTest {

    @Autowired
    private CompraRepo compraRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;

    @Test
    @Sql("classpath:pruebas.sql")
    public void registrarTest(){
        Usuario usuario=usuarioRepo.findById("456").orElse(null);
        Compra compra = new Compra("235", LocalDate.now(), "Efectivo", usuario);

        Compra compraGuardado= compraRepo.save(compra);
        System.out.println(compraGuardado);
        Assertions.assertNotNull(compraGuardado);

    }


    @Test
    @Sql("classpath:pruebas.sql")
    public void eliminarTest(){


        compraRepo.deleteById("020");
        Compra compraBuscado= compraRepo.findById("020").orElse(null);

        Assertions.assertNull(compraBuscado);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void actualizarTest(){


        Compra guardado = compraRepo.findById("020").orElse(null);
        guardado.setMedioPago("Efectivo");
        compraRepo.save(guardado);
        Compra compraBuscado= compraRepo.findById("020").orElse(null);
        Assertions.assertEquals("Efectivo", compraBuscado.getMedioPago());
    }
    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarTest(){
        List<Compra> compras= compraRepo.findAll();
        compras.forEach(compra -> System.out.println(compra));
    }

}
