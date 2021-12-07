package co.edu.uniquindio.proyecto.rest;


import co.edu.uniquindio.proyecto.dto.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/productos")
public class ProductoRestController {
    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping
    public List<Producto> listar(){
        return productoServicio.listarTodosLosProductos();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> obtener(@PathVariable("codigo") String codigoProducto){
        try {
            Producto producto= productoServicio.obtenerProducto(codigoProducto);
            return ResponseEntity.status(200).body(producto);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }


    @PostMapping
    public ResponseEntity<Mensaje> registrarProducto(@RequestBody Producto producto){
        try {
            productoServicio.publicarProducto(producto);
            return ResponseEntity.status(201).body(new Mensaje("El producto ha sido publicado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PutMapping
    public  ResponseEntity<Mensaje> actualizarProducto(@RequestBody Producto producto){
        try {
            productoServicio.actualizarProducto(producto);
            return ResponseEntity.status(200).body(new Mensaje("El producto ha sido actualizado"));

        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));

        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Mensaje> eliminarProducto(@PathVariable("codigo") String codigo){
        try {
            productoServicio.eliminarProducto(codigo);
            return ResponseEntity.status(200).body(new Mensaje("El producto ha sido eliminado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));

        }
    }

    @GetMapping("/lugarCategoria/{cadena}")
    public List<Producto> buscar(@PathVariable("cadena") String cadena){
            return productoServicio.buscarProductoVarios(cadena);
    }

    @GetMapping("/rango/{valor1}/{valor2}")
    public List<Producto> buscar(@PathVariable("valor1") double valor1,@PathVariable("valor2") double valor2 ){
        return productoServicio.productosPorRango(valor1, valor2);
    }

    @GetMapping("/unidades/{unidades}")
    public List<Producto> buscar(@PathVariable("unidades") int unidades){
        return productoServicio.productoPorUnidades(unidades);
    }

    @GetMapping("/vendedor/{codigo}")
    public ResponseEntity<?> buscarP(@PathVariable("codigo") String codigo){

        try {
            List<Producto> productos= productoServicio.productoPropietario(codigo);
            return ResponseEntity.status(200).body(productos);
            
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }

    }




}
