package co.edu.uniquindio.proyecto.entidades;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@ToString
/**
    Variadas restricciones para tipos de datos
    como por ejemplo JoinColumn
    donde no permitimos que un objeto o llave foranea sea nula
 */
public class Comentario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    @NotBlank
    private String mensaje;

    @Column(length = 1000)
    private String respuesta;

    @Column(nullable = false)
    private LocalDate fechaComentario;

    @Column(nullable = false)
    @PositiveOrZero
    private Integer calificacion;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Usuario usuario;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Producto producto;
}
