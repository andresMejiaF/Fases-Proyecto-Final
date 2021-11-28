package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
    @NotBlank
    @Length(min = 2, max = 150, message = "El nombre debe tener minimo 2 y maximo 150 caracteres")
    private String nombre;
    @Column(nullable = false, length = 150, unique = true)
    @Email(message = "Escribe un email valido")
    @NotBlank
    private String email;
    @Column(nullable = false, length = 100)
    @Length(max = 100, message = "La contraseña debe tener maximo 100 caracteres")
    @NotBlank
    private String password;


}
