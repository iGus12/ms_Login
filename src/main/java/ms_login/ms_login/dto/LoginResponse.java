package ms_login.ms_login.dto;

public class LoginResponse {
    
    private String token;
    private String message;
    private boolean success;
    private String rol;

    public LoginResponse(String token, String message, boolean success, String rol) {
        this.token = token;
        this.message = message;
        this.success = success;
        this.rol = rol;
    }

    public LoginResponse(String message, boolean success) {
        this.token = null;
        this.message = message;
        this.success = success;
        this.rol = null;
    }

    public LoginResponse() {}

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}