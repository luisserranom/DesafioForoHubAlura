package alura.ForoHub.repository;


import alura.ForoHub.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

}