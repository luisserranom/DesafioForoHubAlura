package alura.ForoHub.service;

import alura.ForoHub.entity.Usuario;
import alura.ForoHub.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final IUsuarioRepository iUsuarioRepository;

    @Autowired
    public UsuarioService(IUsuarioRepository usuarioRepository) {
        this.iUsuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAll() {
        return iUsuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return iUsuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario) {
        return iUsuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        iUsuarioRepository.deleteById(id);
    }

    public Usuario update(Long id, Usuario usuario) {
        Usuario usuarioExistente = findById(id);
        if (usuarioExistente != null) {
            usuarioExistente.setNombre(usuario.getNombre());
            usuarioExistente.setEmail(usuario.getEmail());
            return iUsuarioRepository.save(usuarioExistente);
        } else {
            return null;
        }
    }
}
