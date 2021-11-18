package co.edu.uniquindio.proyecto.repositorios;


import co.edu.uniquindio.proyecto.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Se crea el Repositorio para  Comentario
 * con su Entidad y tipo de dato de su llave primaria
 */
@Repository
public interface ComentarioRepo extends JpaRepository<Comentario, String> {
    /*
    @Query("select c from  Comentario c  where c.calificacion> :calificacionMenor and c.calificacion <: calificacionMayor")
    List<Comentario> listarComentarios(int calificacionMenor, int calificacionMayor);

    @Query("select c from  Comentario c  where c.calificacion between : calificacionMenor and :calificacionMayor ")
    List<Comentario> listarComentarios2(int calificacionMenor, int calificacionMayor);
    */
}

