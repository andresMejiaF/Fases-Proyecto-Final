package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Ciudad implements Serializable {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    private String nombre;




    public Ciudad(String nombre, Integer codigo) {
        this.nombre = nombre;
        this.codigo = codigo;

    }



}
