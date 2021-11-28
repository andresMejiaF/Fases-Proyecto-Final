package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.io.Serializable;
import java.time.LocalDate;
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
    private LocalDate fechaSubasta;
    @JoinColumn(nullable = false)
    @ManyToOne
    private Producto producto;

    @OneToMany(mappedBy = "subasta")
    @ToString.Exclude
    private List<SubastaUsuario> subastaUsuarios;

    public Subasta(String codigo, LocalDate fechaSubasta, Producto producto ){
        this.codigo=codigo;
        this.fechaSubasta=fechaSubasta;
        this.producto=producto;
    }
}
