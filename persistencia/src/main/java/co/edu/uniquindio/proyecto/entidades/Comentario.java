package co.edu.uniquindio.proyecto.entidades;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@ToString
public class Comentario implements Serializable {
    @Id
    @Column(nullable = false,length = 10)
    @EqualsAndHashCode.Include
    private String codigo;

    @Column(nullable = false)
    private String mensaje;

    @Column(nullable = false)
    private String respuesta;

    @Column(nullable = false)
    private LocalDate fechaComentario;

    @Column(nullable = false)
    private double calificacion;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Usuario usuario;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Producto producto;
}
