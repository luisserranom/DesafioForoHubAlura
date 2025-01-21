package alura.ForoHub.controller;

import alura.ForoHub.entity.Curso;
import alura.ForoHub.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    @Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<Curso> findAll() {
        return cursoService.findAll();
    }

    @GetMapping("/{id}")
    public Curso findById(@PathVariable Long id) {
        return cursoService.findById(id);
    }

    @PostMapping
    public Curso save(@RequestBody Curso curso) {
        return cursoService.save(curso);
    }

    @PutMapping("/{id}")
    public Curso update(@PathVariable Long id, @RequestBody Curso curso) {
        return cursoService.update(id, curso);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        cursoService.deleteById(id);
    }
}