package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import org.hibernate.engine.jdbc.SerializableBlobProxy;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import javax.validation.constraints.Future;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Prestamo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;
    @Column(nullable = false, columnDefinition ="TiMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaPrestamo ;

    @Future
    @Column(nullable = false)
    private LocalDate fechaDevolucion;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuarioPrestamo;

    @ManyToMany
    private List<Libro> libros;

    private int estatura;

}
