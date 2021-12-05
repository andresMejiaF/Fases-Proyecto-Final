package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.SubastaServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class InicioBean implements Serializable {

    @Autowired
    private ProductoServicio productoServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Getter @Setter
    private List<Producto> productos;
    @Getter@Setter
    private List<Usuario> usuarios;
    @Getter @Setter
    private List<Subasta> subastas;
    @Autowired
    private SubastaServicio subastaServicio;

    @PostConstruct
    public void inicializar() {
        this.productos = productoServicio.listarTodosLosProductos();
        this.usuarios= usuarioServicio.listarUsuarios();
        this.subastas = subastaServicio.listarSubastasVigentes();
    }

    public String irADetalle(String id){
        return "detalle_producto?faces-redirect=true&amp;producto="+id;
    }

    public  String irAGestion(String id){

        return "gestionar_producto?faces-redirect=true&amp;producto="+id;
    }

    public  String irAInspeccionar(String codigo){

        return "inspeccionar_u?faces-redirect=true&amp;usuario="+codigo;
    }

    public String irASubasta(String id) {
        return "subastar_producto?faces-redirect=true&amp;producto="+id;
    }

    public String irAGestionSubasta(String id) { return "gestionar_subasta?faces-redirect=true&amp;subasta="+id;}

    public String irADetalleSubasta(String id) { return "detalle_subasta?faces-redirect=true&amp;producto="+id; }


}
