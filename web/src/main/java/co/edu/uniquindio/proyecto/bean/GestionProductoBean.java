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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

    @Getter
    @Setter
    private Producto productoCopy;



    @PostConstruct
    public void inicializar(){

        if(codigoProducto!=null && !codigoProducto.isEmpty()){
            producto = productoServicio.obtenerProducto(codigoProducto);

            productoCopy= new Producto();
            productoCopy.setDescuento(0.0);
            productoCopy.setPrecio(0.0);
            productoCopy.setUnidades(0);
        }
    }

    public void eliminarProducto(){

        try {
            productoServicio.eliminarProducto(codigoProducto);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void actualizarProducto(){

        if(productoCopy.getNombre()!=null){
            producto.setNombre(productoCopy.getNombre());
        }
        if(productoCopy.getPrecio()!=0.0){
            producto.setPrecio(productoCopy.getPrecio());
        }
        if(productoCopy.getDescuento()!=0.0){
            producto.setDescuento((productoCopy.getDescuento()));
        }
        if(productoCopy.getUnidades()!= 0){
            producto.setUnidades(productoCopy.getUnidades());
        }

        if(productoCopy.getCiudad()!=null){
            producto.setCiudad(productoCopy.getCiudad());
        }
        if(productoCopy.getDescripcion() != null){
            producto.setDescripcion(productoCopy.getDescripcion());
        }

        try {
            productoServicio.actualizarProducto(producto);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Producto Actualizado");
            FacesContext.getCurrentInstance().addMessage("msj-bean2", msg);
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-bean2", fm);
            e.printStackTrace();
        }

    }
}
