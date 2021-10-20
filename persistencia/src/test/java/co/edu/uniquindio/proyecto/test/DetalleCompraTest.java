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
/**
 * Se crean los archivos de testeo para DetalleCompra,
 * Aqui probamos el modelo mediante el archivo sql para
 *  pruebas (pruebas.sql)
 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class DetalleCompraTest {


        @Autowired
        private DetalleCompraRepo detalleCompraRepo; //Repositorio
        @Autowired
        private ProductoRepo productoRepo;//Repo auxiliar
        @Autowired
        private CompraRepo compraRepo;//Repo Auxiliar

        @Test
        @Sql("classpath:pruebas.sql")
        public void registrarTest(){//Se crea la entidad para guardarla en el repositorio y verificar el registro

            Compra compra= compraRepo.findById("020").orElse(null);
            Producto producto= productoRepo.findById("9090").orElse(null);
            DetalleCompra detalleCompra = new DetalleCompra("372", 89, 50.00,  producto, compra);

            DetalleCompra detallecompraGuardado= detalleCompraRepo.save(detalleCompra);
            System.out.println(detallecompraGuardado);
            Assertions.assertNotNull(detallecompraGuardado);

        }


        @Test
        @Sql("classpath:pruebas.sql")//Archivo .sql
        public void eliminarTest(){//Se elimina una entidad del repositorio mediante su llave primaria


            detalleCompraRepo.deleteById("372");

            DetalleCompra detalleCompraBuscado= detalleCompraRepo.findById("372").orElse(null);

            Assertions.assertNull(detalleCompraBuscado);
        }

        @Test
        @Sql("classpath:pruebas.sql")
        public void actualizarTest(){//se actualiza una entidad del repositorio

            DetalleCompra detalleCompra = detalleCompraRepo.findById("372").orElse(null);;
            DetalleCompra guardado = detalleCompraRepo.save(detalleCompra);
            guardado.setUnidades(30);
            detalleCompraRepo.save(guardado);
            DetalleCompra detalleCompraBuscado= detalleCompraRepo.findById("372").orElse(null);
            Assertions.assertEquals(30, detalleCompraBuscado.getUnidades());
        }
        @Test
        @Sql("classpath:pruebas.sql")
        public  void listarTest(){//Se listan las entidades creadas en pruebas.sql
            List<DetalleCompra> detalleCompras= detalleCompraRepo.findAll();
            detalleCompras.forEach(detalleCompra -> System.out.println(detalleCompra));
        }


    }
