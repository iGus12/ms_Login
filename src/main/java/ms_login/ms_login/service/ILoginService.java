package ms_login.ms_login.service;
import ms_login.ms_login.dto.LoginRequest;
import ms_login.ms_login.dto.LoginResponse;

public interface ILoginService {
    LoginResponse login(LoginRequest request);
}