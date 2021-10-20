package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.SubastaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Se crea el Repositorio para  SubastaUsuario
 * con su Entidad y tipo de dato de su llave primaria
 */
public interface SubastaUsuarioRepo extends JpaRepository<SubastaUsuario, String> {
}
