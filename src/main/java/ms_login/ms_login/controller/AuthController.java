package ms_login.ms_login.controller;

import ms_login.ms_login.dto.LoginRequest;
import ms_login.ms_login.dto.LoginResponse;
import ms_login.ms_login.model.Usuario;
import ms_login.ms_login.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private ILoginService loginService; 

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
       
        LoginResponse respuesta = loginService.login(request);
        
        if (respuesta.isSuccess()) {
            return ResponseEntity.ok(respuesta);
        } else {
            return ResponseEntity.status(401).body(respuesta); 
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(loginService.registrar(usuario));
    }
}