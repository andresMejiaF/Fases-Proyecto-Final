package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.MailService;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
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
import javax.persistence.Column;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private MailService mailService;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;
    @Getter @Setter
    private List<Producto> productos;

    @Getter @Setter
    private List<Producto> productosComprados;
    @Getter @Setter
    private String telefono;
    private ArrayList<String> telefonos;

    @Getter@Setter
    private List<Producto> favoritos;
    @Getter@Setter
    private List<Producto> productosFavoritos;


    public UsuarioBean(UsuarioServicio usuarioServicio, ProductoServicio productoServicio, CiudadServicio ciudadServicio) {
        this.usuarioServicio = usuarioServicio;
        this.productoServicio = productoServicio;
        this.ciudadServicio = ciudadServicio;
    }

    @PostConstruct
    public void inicializar(){
        usuario= new Usuario();
        ciudades= ciudadServicio.listarCiudades();
        telefonos= new ArrayList<>();

        if(usuarioSesion!=null) {
            favoritos = productoServicio.productoFavorito(usuarioSesion.getCodigo());
            try {
                this.productos=productoServicio.listarProductos(usuarioSesion.getCodigo());
                this.productosComprados=productoServicio.listarProductoComprado(usuarioSesion.getCodigo());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public  void  registrarUsuario(){
            telefonos.add(telefono);
        try {
            usuario.setTelefonos(telefonos);
            usuarioServicio.registrarUsuario(usuario);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro Exitoso!");
            FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
        }
    }



    public void sendMailRespuesta(String respuesta,String email){

        String subject = "En hora buena, alguien respondio tu comentario";
        String message = respuesta;

        mailService.sendMail("pruebayespacio@gmail.com", email,subject,message);

    }

   public void agregarFavoritos(String codigo){


        favoritos.add(productoServicio.obtenerProducto(codigo));



        usuarioSesion.setProductosFavoritos(favoritos);

       try {
           usuarioServicio.actualizarUsuario(usuarioSesion);
       } catch (Exception e) {
           e.printStackTrace();
       }

   }

   public void eliminarDeFavoritos(String codigo){

        favoritos.remove(productoServicio.obtenerProducto(codigo));

        usuarioSesion.setProductosFavoritos(favoritos);

       try {
           usuarioServicio.actualizarUsuario(usuarioSesion);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
}
