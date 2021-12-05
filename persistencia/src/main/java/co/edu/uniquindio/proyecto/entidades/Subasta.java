package co.edu.uniquindio.proyecto.entidades;

import jdk.jfr.Timestamp;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
/**
 * Clase Subasta, restricciones para atributos y uso de lombok
 */
public class Subasta implements Serializable {
    @Id
    @Column(nullable = false,length = 10)
    @EqualsAndHashCode.Include
    private String codigo;

    @Column(nullable = false)
    @Future(message = "La fecha debe estar definida en el futuro")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Timestamp
    private LocalDateTime fechaSubasta;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Producto producto;

    @Getter @Setter
    private Integer tiempoLimite;

    @JoinColumn(nullable = false)
    @ManyToOne
    @ToString.Exclude
    private Usuario vendedor;

    @OneToMany(mappedBy = "subasta")
    @ToString.Exclude
    private List<SubastaUsuario> subastaUsuarios;

    public Subasta(String codigo, LocalDateTime fechaSubasta, Producto producto ){
        this.codigo=codigo;
        this.fechaSubasta=fechaSubasta;
        this.producto=producto;
    }
}
