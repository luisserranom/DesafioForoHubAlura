package alura.ForoHub.service;

import alura.ForoHub.entity.Curso;
import alura.ForoHub.repository.ICursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    private final ICursoRepository iCursoRepository;

    @Autowired
    public CursoService(ICursoRepository cursoRepository) {
        this.iCursoRepository = cursoRepository;
    }

    public List<Curso> findAll() {
        return iCursoRepository.findAll();
    }

    public Curso findById(Long id) {
        return iCursoRepository.findById(id).orElse(null);
    }

    public Curso save(Curso curso) {
        return iCursoRepository.save(curso);
    }

    public void deleteById(Long id) {
        iCursoRepository.deleteById(id);
    }

    public Curso update(Long id, Curso curso) {
        Curso cursoExistente = iCursoRepository.findById(id).orElse(null);
        if (cursoExistente != null) {
            cursoExistente.setNombre(curso.getNombre());
            return iCursoRepository.save(cursoExistente);
        } else {
            return null;
        }
    }

}
