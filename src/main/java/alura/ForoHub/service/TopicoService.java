package alura.ForoHub.service;

import alura.ForoHub.entity.Curso;
import alura.ForoHub.entity.Topico;
import alura.ForoHub.entity.Usuario;
import alura.ForoHub.repository.ITopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TopicoService {

    private final ITopicoRepository iTopicoRepository;

    @Autowired
    public TopicoService(ITopicoRepository iTopicoRepository) {
        this.iTopicoRepository = iTopicoRepository;
    }

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CursoService cursoService;

    public List<Topico> findAll() {
        return iTopicoRepository.findAll();
    }

    public Topico findById(Long id) {
        return iTopicoRepository.findById(id).orElse(null);
    }

    public Topico save(Topico topico) {
        if (iTopicoRepository.findByTituloAndMensaje(topico.getTitulo(), topico.getMensaje()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tópico duplicado");
        }

        Usuario autor = usuarioService.findById(topico.getAutor().getId());
        Curso curso = cursoService.findById(topico.getCurso().getId());

        topico.setAutor(autor);
        topico.setCurso(curso);

        return iTopicoRepository.save(topico);
    }
    public void deleteById(Long id) {
        iTopicoRepository.deleteById(id);
    }

    public Topico update(Long id, Topico topico) {
        Topico topicoExistente = findById(id);
        if (topicoExistente != null) {
            topicoExistente.setTitulo(topico.getTitulo());
            topicoExistente.setMensaje(topico.getMensaje());
            return iTopicoRepository.save(topicoExistente);
        } else {
            return null;
        }
    }
}