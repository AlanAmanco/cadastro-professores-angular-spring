package amanco.alan.cadastroProfessor.model.repository;

import amanco.alan.cadastroProfessor.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository  extends JpaRepository<Usuario,Integer> {

    UserDetails findByUsername(String username);
}
