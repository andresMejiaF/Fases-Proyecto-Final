package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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