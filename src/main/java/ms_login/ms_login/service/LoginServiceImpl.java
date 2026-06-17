    package ms_login.ms_login.service;

    import ms_login.ms_login.dto.LoginRequest;
    import ms_login.ms_login.dto.LoginResponse;
    import ms_login.ms_login.model.Usuario;
    import ms_login.ms_login.Repository.UsuarioRepository;
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
    try {
        System.out.println("Intentando login con username: " + request.getUsername());

        Optional<Usuario> userOpt = repository.findByUsername(request.getUsername());

        if (userOpt.isEmpty()) {
            System.out.println("Usuario no encontrado: " + request.getUsername());
            return new LoginResponse("Credenciales incorrectas", false);
        }

        Usuario usuario = userOpt.get();

        System.out.println("Usuario encontrado: " + usuario.getUsername());
        System.out.println("Rol encontrado: " + usuario.getRol());

        boolean passwordCorrecta = passwordEncoder.matches(
                request.getPassword(),
                usuario.getPassword()
        );

        System.out.println("Password correcta: " + passwordCorrecta);

        if (!passwordCorrecta) {
            return new LoginResponse("Credenciales incorrectas", false);
        }

        String token = jwtService.generarToken(usuario.getUsername());

        System.out.println("Token generado correctamente");

        return new LoginResponse(
                token,
                "Login exitoso",
                true,
                usuario.getRol()
        );

    } catch (Exception error) {
        error.printStackTrace();

        return new LoginResponse(
                "Error interno en login: " + error.getMessage(),
                false
        );
    }
}
    }