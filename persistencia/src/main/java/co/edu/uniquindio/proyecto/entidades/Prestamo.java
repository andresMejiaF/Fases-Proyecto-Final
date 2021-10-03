package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import org.hibernate.engine.jdbc.SerializableBlobProxy;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Prestamo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    private LocalDateTime fechaPrestamo;

    private LocalDate fechaDevolucion;



}
