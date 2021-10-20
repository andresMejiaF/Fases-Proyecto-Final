package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Se crea el Repositorio para  Chat
 * con su Entidad y tipo de dato de su llave primaria
 */
@Repository
public interface ChatRepo extends JpaRepository<Chat, String> {

}
