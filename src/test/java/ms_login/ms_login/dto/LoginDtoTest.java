package ms_login.ms_login.dto;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class LoginDtoTest {

    @Test
    void debeCrearLoginRequest() {
        LoginRequest request = new LoginRequest();
        request.setUsername("usuario1");
        request.setPassword("1234");
        request.setEmail("usuario1@email.com");

        assertThat(request.getUsername()).isEqualTo("usuario1");
        assertThat(request.getPassword()).isEqualTo("1234");
        assertThat(request.getEmail()).isEqualTo("usuario1@email.com");
    }

    @Test
    void debeCrearLoginResponseConConstructorVacio() {
        LoginResponse response = new LoginResponse();
        response.setMensaje("Login exitoso");
        response.setSuccess(true);
        response.setRol("ADMIN");

        assertThat(response.getMensaje()).isEqualTo("Login exitoso");
        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getRol()).isEqualTo("ADMIN");
    }

    @Test
    void debeCrearLoginResponseConConstructorParcial() {
        LoginResponse response = new LoginResponse("Credenciales incorrectas", false);

        assertThat(response.getMensaje()).isEqualTo("Credenciales incorrectas");
        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getToken()).isNull();
        assertThat(response.getRol()).isNull();
    }
}