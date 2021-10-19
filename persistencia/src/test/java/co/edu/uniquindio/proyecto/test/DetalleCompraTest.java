package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
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
public class DetalleCompraTest {


        @Autowired
        private DetalleCompraRepo detalleCompraRepo;
        @Autowired
        private ProductoRepo productoRepo;
        @Autowired
        private CompraRepo compraRepo;

        @Test
        @Sql("classpath:pruebas.sql")
        public void registrarTest(){

            Compra compra= compraRepo.findById("020").orElse(null);
            Producto producto= productoRepo.findById("9090").orElse(null);
            DetalleCompra detalleCompra = new DetalleCompra("372", 89, 50.00,  producto, compra);

            DetalleCompra detallecompraGuardado= detalleCompraRepo.save(detalleCompra);
            System.out.println(detallecompraGuardado);
            Assertions.assertNotNull(detallecompraGuardado);

        }


        @Test
        @Sql("classpath:pruebas.sql")
        public void eliminarTest(){


            detalleCompraRepo.deleteById("372");

            DetalleCompra detalleCompraBuscado= detalleCompraRepo.findById("372").orElse(null);

            Assertions.assertNull(detalleCompraBuscado);
        }

        @Test
        @Sql("classpath:pruebas.sql")
        public void actualizarTest(){

            DetalleCompra detalleCompra = detalleCompraRepo.findById("372").orElse(null);;
            DetalleCompra guardado = detalleCompraRepo.save(detalleCompra);
            guardado.setUnidades(30);
            detalleCompraRepo.save(guardado);
            DetalleCompra detalleCompraBuscado= detalleCompraRepo.findById("372").orElse(null);
            Assertions.assertEquals(30, detalleCompraBuscado.getUnidades());
        }
        @Test
        @Sql("classpath:pruebas.sql")
        public  void listarTest(){
            List<DetalleCompra> detalleCompras= detalleCompraRepo.findAll();
            detalleCompras.forEach(detalleCompra -> System.out.println(detalleCompra));
        }


    }
