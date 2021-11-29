package co.edu.uniquindio.proyecto.bean;


import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;

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

    @Getter @Setter
    private ArrayList<ProductoCarrito> productosCarrito;
    @Getter @Setter
    private double subTotal;

    @PostConstruct
    public  void inicializar(){
        this.subTotal= 0F;
        this.productosCarrito= new ArrayList<>();
    }

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

    public void agregarAlCarrito(String codigo, double precio, String nombre, String imagen){
        ProductoCarrito pc= new ProductoCarrito(codigo, nombre, imagen, precio, 1);
        if(!productosCarrito.contains(pc)){
            productosCarrito.add(pc);
            subTotal+=precio;
            FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Producto agregado al carrito");
            FacesContext.getCurrentInstance().addMessage("add-cart", fm);
        }else {
            FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Ya tienes el producto agregado");
            FacesContext.getCurrentInstance().addMessage("add-cart", fm);
        }

    }

    public void eliminarDelCarrito(int indice){
        subTotal -= productosCarrito.get(indice).getPrecio();
        productosCarrito.remove(indice);
    }

    public void actualizarSubTotal(){
        subTotal = 0F;
        for(ProductoCarrito p: productosCarrito){
            subTotal += p.getPrecio()* p.getUnidades();
        }
    }

    public void comprar(){

    }
}
