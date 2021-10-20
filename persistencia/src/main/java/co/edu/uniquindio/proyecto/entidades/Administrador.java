package co.edu.uniquindio.proyecto.entidades;

import lombok.*;


import javax.persistence.Entity;
import java.io.Serializable;


/**
 * Se crean los setter and getters gracias a lombok
 */
@Entity
@Getter
@Setter
//Constructor vacio
@NoArgsConstructor
//llama al toString Padre (Persona)
@ToString(callSuper = true)
public class Administrador extends Persona implements Serializable {
    //Se crea un constructor
    public Administrador(String codigo, String nombre, String email, String password) {
        super(codigo, nombre, email, password);
    }

}
