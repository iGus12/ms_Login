package ms_login.ms_login.controller;

import ms_login.ms_login.dto.LoginRequest; 

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Importaciones necesarias para devolver el JSON
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> procesarLogin(@RequestBody LoginRequest peticionReact) {
        
        String correoRecibido = peticionReact.getEmail();
        
       
        String jwtSimulado = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJndXN0YXZvIn0.FirmaSimuladaSanosYSalvos123";
        
        
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("email", correoRecibido);
        respuesta.put("token", jwtSimulado);
        respuesta.put("status", "200 OK");
        
        return ResponseEntity.ok(respuesta);
        
    }
}