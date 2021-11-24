package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//Especificacion de tablas en la Herencia
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
@AllArgsConstructor
@ToString
/**
 * Se crea la clase padre donde dependen otras clases como usuario
 * o Administrador, todas implementan Serializable para poder
 * ser manipulados pos Mysql
 */
public class Persona implements Serializable {
    @Id
    @Column(length = 30)
    @EqualsAndHashCode.Include
    private String codigo;
    @Column(nullable = false, length = 150)
    @Length(max= 150)
    private String nombre;
    @Column(nullable = false, length = 150, unique = true)
    @Email(message = "Escribe un email valido")
    private String email;
    @Column(nullable = false, length = 100)
    private String password;


}
