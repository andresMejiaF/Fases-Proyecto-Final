package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Mensaje implements Serializable {
    @Id
    @Column(nullable = false,length = 10)
    @EqualsAndHashCode.Include
    private String codigo;
    @Column(nullable = false)
    private String mensaje;
    @Column(nullable = false, length = 20)
    private String emisor;
    @Column(nullable = false)
    private LocalDate fecha;
    @JoinColumn(nullable = false)
    @ManyToOne
    private Chat chat;

}