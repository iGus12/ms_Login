package ms_login.ms_login.dto;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LoginDtoTest {

    @Test
    @DisplayName("Debería validar LoginRequest con usuario real (Lucía Puentes)")
    void testLoginRequest_Admin() {
        LoginRequest request = new LoginRequest();
        request.setUsername("luc.puentes");
        request.setPassword("Aivj2011.");
        request.setEmail("luc.puentes@duocuc.cl"); // Asumiendo formato

        assertThat(request.getUsername()).isEqualTo("luc.puentes");
        assertThat(request.getPassword()).isEqualTo("Aivj2011.");
        assertThat(request.getEmail()).isEqualTo("luc.puentes@duocuc.cl");
    }

    @Test
    @DisplayName("Debería validar LoginResponse con rol de Usuario Real (Carla Vasquez)")
    void testLoginResponse_User() {
        LoginResponse response = new LoginResponse("token-ca-vasquezl", "Login exitoso", true, "USER");

        assertThat(response.getMessage()).isEqualTo("Login exitoso");
        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getRol()).isEqualTo("USER");
        assertThat(response.getToken()).isEqualTo("token-ca-vasquezl");
    }

    @Test
    @DisplayName("Debería validar LoginResponse con rol de Admin Real (Lucía Puentes)")
    void testLoginResponse_Admin() {
        LoginResponse response = new LoginResponse("token-luc-puentes", "Login exitoso", true, "ADMIN");

        assertThat(response.getRol()).isEqualTo("ADMIN");
        assertThat(response.getMessage()).isEqualTo("Login exitoso");
    }
}