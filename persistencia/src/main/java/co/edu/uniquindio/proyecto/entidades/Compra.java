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
@ToString
/**
 * Clase compra, restricciones para atributos y uso de lombok
 */
public class Compra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;
    @Column(nullable = false)
    private LocalDate fechaCompra;
    @Column(nullable = false)
    private  String medioPago;
    @JoinColumn(nullable = false)
    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "compra")
    @ToString.Exclude
    private List<DetalleCompra> detalleCompras;

    public Compra( LocalDate fechaCompra, String medioPago, Usuario usuario) {
        this.fechaCompra=fechaCompra;
        this.medioPago=medioPago;
        this.usuario=usuario;
    }
}