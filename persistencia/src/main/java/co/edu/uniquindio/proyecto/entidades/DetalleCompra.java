package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

/**
 * Clase o entidad Detallecompra, restricciones para atributos y uso de lombok
 */
public class DetalleCompra implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;
    @Column(nullable = false)
    @Positive
    private Integer unidades;

    @Column(nullable = false)
    @Positive
    private double precioProducto;
    @JoinColumn(nullable = false)
    @ManyToOne
    private Producto producto;
    @JoinColumn(nullable = false)
    @ManyToOne
    private Compra compra;

    public DetalleCompra(Integer unidades, double precioProducto, Producto producto, Compra compra){
        this.unidades=unidades;
        this.precioProducto=precioProducto;
        this.producto=producto;
        this.compra=compra;
    }
}