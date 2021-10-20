package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Chat implements Serializable {

    @Id
    @Column(nullable = false,length = 10)
    @EqualsAndHashCode.Include
    private String codigo;

    /**
     * Se crean relaciones (uno a muchos y muchos a uno)
     * también se crea un constructor
     */
    @OneToMany(mappedBy = "chat")
    @ToString.Exclude
    private List<Mensaje> mensajes;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Usuario usuarioComprador;

    public Chat(String codigo, Usuario usuarioComprador) {
        this.codigo = codigo;
        this.usuarioComprador=usuarioComprador;
    }
}
