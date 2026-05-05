package ms_login.ms_login.service;

import ms_login.ms_login.dto.LoginRequest;
import ms_login.ms_login.dto.LoginResponse;
import ms_login.ms_login.model.Usuario;
import ms_login.ms_login.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public Usuario registrar(Usuario usuario) {
       
        return repository.save(usuario);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        
        Optional<Usuario> userOpt = repository.findByUsername(request.getUsername());
        
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(request.getPassword())) {
            return new LoginResponse();
        }
        
        return new LoginResponse();
    }
}
    