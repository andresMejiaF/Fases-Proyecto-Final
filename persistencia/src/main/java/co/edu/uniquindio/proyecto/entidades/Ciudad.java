package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor

public class Ciudad implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @Column(nullable = false, length = 30)
    private Integer codigo;
    @Column(nullable = false, length = 80)
    private String nombre;

    @OneToMany(mappedBy = "ciudad")
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "ciudad")
    private List<Producto> productos;

    public Ciudad(String nombre, Integer codigo) {
        this.nombre = nombre;
        this.codigo = codigo;

    }

    @Override
    public String toString() {
        return "Ciudad{" +
                "codigo=" + codigo +
                " nombre="+ nombre +'}';
    }
}