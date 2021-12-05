package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
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
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class DetalleProductoBean implements Serializable {

    @Autowired
    private ProductoServicio productoServicio;

    @Value("#{param['producto']}")
    private String codigoProducto;
    @Getter @Setter
    private Producto producto;

    @Getter @Setter
    private Comentario nuevoComentario;

    @Getter@Setter
    private List<Comentario> comentarios;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @Getter@Setter
    private Integer calificacionPromedio;

    @PostConstruct
    public void inicializar(){
        nuevoComentario= new Comentario();
        if(codigoProducto!=null && !codigoProducto.isEmpty()){
            producto = productoServicio.obtenerProducto(codigoProducto);
            this.comentarios= producto.getComentarios();
            this.calificacionPromedio= Math.round(productoServicio.obtenerPromedioCalificacion(codigoProducto));
        }
    }

    public void crearComentario(){

        try {
            if(usuarioSesion != null) {
                nuevoComentario.setProducto(producto);
                nuevoComentario.setUsuario(usuarioSesion);
                productoServicio.comentarProducto(nuevoComentario);
                this.comentarios.add(nuevoComentario);
                nuevoComentario = new Comentario();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para eliminar un comentario feo
     */
    public void eliminarComentario(Comentario comentario, Usuario usuario)
    {

        if( usuarioSesion!=null && usuario.equals(usuarioSesion)) {
            productoServicio.eliminarComentario(comentario);
            FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El comentario se a eliminado con exito");
            FacesContext.getCurrentInstance().addMessage("msj-comentario", fm);
        }else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Usted no es el usuario que hizo este comentario");
            FacesContext.getCurrentInstance().addMessage("msj-comentario", fm);
        }


    }

    public void crearOferta() {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Oferta realizada!");
        FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
    }

}
