package alura.ForoHub.repository;

import alura.ForoHub.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ICursoRepository extends JpaRepository<Curso, Long> {

}