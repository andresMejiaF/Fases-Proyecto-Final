package co.edu.uniquindio.proyecto.infoDefc;


import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CategoriaServicio;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class InformacionPorDefecto implements CommandLineRunner {

    @Autowired
    private  CiudadServicio ciudadServicio;
    @Autowired
    private CategoriaServicio categoriaServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;



    @Override
    public void run(String... args) throws Exception {
        Usuario usuario = usuarioServicio.obtenerUsuarioAdmin("pruebayespacio@gmail.com");

        if(usuario==null){
            ciudadServicio.registrarCiudad(new Ciudad("Cali", 000));
            Ciudad ciudad= ciudadServicio.obtenerCiudad(000);
            usuario= new Usuario();
            List<String> telef= new ArrayList<>();
            telef.add("322 928 0432");
            usuario.setCodigo("0000");
            usuario.setNombre("Admin_SAJV");
            usuario.setEmail("pruebayespacio@gmail.com");
            usuario.setPassword("admin");
            usuario.setUsername("ADMINISTRADOR");
            usuario.setCiudad(ciudad);
            usuario.setTelefonos(telef);
            usuarioServicio.registrarUsuario(usuario);
        }


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
