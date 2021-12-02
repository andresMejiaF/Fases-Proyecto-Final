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

@Component
@ViewScoped
public class GestionProductoBean implements Serializable {

    @Value("#{param['producto']}")
    private String codigoProducto;

    @Autowired
    private ProductoServicio productoServicio;

    @Getter
    @Setter
    private Producto producto;

    @PostConstruct
    public void inicializar(){

        if(codigoProducto!=null && !codigoProducto.isEmpty()){
            producto = productoServicio.obtenerProducto(codigoProducto);
        }
    }

    public void eliminarProducto(){

        try {
            productoServicio.eliminarProducto(codigoProducto);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
