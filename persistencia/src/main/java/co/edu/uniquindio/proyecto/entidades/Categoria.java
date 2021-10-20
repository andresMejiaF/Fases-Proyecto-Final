package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
/**
 * Se crea una llave principal con una condición
 * se crea una variable nombre y una lista productos
 */
public class Categoria implements Serializable {
    @Id
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private String codigo;

    @Column(nullable = false)
    private String nombre;

    @ManyToMany(mappedBy = "categorias")
    private List<Producto> productos;

}
