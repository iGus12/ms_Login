package ms_login.ms_login.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ms_login.ms_login.dto.LoginRequest;
import ms_login.ms_login.dto.LoginResponse;
import ms_login.ms_login.model.Usuario;
import ms_login.ms_login.repository.UsuarioRepository;
import ms_login.ms_login.util.JwtService;

@ExtendWith(MockitoExtension.class)
public class LoginServiceImplTest {

    @Mock
    private UsuarioRepository repository;

    @Mock
    private JwtService jwtService;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private LoginServiceImpl loginService;

    @Test
    @DisplayName("Test REALISTA: Login exitoso como ADMIN (Lucas Puentes)")
    void testLogin_AdminReal() {
        // Arrange
        LoginRequest request = new LoginRequest();
        request.setUsername("luc.puentes");
        request.setPassword("Aivj2011.");

        Usuario usuario = new Usuario();
        usuario.setUsername("luc.puentes");
        usuario.setPassword("hashed_Aivj2011.");
        usuario.setRol("ADMIN");

        when(repository.findByUsername("luc.puentes")).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("Aivj2011.", "hashed_Aivj2011.")).thenReturn(true);
        when(jwtService.generarToken("luc.puentes")).thenReturn("token-admin-seguro");

        // Act
        LoginResponse response = loginService.login(request);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals("ADMIN", response.getRol());
        assertEquals("token-admin-seguro", response.getToken());
    }

    @Test
    @DisplayName("Test REALISTA: Login exitoso como USER (Carla Vasquez)")
    void testLogin_UserReal() {
        // Arrange
        LoginRequest request = new LoginRequest();
        request.setUsername("ca.vasquezl");
        request.setPassword("Carla1234");

        Usuario usuario = new Usuario();
        usuario.setUsername("ca.vasquezl");
        usuario.setPassword("hashed_Carla1234");
        usuario.setRol("USER");

        when(repository.findByUsername("ca.vasquezl")).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("Carla1234", "hashed_Carla1234")).thenReturn(true);
        when(jwtService.generarToken("ca.vasquezl")).thenReturn("token-user-seguro");

        // Act
        LoginResponse response = loginService.login(request);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals("USER", response.getRol());
        assertEquals("token-user-seguro", response.getToken());
    }

    @Test
    @DisplayName("Debería fallar el login con contraseña incorrecta")
    void testLogin_FalloPassword() {
        // Arrange
        LoginRequest request = new LoginRequest();
        request.setUsername("luc.puentes");
        request.setPassword("ClaveErrada");

        Usuario usuario = new Usuario();
        usuario.setPassword("hashed_Aivj2011.");

        when(repository.findByUsername("luc.puentes")).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("ClaveErrada", "hashed_Aivj2011.")).thenReturn(false);

        // Act
        LoginResponse response = loginService.login(request);

        // Assert
        assertFalse(response.isSuccess());
        assertEquals("Credenciales incorrectas", response.getMessage());
    }
}