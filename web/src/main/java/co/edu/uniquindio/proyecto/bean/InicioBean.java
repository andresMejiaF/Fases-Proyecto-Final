package co.edu.uniquindio.proyecto.bean;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class InicioBean implements Serializable {

    @Getter
    private String mensaje= "Pagina inicial JSF";

}
