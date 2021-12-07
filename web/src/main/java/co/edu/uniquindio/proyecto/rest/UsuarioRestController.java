package co.edu.uniquindio.proyecto.rest;



import co.edu.uniquindio.proyecto.dto.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.proyecciones.UsuarioBase;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioRestController {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping
    public List<Usuario> listar(){
        return usuarioServicio.listarUsuarios();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable("id") String idusuario){
        try {
            Usuario usuario= usuarioServicio.obtenerUsuario(idusuario);
            return ResponseEntity.status(200).body(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Mensaje> crear(@RequestBody Usuario usuario){
        try {
            usuarioServicio.registrarUsuario(usuario);
           return ResponseEntity.status(201).body(new Mensaje("El usuario ha sido creado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }



    @PutMapping
    public  ResponseEntity<Mensaje> actualizar(@RequestBody Usuario usuario){
        try {
             usuarioServicio.actualizarUsuario(usuario);
            return ResponseEntity.status(200).body(new Mensaje("El usuario ha sido actualizado"));

        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mensaje> eliminar(@PathVariable("id") String id){
        try {
            usuarioServicio.eliminarUsuario(id);
            return ResponseEntity.status(200).body(new Mensaje("El usuario ha sido eliminado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));

        }
    }

    @GetMapping("/favoritos/{email}")
    public ResponseEntity<?> obtenerfavoritos(@PathVariable("email") String email){
        try {
          List<Producto> list=  usuarioServicio.listarFavoritos(email);
            return ResponseEntity.status(200).body(list);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

}
