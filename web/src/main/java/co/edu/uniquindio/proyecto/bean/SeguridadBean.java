package co.edu.uniquindio.proyecto.bean;


import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@Scope("session")
@Component
public class SeguridadBean implements Serializable {

    @Getter@Setter
    private boolean autenticado;

    @Getter@Setter
    private String email, password;
    @Getter@Setter
    private Usuario usuarioSesion;

    @Autowired
    private UsuarioServicio usuarioServicio;
    public String inciarSesion(){
        if(!email.isEmpty() && !password.isEmpty()){
            try {

                usuarioSesion= usuarioServicio.iniciarSesion(email, password);
                autenticado=true;
                return "/index?faces-redirect=true";
            } catch (Exception e) {
                FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("login-bean", fm);
            }
        }
        return null;
    }

    public String cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return"/index?faces-redirect=true";
    }
}
