package ms_login.ms_login.service;

import ms_login.ms_login.dto.LoginRequest;
import ms_login.ms_login.dto.LoginResponse;
import ms_login.ms_login.model.Usuario;
import ms_login.ms_login.Repository.UsuarioRepository;
import ms_login.ms_login.util.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private JwtService jwtService;

    @Override
    public Usuario registrar(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        Optional<Usuario> userOpt = repository.findByUsername(request.getUsername());

        if (userOpt.isPresent() && userOpt.get().getPassword().equals(request.getPassword())) {

            String token = jwtService.generarToken(userOpt.get().getUsername());

            return new LoginResponse(token, "Login exitoso", true);
        }

        return new LoginResponse("Credenciales incorrectas", false);
    }
}