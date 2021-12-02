package co.edu.uniquindio.proyecto.infoDefc;


import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.servicios.CategoriaServicio;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class InformacionPorDefecto implements CommandLineRunner {

    @Autowired
    private  CiudadServicio ciudadServicio;
    @Autowired
    private CategoriaServicio categoriaServicio;




    @Override
    public void run(String... args) throws Exception {

        if(ciudadServicio.listarCiudades().isEmpty() ) {
            ciudadServicio.registrarCiudad(new Ciudad("Armenia", 01));
            ciudadServicio.registrarCiudad(new Ciudad("Bogota", 02));
            ciudadServicio.registrarCiudad(new Ciudad("Manizales", 03));
            ciudadServicio.registrarCiudad(new Ciudad("Pereira", 04));
            ciudadServicio.registrarCiudad(new Ciudad("Florencia", 05));
            ciudadServicio.registrarCiudad(new Ciudad("Bucaramanga", 06));
            ciudadServicio.registrarCiudad(new Ciudad("Medellin", 07));
            ciudadServicio.registrarCiudad(new Ciudad("Cartagena", 12));
        }

        if(categoriaServicio.listCategorias().isEmpty()) {
            categoriaServicio.registrarCategoria(new Categoria("01", "Tecnologia" ));
            categoriaServicio.registrarCategoria(new Categoria("02", "Deporte" ));
            categoriaServicio.registrarCategoria(new Categoria("03", "Ropa" ));
            categoriaServicio.registrarCategoria(new Categoria("04", "Libros" ));
            categoriaServicio.registrarCategoria(new Categoria("05", "Videojuegos" ));
            categoriaServicio.registrarCategoria(new Categoria("06", "Electrodomesticos" ));
            categoriaServicio.registrarCategoria(new Categoria("09", "Otros" ));
        }



    }




}
