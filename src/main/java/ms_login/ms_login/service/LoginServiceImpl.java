package ms_login.ms_login.service;

import ms_login.ms_login.dto.LoginRequest;
import ms_login.ms_login.dto.LoginResponse;
import ms_login.ms_login.model.Usuario;
import ms_login.ms_login.repository.UsuarioRepository;
import ms_login.ms_login.util.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Usuario registrar(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        if (usuario.getRol() == null || usuario.getRol().isBlank()) {
            usuario.setRol("USER");
        } else {
            usuario.setRol(usuario.getRol().toUpperCase());
        }

        return repository.save(usuario);
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        Optional<Usuario> userOpt = repository.findByUsername(request.getUsername());

        if (userOpt.isPresent() &&
                passwordEncoder.matches(request.getPassword(), userOpt.get().getPassword())) {

            String token = jwtService.generarToken(userOpt.get().getUsername());

            return new LoginResponse(token, "Login exitoso", true, userOpt.get().getRol());
        }

        return new LoginResponse("Credenciales incorrectas", false);
    }
}