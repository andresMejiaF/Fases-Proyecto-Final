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
public class Compra implements Serializable {

    @Id
    @Column(nullable = false,length = 10)
    @EqualsAndHashCode.Include
    private String codigo;
    @Column(nullable = false)
    private LocalDate fechaCompra;
    @Column(nullable = false, length = 10)
    private  String medioPago;
    @JoinColumn(nullable = false)
    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "compra")
    private List<DetalleCompra> detalleCompras;

}