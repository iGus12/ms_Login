package ms_login.ms_login.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.jsonwebtoken.JwtException;

public class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
    }

    @Test
    @DisplayName("1. Happy Path: Generar y extraer token correctamente")
    void testGenerarYValidarToken() {
        String username = "luc.puentes";
        String token = jwtService.generarToken(username);
        
        assertNotNull(token);
        assertEquals(username, jwtService.extractSubject(token));
    }

    @Test
    @DisplayName("2. Seguridad: Fallar al leer un token manipulado")
    void testTokenManipulado() {
        // Creamos un token real
        String token = jwtService.generarToken("luc.puentes");
        // Lo rompemos (le cambiamos un caracter)
        String tokenRoto = token.substring(0, token.length() - 5) + "abcde";

        // El sistema debería lanzar un error al intentar leerlo
        assertThrows(JwtException.class, () -> {
            jwtService.extractSubject(tokenRoto);
        }, "Debería lanzar error si el token fue manipulado");
    }

    @Test
    @DisplayName("3. Robustez: Fallar ante token vacío o mal formado")
    void testTokenInvalido() {
        assertThrows(JwtException.class, () -> {
            jwtService.extractSubject("esto-no-es-un-token");
        }, "Debería lanzar error ante una cadena que no es un JWT");
    }
}