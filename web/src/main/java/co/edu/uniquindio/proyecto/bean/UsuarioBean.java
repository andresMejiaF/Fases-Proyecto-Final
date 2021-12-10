package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
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

    private SubastaServicio subastaServicio;

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

    @Getter @Setter
    private List<Subasta> subastas;
    private BarChartModel barProductos;
    private PieChartModel pieListaFav;
    private PieChartModel pieCiudadProductos;


    public UsuarioBean(UsuarioServicio usuarioServicio, ProductoServicio productoServicio, CiudadServicio ciudadServicio,
                       SubastaServicio subastaServicio) {
        this.usuarioServicio = usuarioServicio;
        this.productoServicio = productoServicio;
        this.ciudadServicio = ciudadServicio;
        this.subastaServicio = subastaServicio;
    }

    @PostConstruct
    public void inicializar() {
        usuario = new Usuario();
        ciudades = ciudadServicio.listarCiudades();
        telefonos = new ArrayList<>();
      //  graficarProductos();
      //  CrearCiudadProductos();

        if(usuarioSesion!=null) {
            favoritos = productoServicio.productoFavorito(usuarioSesion.getCodigo());
            try {
                this.productos=productoServicio.listarProductos(usuarioSesion.getCodigo());
                this.productosComprados=productoServicio.listarProductoComprado(usuarioSesion.getCodigo());
                this.subastas=subastaServicio.listarSubastas(usuarioSesion.getCodigo());
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
       FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Producto agregado a favoritos");
       FacesContext.getCurrentInstance().addMessage("msj-favoritos", msg);

   }

   public void eliminarDeFavoritos(String codigo){

        favoritos.remove(productoServicio.obtenerProducto(codigo));

        usuarioSesion.setProductosFavoritos(favoritos);

       try {
           usuarioServicio.actualizarUsuario(usuarioSesion);
       } catch (Exception e) {
           e.printStackTrace();
       }

       FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Producto eliminado de tus favoritos");
       FacesContext.getCurrentInstance().addMessage("msj-favoritos", msg);
   }



    public void CrearCiudadProductos(){

        pieCiudadProductos  = new PieChartModel();

        List<Ciudad>ciudades = ciudadServicio.listarCiudades();

        for(Ciudad c:ciudades){

            for (Ciudad ciudad1 : (List<Ciudad>) productoServicio.obtenerProducto(c.getNombre())) {

            }


            pieCiudadProductos.set(c.getNombre(),ciudades.size());
        }

        pieCiudadProductos.setTitle("nombre ciudades del producto");
        pieCiudadProductos.setLegendPosition("e");
        pieCiudadProductos.setFill(true);
        pieCiudadProductos.setShowDataLabels(true);
        pieCiudadProductos.setDiameter(200);

    }

    public BarChartOptions opcionesBarras(String titulo, int min, int max){
        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        ticks.setMin(min);
        ticks.setMax(max);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText(titulo);
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        return options;
    }

    public void graficarProductos(){

        barProductos = new BarChartModel();
        List<Number>valores = new ArrayList<>();
        List<String>labels = new ArrayList<>();
        List<String> bgColor = new ArrayList<>();
        BarChartDataSet barDataSet = new BarChartDataSet();
        ChartData data = new ChartData();
        barDataSet.setLabel("Producto");

        BarChartOptions opciones = opcionesBarras("Cantidad de lugares por ciudad",0,10);

        for(Producto p:productos){

            List<Producto>unidades = (List<Producto>) productoServicio.obtenerProducto(p.getCodigo());
            valores.add(productos.size());
            labels.add(p.getCodigo());
            bgColor.add("rgba(54, 162, 235, 0.2)");
        }

        barDataSet.setBackgroundColor(bgColor);
        barDataSet.setData(valores);
        data.addChartDataSet(barDataSet);
        data.setLabels(labels);
        barProductos.setData(data);

        barProductos.setOptions(opciones);
    }

   /* public void graficarCiudadP(){

        barCiudadProductos = new BarChartModel();
        List<Number>valores = new ArrayList<>();
        List<String>labels = new ArrayList<>();
        List<String> bgColor = new ArrayList<>();
        BarChartDataSet barDataSet = new BarChartDataSet();
        ChartData data = new ChartData();
        barDataSet.setLabel("Ciudades");

        BarChartOptions opciones = opcionesBarras("Ciudadaes de los productos",0,5);

        List<Ciudad>Ciudades= ciudadServicio.listarCiudades();

        for(Ciudad c:ciudades){

            try{
                valores.add(ciudadServicio.listarCiudades(c.getNombre()));
            }catch (Exception e){
                e.printStackTrace();
            }

            labels.add(c.getNombre());
            bgColor.add("rgba(54, 162, 235, 0.2)");
        }

        barDataSet.setBackgroundColor(bgColor);
        barDataSet.setData(valores);
        data.addChartDataSet(barDataSet);
        data.setLabels(labels);
        barCiudadProductos.setData(data);

        barCiudadProductos.setOptions(opciones);
        */
    }

