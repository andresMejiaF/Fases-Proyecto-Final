package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class UsuarioBean implements Serializable {

    @Getter @Setter
    private Usuario usuario;
    private final UsuarioServicio usuarioServicio;

    private final  ProductoServicio productoServicio;

    private final CiudadServicio ciudadServicio;


    @Getter @Setter
    private Ciudad ciudad;
    @Getter @Setter
    private List<Ciudad> ciudades;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;
    @Getter @Setter
    private List<Producto> productos;

    @Getter @Setter
    private List<Producto> productosComprados;


    public UsuarioBean(UsuarioServicio usuarioServicio, ProductoServicio productoServicio, CiudadServicio ciudadServicio) {
        this.usuarioServicio = usuarioServicio;
        this.productoServicio = productoServicio;
        this.ciudadServicio = ciudadServicio;
    }

    @PostConstruct
    public void inicializar(){
        usuario= new Usuario();
        ciudades= ciudadServicio.listarCiudades();
        try {
            this.productos=productoServicio.listarProductos(usuarioSesion.getCodigo());
            this.productosComprados=productoServicio.listarProductos(usuarioSesion.getCodigo());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public  void  registrarUsuario(){
        try {
            usuarioServicio.registrarUsuario(usuario);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro Exitoso!");
            FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
        }
    }

    /*
    public List<Producto> productosUsuario(){

        if (usuarioSesion!=null) {

            try {
                List<Producto> productos = productoServicio.listarProductos(usuarioSesion.getCodigo());
                return productos;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    */


}
