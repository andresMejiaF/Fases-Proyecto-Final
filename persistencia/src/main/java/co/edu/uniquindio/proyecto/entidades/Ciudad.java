package co.edu.uniquindio.proyecto.entidades;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Ciudad implements Serializable {
    /**
     * Se crean condiciones como evitar espacios vacios
     * se crean variables y listas, tambien excluimos las
     * relaciones OneToMany del toString de Ciudad
     */
    @Id
    @EqualsAndHashCode.Include
    @Column(nullable = false, length = 30)
    private Integer codigo;
    @Column(nullable = false, length = 80)
    private String nombre;

    @OneToMany(mappedBy = "ciudad")
    @ToString.Exclude
    @JsonIgnore
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "ciudad")
    @ToString.Exclude
    @JsonIgnore
    private List<Producto> productos;

    public Ciudad(String nombre, Integer codigo) {
        this.nombre = nombre;
        this.codigo = codigo;

    }


}