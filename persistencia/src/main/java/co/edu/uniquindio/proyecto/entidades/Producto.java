package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producto implements Serializable {
    @Id
    @Column(nullable = false,length = 10)
    @EqualsAndHashCode.Include
    private String codigo;

    private String nombre;

    private int unidades;

    private String descripcion;

    private double precio;

    private LocalDate fechaLimite;

    private double descuento;

    @ManyToOne
    private Usuario codigoVendedor;

    @ManyToMany
    private List<Usuario> codigoUsuario;
    @OneToMany(mappedBy = "codigoProducto")
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "codigoProducto")
    private List<DetalleCompra> detalleCompras;

    @ManyToOne
    private Ciudad codigoCiudad;

    @OneToMany(mappedBy = "codigoProducto")
    private List<Subasta> subastas;
    @ManyToMany
    private List<Categoria> codigoCategorias;

    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> imagen;
}
