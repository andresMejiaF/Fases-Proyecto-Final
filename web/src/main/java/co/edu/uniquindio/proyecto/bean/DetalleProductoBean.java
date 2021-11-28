package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class DetalleProductoBean implements Serializable {

    @Autowired
    private ProductoServicio productoServicio;

    @Value("#{param['producto']}")
    private String codigoProducto;
    @Getter @Setter
    private Producto producto;

    @Getter @Setter
    private Comentario nuevoComentario;

    @Getter@Setter
    private List<Comentario> comentarios;

    @PostConstruct
    public void inicializar(){
        this.nuevoComentario= new Comentario();
        if(codigoProducto!=null && !codigoProducto.isEmpty()){
            producto = productoServicio.obtenerProducto(codigoProducto);
        }
    }

    public void crearComentario(){

    }


}
