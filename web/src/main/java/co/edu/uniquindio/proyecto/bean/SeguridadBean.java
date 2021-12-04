package co.edu.uniquindio.proyecto.bean;


import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.MailService;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
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
import java.util.List;

@Scope("session")
@Component
public class SeguridadBean implements Serializable {

    @Getter@Setter
    private boolean autenticado;

    @Getter@Setter
    private boolean autenticadoAdmin;

    @Getter@Setter
    private String email, password;

    @Getter@Setter
    private Usuario usuarioSesion;

    private String respuesta;

    @Getter @Setter
    private Usuario usuarioAux;

    @Autowired
    private MailService mailService;

    @Getter @Setter
    @NotBlank
    private String emailR;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Getter @Setter
    private ArrayList<ProductoCarrito> productosCarrito;
    @Getter @Setter
    private double subTotal;

    @Autowired
    private ProductoServicio productoServicio;
    @Getter @Setter
    private List<Producto> productos;
    @Getter@Setter
    private ArrayList<String> mediosPago;
    @Getter @Setter
    private String medio;

    private boolean agregar;

    @Getter @Setter
    private Producto productAuxiliar;
    @Getter@Setter
    private String telefono;

    @PostConstruct
    public  void inicializar(){
        this.subTotal= 0F;
        this.productosCarrito= new ArrayList<>();

        mediosPago= new ArrayList<>();
        mediosPago.add("PSE");
        mediosPago.add("Tarjeta credito");
        mediosPago.add("Tarjeta debito");
        mediosPago.add("Pago contraentrega");

    }

    public String inciarSesion(){
        if(!email.isEmpty() && !password.isEmpty()){
            try {
                if(email.equals("pruebayespacio@gmail.com") && password.equals("admin")){
                    autenticadoAdmin=true;
                }
                usuarioSesion= usuarioServicio.iniciarSesion(email, password);
                telefono=usuarioSesion.getTelefonos().get(0);
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

    public void comprar()
    {
        Integer unidades;
        agregar=true;

        for(int i=0; i<productosCarrito.size() ; i++){
            if(productosCarrito.get(i).getUnidades()>productoServicio.obtenerProducto(productosCarrito.get(i).getCodigo()).getUnidades()){
                FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Las unidades que trara de agregar para el producto: "
                        +productosCarrito.get(i).getNombre()+" son mayores a las disponibles");
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
                agregar=false;
            }
        }

        if(usuarioSesion!=null && !productosCarrito.isEmpty() && agregar!=false) {
            try {
                productoServicio.comprarProductos(usuarioSesion, productosCarrito, medio);
                correoCompra(productosCarrito, subTotal);
                productosCarrito.clear();
                subTotal=0F;
                correoCompra(productosCarrito, subTotal);
                FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Compra realizada correctamente");

                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);

            } catch (Exception e) {
                FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
            }
        }else {
            FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Verifica los datos");
            FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
        }
    }

    /**
     * Método para buscar el correo
     * @return
     */
    public String buscarPorEmail(){

        try {
            usuarioAux = usuarioServicio.obtenerPersonaEmail(emailR);

            if(usuarioAux!=null){

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
     * Método para cambiar clave
     */
    public void cambiarPassword(){

        try {
            if (!password.isEmpty()){

                usuarioServicio.cambiarPassword(email,password);

                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "La contraseña se actualizo con exito");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

            }else{
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "No se pudo actualizar la contraseña");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }

        }catch (Exception e){
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }

    }

    /**
     * Método para enviar el correo de recuperación
     */
    public void sendMail()
    {
        String subject = "Recuperacion de contraseña";
        String url = "http://localhost:8080/recuperarContrasena.xhtml";
        String message = "Un saludo por parte de unishop!" + "\n" + " Para recuperar su contraseña, de click en el siguiente enlace: " + "\n" + url;

        mailService.sendMail("pruebayespacio@gmail.com", usuarioAux.getEmail(),subject,message);
    }

    /**
     * Metodo para enviar correo con los detalles de la compra
     */
    public void correoCompra(ArrayList<ProductoCarrito> productosCarrito, double subTotal)
    {

        respuesta="";
        for(ProductoCarrito p: productosCarrito) {
            respuesta +="\n"+ "Producto: "+p.getNombre() + "\n" + "Precio: " + p.getPrecio() + "\n" + "Unidades: " + p.getUnidades() + "\n" +"-----------------" ;
        }
        String subject = "Detalle de la compra";
        String message = "Un saludo por parte de unishop!" + "\n" + "Su compra se a realizado con exito, estos son los detalles de su compra: "
                +"\n" + respuesta + "\n" + "todo con un total a pagar de: "+ subTotal;

        mailService.sendMail("pruebayespacio@gmail.com", usuarioSesion.getEmail(),subject,message);
    }
}
