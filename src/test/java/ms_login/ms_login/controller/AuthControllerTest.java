package ms_login.ms_login.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import ms_login.ms_login.dto.LoginRequest;
import ms_login.ms_login.dto.LoginResponse;
import ms_login.ms_login.service.ILoginService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ILoginService loginService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /api/auth/login - Login exitoso retorna 200")
    void testLogin_Exitoso() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setUsername("luc.puentes");
        request.setPassword("Aivj2011.");

        LoginResponse response = new LoginResponse("token-fake-123", "Login exitoso", true, "ADMIN");
        
        when(loginService.login(any(LoginRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/auth/login") // <--- RUTA CORRECTA
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.token").value("token-fake-123"))
                .andExpect(jsonPath("$.rol").value("ADMIN"));
    }

    @Test
    @DisplayName("POST /api/auth/login - Credenciales incorrectas retorna 401")
    void testLogin_Fallido() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setUsername("luc.puentes");
        request.setPassword("ClaveErrada");

        LoginResponse response = new LoginResponse("Credenciales incorrectas", false);
        
        when(loginService.login(any(LoginRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/auth/login") // <--- RUTA CORRECTA
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized()) // <--- ESPERAMOS 401 PORQUE EL CÓDIGO DEVUELVE 401
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Credenciales incorrectas"));
    }
}