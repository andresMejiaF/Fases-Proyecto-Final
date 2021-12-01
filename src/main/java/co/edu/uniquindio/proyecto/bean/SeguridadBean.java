package co.edu.uniquindio.proyecto.bean;


import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.entidades.Persona;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import co.edu.uniquindio.proyecto.servicios.MailService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;

@Scope("session")
@Component
public class SeguridadBean implements Serializable
{
    @Getter@Setter
    private boolean autenticado;
    @Getter @Setter
    private Persona persona;
    @Getter @Setter
    private Persona personaAux;
    @Getter@Setter
    @NotBlank
    private String email, emailR, password;
    @Getter@Setter
    private Usuario usuarioSesion;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Getter @Setter
    private ArrayList<ProductoCarrito> productosCarrito;
    @Getter @Setter
    private double subTotal;

    @Autowired
    private ProductoServicio productoServicio;
    @Autowired
    private MailService mailService;

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

    /**
     * Metodo para realizar la compra
     */
    public void comprar()
    {
        if(usuarioSesion!=null && !productosCarrito.isEmpty())
        {
            try
            {
                productoServicio.comprarProductos(usuarioSesion, productosCarrito, "PSE");
                productosCarrito.clear();
                subTotal=0F;
                FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Compra realizada corrctamente");
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
            } catch (Exception e) {
                FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
            }
        }
    }

    /**
     * Metodo para buscar por correo
     * @return
     */
    public String buscarPorEmail()
    {
        try {
            personaAux = usuarioServicio.obtenerUsuarioEmail(emailR);

            if(personaAux!=null){

                sendMail();
            }else{

                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "El email que ingreso no se encuentra registrado");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }

            return "/index?faces-redirect=true";

        }catch (Exception e){

            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
        return null;
    }

    /**
     * Metodo para cambiar el password
     */
    public void cambiarPassword()
    {
        try {
            if (!password.isEmpty()){

                usuarioServicio.cambiarPassword(email,password);

                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! la contraseÃ±a se actualizo con exito");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

            }else{
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Â¡Lo sentimos! no pudimos actualizar tu contraseÃ±a");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }

        }catch (Exception e){
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
    }

    /**
     * Metodo para actualizar la contra
     */
    public void sendMail()
    {
        String subject = "Recuperacion de password";
        String url = "http://localhost:8080/recuperarContrasena.xhtml";
        String message = "Para recuperar su password, de click en el siguiente enlace" + "\n"+ url;
        mailService.sendMail("unilocal0804@gmail.com", personaAux.getEmail(),subject,message);
    }
}

