package co.edu.uniquindio.proyecto.dto;


import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class ProductoCarrito {

    @EqualsAndHashCode.Include
    private String codigo;
    private String nombre, imagen;
    private double precio;
    private Integer unidades;

}
