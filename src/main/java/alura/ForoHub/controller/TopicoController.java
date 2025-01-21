package alura.ForoHub.controller;



import alura.ForoHub.entity.Topico;
import alura.ForoHub.repository.ITopicoRepository;
import alura.ForoHub.service.TopicoService;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/topicos")
public class TopicoController {

    private final TopicoService topicoService;
    private final ITopicoRepository iTopicoRepository;


    @Autowired
    public TopicoController(TopicoService topicoService, ITopicoRepository topicoRepository) {
        this.topicoService = topicoService;
        this.iTopicoRepository = topicoRepository;
    }

    @GetMapping
    public ResponseEntity<Page<Topico>> listarTopicos(
            @PageableDefault(page = 0, size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Topico> topicos = iTopicoRepository.findAll(pageable);
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/detallesTopico/{id}")
    public ResponseEntity<Topico> detalharTopico(@PathVariable Long id) {
        Topico topico = iTopicoRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(topico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topico> actualizarTopico(@PathVariable Long id, @RequestBody Topico topico) {
        Optional<Topico> topicoOptional = iTopicoRepository.findById(id);

        if (topicoOptional.isPresent()) {
            Topico topicoActualizado = topicoOptional.get();
            topicoActualizado.setTitulo(topico.getTitulo());
            topicoActualizado.setMensaje(topico.getMensaje());
            topicoActualizado.setAutor(topico.getAutor());
            topicoActualizado.setCurso(topico.getCurso());

            iTopicoRepository.save(topicoActualizado);
            return ResponseEntity.ok(topicoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public Topico findById(@PathVariable Long id) {
        return topicoService.findById(id);
    }

    @PostMapping
    public Topico save(@RequestBody Topico topico) {
        return topicoService.save(topico);
    }

   /* @PutMapping("/{id}")
    public Topico update(@PathVariable Long id, @RequestBody Topico topico) {
        return topicoService.update(id, topico);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopico(@PathVariable Long id) {
        Optional<Topico> topico = iTopicoRepository.findById(id);
        if (topico.isPresent()) {
            iTopicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}