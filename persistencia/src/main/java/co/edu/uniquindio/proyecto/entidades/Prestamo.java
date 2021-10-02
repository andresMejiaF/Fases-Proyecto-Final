package co.edu.uniquindio.proyecto.entidades;

import org.hibernate.engine.jdbc.SerializableBlobProxy;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Prestamo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer codigo;

    private LocalDateTime fechaPrestamo;

    private LocalDate fechaDevolucion;

    public Prestamo() {
        super();
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public LocalDateTime getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDateTime fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
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
        if (!(o instanceof Prestamo)) return false;
        Prestamo prestamo = (Prestamo) o;
        return Objects.equals(getCodigo(), prestamo.getCodigo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigo());
    }
}
