package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.SubastaServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

@Component
@ViewScoped
public class SubastaBean implements Serializable {

    @Getter@Setter
    private Subasta subasta;
    @Getter@Setter
    private Producto producto;
    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;
    @Value("#{param['producto']}")
    private String codigoProducto;
    @Autowired
    private ProductoServicio productoServicio;
    @Autowired
    private SubastaServicio subastaServicio;

    private ArrayList<String> imagenes;

    @PostConstruct
    public void inicializar() {
        this.subasta = new Subasta();
        this.imagenes = new ArrayList<>();
        this.producto = new Producto();
    }

    public void crearSubasta() {
        try {
            if(usuarioSesion != null) {
                producto = productoServicio.obtenerProducto(codigoProducto);
                Integer codigo = ((int) Math.floor(Math.random()*10000+1));
                subasta.setCodigo(codigo.toString());
                subasta.setProducto(producto);
                subasta.setFechaSubasta(calcularFechaSubasta());
                subasta.setVendedor(usuarioSesion);
                subastaServicio.publicarSubasta(subasta);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Producto en subasta!");
                FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
            }
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-bean", fm);

        }
    }

    private LocalDateTime calcularFechaSubasta() {
        LocalDateTime fechaActual = LocalDateTime.now();
        fechaActual = fechaActual.plusHours(Long.valueOf(subasta.getTiempoLimite()));
        return fechaActual;
    }


}
