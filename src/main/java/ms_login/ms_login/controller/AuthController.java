package ms_login.ms_login.controller;

package cl.sanosysalvo.ms_login.controller;

import cl.sanosysalvo.ms_login.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController

@RequestMapping("/api/auth")
public class AuthController {

   

    
    @PostMapping("/login")
    public ResponseEntity<?> procesarLogin(@RequestBody LoginRequest peticionReact) {
        
        
        String correoRecibido = peticionReact.getEmail();
        
        
        return ResponseEntity.ok("Conexión exitosa. Recibimos el correo: " + correoRecibido);
    }
}