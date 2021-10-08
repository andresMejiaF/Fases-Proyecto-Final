package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Usuario extends Persona implements Serializable {


    @Column(nullable = false, unique = true, length = 120)
    private String email;
    @ElementCollection
    @Column(nullable = false)
    private Map <String, String> numTelefono;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Ciudad ciudad;

    @OneToMany(mappedBy = "usuarioPrestamo")
    private List<Prestamo> prestamos;

}
