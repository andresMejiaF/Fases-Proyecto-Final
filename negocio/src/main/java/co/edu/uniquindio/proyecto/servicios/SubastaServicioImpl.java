package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.excepciones.SubastaNoEncontradaExcepcion;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubastaServicioImpl implements SubastaServicio {

    @Autowired
    private SubastaRepo subastaRepo;

    @Override
    public Subasta publicarSubasta(Subasta subasta) throws Exception {
        try {
            return subastaRepo.save(subasta);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Subasta> listarSubastas(String codigoUsuario) throws Exception {
        try {
            return subastaRepo.listarPorCodUsuario(codigoUsuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Subasta obtenerSubasta(String codigoSubasta) throws SubastaNoEncontradaExcepcion {
        return subastaRepo.findById(codigoSubasta).orElseThrow(() -> new SubastaNoEncontradaExcepcion("El codigo de la subasta no es valido"));
    }

    @Override
    public List<Subasta> listarSubastasVigentes() {
        return subastaRepo.listarSubastasDisponibles();
    }
}
