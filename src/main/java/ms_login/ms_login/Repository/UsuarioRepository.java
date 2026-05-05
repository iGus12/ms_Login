package ms_login.ms_login.Repository;

import ms_login.ms_login.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    

    Optional<Usuario> findByUsername(String username);
}
