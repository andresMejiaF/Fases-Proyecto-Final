package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Ciudad implements Serializable {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer codigo;

    private String nombre;


    public Ciudad(){
        super();
    }

    public Ciudad(String nombre) {
        this.nombre = nombre;

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
        if (!(o instanceof Ciudad)) return false;
        Ciudad ciudad = (Ciudad) o;
        return Objects.equals(getCodigo(), ciudad.getCodigo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigo());
    }
}
