package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
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
import java.util.List;

@Component
@ViewScoped
public class UsuarioBean implements Serializable {

    @Getter @Setter
    private Usuario usuario;
    private final UsuarioServicio usuarioServicio;

    private final CiudadServicio ciudadServicio;
    @Getter @Setter
    private Ciudad ciudad;
    @Getter @Setter
    private List<Ciudad> ciudades;

    public UsuarioBean(UsuarioServicio usuarioServicio, CiudadServicio ciudadServicio) {
        this.usuarioServicio = usuarioServicio;
        this.ciudadServicio = ciudadServicio;
    }

    @PostConstruct
    public void inicializar(){
        usuario= new Usuario();
        ciudades= ciudadServicio.listarCiudades();
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

}
