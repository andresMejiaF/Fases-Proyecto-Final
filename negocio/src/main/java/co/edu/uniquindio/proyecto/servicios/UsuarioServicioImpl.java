package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.proyecciones.UsuarioBase;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
 public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public Usuario registrarUsuario(Usuario u) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(u.getCodigo());
        if(buscado.isPresent()){
            throw new Exception("El codigo del usuario ya existe");
        }
        buscado = buscarPorEmail(u.getEmail());
        if(buscado.isPresent()){
            throw new Exception("El Correo del usuario ya existe");
        }

        buscado = usuarioRepo.findByUsername(u.getUsername());
        if(buscado.isPresent()){
            throw new Exception("El Username del usuario ya existe");
        }

        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        u.setPassword(passwordEncryptor.encryptPassword( u.getPassword()));

        return usuarioRepo.save(u);
    }

    @Override
    public Usuario actualizarUsuario(Usuario u) throws Exception {

        Optional<Usuario> buscado = usuarioRepo.findById(u.getCodigo());
        if(buscado.isEmpty()){
            throw new Exception("El usuario no existe");
        }

        return usuarioRepo.save(u);
    }



    private Optional<Usuario> buscarPorEmail(String email){
        return usuarioRepo.findByEmail(email);
    }
    @Override
    public void eliminarUsuario(String codigo) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(codigo);
        if(buscado.isEmpty()){
            throw new Exception("El codigo del usuario NO existe");
        }
        usuarioRepo.deleteById(codigo);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }



    @Override
    public List<Producto> listarFavoritos(String email)throws Exception {

        Optional<Usuario> buscado = buscarPorEmail( email);
        if(buscado.isEmpty()){
            throw  new Exception("El correo no existe :(");
        }
        return usuarioRepo.obtenerProductoFavoritos(email);
    }

    @Override
    public Usuario obtenerUsuario(String codigo) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(codigo);
        if(buscado.isEmpty()){
            throw new Exception("El usuario NO existe :(");
        }
        return buscado.get();
    }

    @Override
    public Usuario iniciarSesion(String email, String password) throws Exception {

       // return usuarioRepo.findByEmailAndPassword(email, password).orElseThrow(() -> new Exception("Los datos de autenticacion son incorrectos"));
        Usuario usuarioEmail = usuarioRepo.findByEmail(email).orElseThrow(() -> new Exception("Los datos de autenticacion son incorrectos"));
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        if(passwordEncryptor.checkPassword(password, usuarioEmail.getPassword())){
            return usuarioEmail;
        }else {
            throw new Exception("La contrase??a es incorrecta");
        }
    }

    @Override
    public Usuario obtenerPersonaEmail(String email) throws Exception {

        Optional<Usuario> personaEncontrada = usuarioRepo.findByEmail(email);

        if(personaEncontrada.isEmpty()){

            throw new Exception("No existe un usuario con el correo dado");
        }
        return personaEncontrada.get();
    }


    @Override
    public void cambiarPassword(String email,String passwordN) throws Exception {

        Usuario personaEncontrada = obtenerPersonaEmail(email);

        if (personaEncontrada!=null){
            //personaEncontrada.setPassword(passwordN);

            StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
            personaEncontrada.setPassword(passwordEncryptor.encryptPassword(passwordN));
            usuarioRepo.save(personaEncontrada);
        }else{
            throw new Exception("No existe una persona con el correo dado");
        }

    }

    @Override
    public Usuario obtenerUsuarioAdmin(String correo) {
        return usuarioRepo.obtenerUsuarioAdmin(correo);
    }

}
