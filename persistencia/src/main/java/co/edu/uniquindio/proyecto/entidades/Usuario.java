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
    private Map <String, String> numTelefono;

    @OneToMany(mappedBy = "usuarioComprador")
    private List<Chat> chats;

    @ManyToOne
    private Ciudad codigoCiudad;

    @OneToMany(mappedBy = "codigoUsuario")
    private List<Compra> compras;

    @OneToMany(mappedBy = "codigoUsuario")
    private List<SubastaUsuario> subastaUsuarios;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "codigoVendedor")
    private List<Producto> productos;

    @ManyToMany(mappedBy = "codigoUsuario")
    private List<Producto> codigoProducto;
}
