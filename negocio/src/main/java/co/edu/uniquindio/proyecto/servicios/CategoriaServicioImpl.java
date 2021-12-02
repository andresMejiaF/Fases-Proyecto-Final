package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServicioImpl implements CategoriaServicio{

    @Autowired
    private CategoriaRepo categoriaRepo;


    @Override
    public List<Categoria> listCategorias() {
        return categoriaRepo.findAll();
    }

    @Override
    public void registrarCategoria(Categoria categoria) {

        categoriaRepo.save(categoria);
    }
}
