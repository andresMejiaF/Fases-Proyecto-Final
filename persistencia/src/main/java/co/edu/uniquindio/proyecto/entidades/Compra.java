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
public class Compra implements Serializable {

    @Id
    @Column(nullable = false,length = 10)
    @EqualsAndHashCode.Include
    private String codigo;
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

    public Compra(String codigo, LocalDate fechaCompra, String medioPago, Usuario usuario) {
        this.codigo = codigo;
        this.fechaCompra=fechaCompra;
        this.medioPago=medioPago;
        this.usuario=usuario;
    }
}