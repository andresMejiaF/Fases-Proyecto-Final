package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
/**
 * Clase Producto, restricciones para atributos y uso de lombok
 */
public class Producto implements Serializable {
    @Id
    @Column(nullable = false,length = 10)
    @EqualsAndHashCode.Include
    private String codigo;

    @NotBlank(message = "El nombre del producto no deberia estar vacio, es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @PositiveOrZero
    @Column(nullable = false)
    private int unidades;
    @NotBlank
    @Column(nullable = false)
    private String descripcion;
    @Positive
    @Column(nullable = false)
    private double precio;
    @Future
    @Column(nullable = false)
    private LocalDate fechaLimite;
    @Column(nullable = false)
    private double descuento;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Usuario vendedor;

    @ManyToMany(mappedBy = "productosFavoritos")
    private List<Usuario> usuarios;
    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<DetalleCompra> detalleCompras;
    //@JoinColumn(nullable = false)
    @ManyToOne
    private Ciudad ciudad;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Subasta> subastas;
    @ManyToMany
    private List<Categoria> categorias;

    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> imagen;
}
