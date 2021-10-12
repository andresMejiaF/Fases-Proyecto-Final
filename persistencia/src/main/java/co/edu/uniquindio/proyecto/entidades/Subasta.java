package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Subasta implements Serializable {
    @Id
    @Column(nullable = false,length = 10)
    @EqualsAndHashCode.Include
    private String codigo;

    @Column(nullable = false)
    private LocalDate fechaSubasta;
    @JoinColumn(nullable = false)
    @ManyToOne
    private Producto producto;

    @OneToMany(mappedBy = "subasta")
    private List<SubastaUsuario> subastaUsuarios;

}
