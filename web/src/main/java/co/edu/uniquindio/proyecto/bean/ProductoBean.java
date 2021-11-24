package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalDate;

@Component
@ViewScoped
public class ProductoBean implements Serializable {
    @Getter@Setter
    private Producto producto;

    @Autowired
    private ProductoServicio productoServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostConstruct
    public void inicializar(){
        this.producto= new Producto();
    }

    public  String crearProducto(){

        try {
            //Se quema el vendedor al producto por lo de la foranea
           Usuario usuario= usuarioServicio.obtenerUsuario("94285");
           producto.setVendedor(usuario);
            productoServicio.publicarProducto(producto);
            return "productoCreado?faces-redirect=true";
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);

        }
        return null;
    }
}
