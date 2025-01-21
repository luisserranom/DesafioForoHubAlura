package alura.ForoHub.repository;

import alura.ForoHub.entity.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ITopicoRepository extends JpaRepository<Topico, Long> {
    @Query("SELECT t FROM Topico t WHERE t.titulo = :titulo AND t.mensaje = :mensaje")
    Topico findByTituloAndMensaje(@Param("titulo") String titulo, @Param("mensaje") String mensaje);
}