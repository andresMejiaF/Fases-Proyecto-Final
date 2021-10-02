package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

@Entity
public class Persona implements Serializable {

    @Id
    private String cedula;
    private String nombre;
    private String email;
    @ElementCollection
    private Map <String, String> numTelefono;
    @Enumerated(EnumType.STRING)
    private generoPersona genero;

    public Persona(){
        super();
    }

    public Persona(String cedula,String nombre ,String email) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
    }


    public Map<String, String> getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(Map<String, String> getNumTelefono) {
        this.numTelefono = numTelefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona)) return false;
        Persona persona = (Persona) o;
        return Objects.equals(getCedula(), persona.getCedula());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCedula());
    }
}
