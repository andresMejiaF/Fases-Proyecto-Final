package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Libro implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private String isbn;
    private String nombre;
    private Integer unidades;
    private Integer anio;



    public Libro(String isbn, String nombre, Integer unidades, Integer anio) {
        this.isbn = isbn;
        this.nombre= nombre;
        this.unidades= unidades;
        this. anio=anio;
    }


}

