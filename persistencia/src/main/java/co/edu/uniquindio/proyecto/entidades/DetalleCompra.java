package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@AllArgsConstructor
/**
 * Clase o entidad Detallecompra, restricciones para atributos y uso de lombok
 */
public class DetalleCompra implements Serializable {
    @Id
    @Column(nullable = false,length = 10)
    @EqualsAndHashCode.Include
    private String codigo;
    @Column(nullable = false)
    private Integer unidades;

    @Column(nullable = false)
    private double precioProducto;
    @JoinColumn(nullable = false)
    @ManyToOne
    private Producto producto;
    @JoinColumn(nullable = false)
    @ManyToOne
    private Compra compra;
}