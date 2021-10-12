package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Usuario extends Persona implements Serializable {

    @ElementCollection
    @Column(nullable = false)
    private Map <String, String> Telefono;

    @OneToMany(mappedBy = "usuarioComprador")
    private List<Chat> chats;
    @JoinColumn(nullable = false)
    @ManyToOne
    private Ciudad ciudad;

    @OneToMany(mappedBy = "usuario")
    private List<Compra> compras;

    @OneToMany(mappedBy = "usuario")
    private List<SubastaUsuario> subastaUsuarios;
    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "vendedor")
    private List<Producto> productos;

    @ManyToMany
    private List<Producto> producto;
}
