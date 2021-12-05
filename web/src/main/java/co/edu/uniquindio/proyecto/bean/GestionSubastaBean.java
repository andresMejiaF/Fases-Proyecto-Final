package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.SubastaServicio;
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
public class GestionSubastaBean implements Serializable {

    @Value("#{param['subasta']}")
    private String codigoSubasta;

    @Getter
    @Setter
    private Subasta subasta;

    @Autowired
    private SubastaServicio subastaServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @PostConstruct
    public void inicializar(){

        if(codigoSubasta!=null && !codigoSubasta.isEmpty()){
            subasta = subastaServicio.obtenerSubasta(codigoSubasta);
            Producto producto = productoServicio.obtenerProducto(subasta.getProducto().getCodigo());
            subasta.setProducto(producto);
        }
    }

}
