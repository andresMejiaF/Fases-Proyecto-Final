package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
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
import java.util.ArrayList;

@Component
@ViewScoped
public class GestionUsuarioBean implements Serializable {

    @Value("#{param['usuario']}")
    private String codigoUsuario;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Getter @Setter
    private Usuario usuario;
    @Getter@Setter
    private Usuario usuarioCopy;
    @Getter @Setter
    private String telefono;


    @PostConstruct
    public void inicializar(){

        if(codigoUsuario!=null && !codigoUsuario.isEmpty()){
            try {
                usuario=usuarioServicio.obtenerUsuario(codigoUsuario);
            } catch (Exception e) {
                e.printStackTrace();
            }

            usuarioCopy= new Usuario();

        }
    }

    public void eliminarUsuario(){
        try {
            usuarioServicio.eliminarUsuario(codigoUsuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarUsuario(){

        ArrayList<String> telefonos=new ArrayList<>();

        telefonos.add(telefono);
        usuarioCopy.setTelefonos(telefonos);

        if(usuarioCopy.getNombre()!=null){
            usuario.setNombre(usuarioCopy.getNombre());
        }
        if(usuarioCopy.getEmail()!=null){
            usuario.setEmail(usuarioCopy.getEmail());
        }
        if(usuarioCopy.getUsername()!=null){
            usuario.setUsername(usuarioCopy.getUsername());
        }
        if(usuarioCopy.getCiudad()!= null){
            usuario.setCiudad(usuarioCopy.getCiudad());
        }
        if(usuarioCopy.getTelefonos()!=null){
            usuario.setTelefonos(usuarioCopy.getTelefonos());
        }
        try {
            usuarioServicio.actualizarUsuario(usuario);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "usuario Actualizado");
            FacesContext.getCurrentInstance().addMessage("msj-bean3", msg);
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-bean3", fm);
            e.printStackTrace();
        }



    }


}
