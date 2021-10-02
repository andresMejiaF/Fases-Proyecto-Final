package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Autor implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer codigo;

    private String nombre;
    private String anioNacimiento;

    public Autor(){
        super();
    }

    public Autor(String nombre, String anioNacimiento) {
        this.nombre = nombre;
        this.anioNacimiento=anioNacimiento;
    }

    public String getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(String anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Autor)) return false;
        Autor autor = (Autor) o;
        return Objects.equals(getCodigo(), autor.getCodigo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigo());
    }
}
