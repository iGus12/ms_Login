package ms_login.ms_login.service;

import ms_login.ms_login.dto.LoginRequest;
import ms_login.ms_login.dto.LoginResponse;
import ms_login.ms_login.model.Usuario; 

public interface ILoginService {
    
    LoginResponse login(LoginRequest request);
  
    Usuario registrar(Usuario usuario);
}