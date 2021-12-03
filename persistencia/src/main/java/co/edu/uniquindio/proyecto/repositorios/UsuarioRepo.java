package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Se crea el Repositorio para  Usuario
 * con su Entidad y tipo de dato de su llave primaria
 */
@Repository
public interface UsuarioRepo extends JpaRepository <Usuario, String>{
 /*
    @Query("select u from Usuario u where u.nombre= : nombre")
    List<Usuario> obtenerUsuariosPorNombre(String nombre);
   */
 //Hace lo mismo los dos,trae el nombre de los usuarios
    List <Usuario> findAllByNombreContains(String nombre);//Este regresa una lista de usuarios con ese nombre
    Optional<Usuario> findAllByEmail(String email);//Este regresa el usuario con ese correo
   /*
    @Query("select u from Usuario u where u.email= :email and u.password=: clave")
    Optional<Usuario> verificarAutenticacion(String email, String clave);
    */
    Optional<Usuario> findByEmailAndPassword(String email, String clave);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByUsername(String username);

    Page<Usuario> findAll(Pageable paginador);
    //Corregir para que liste los favoritos ( es que hay dos)
    @Query ("select p from Usuario u, IN (u.productosFavoritos) p where u.email = : email")
    List <Producto> obtenerProductoFavoritos(String email);

    @Query("select  u.email,u.nombre, p from Usuario  u left join  u.productos p")
     List<Object[]> listarUsuariosYProductos();

    //Cantidad de usuarios por ciudad
    @Query("select c.nombre,  count(u) from  Usuario  u join u.ciudad c group by c")
    List<Object[]> obtenerUsuariosPorCiudad();

    /*
    @Query("select u.telefonos from Usuario  u join u.telefonos where u.codigo=:codigo")
    ArrayList<String> obtenerTelefonos(String codigo);
     */
}

