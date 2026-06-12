package ms_login.ms_login.dto;

public class LoginResponse {
    
    private String token;
    private String mensaje;
    private boolean success;
    private String rol;

    public LoginResponse(String token, String mensaje, boolean success, String rol) {
        this.token = token;
        this.mensaje = mensaje;
        this.success = success;
        this.rol = rol;
    }

    public LoginResponse(String mensaje, boolean success) {
        this.token = null;
        this.mensaje = mensaje;
        this.success = success;
        this.rol = null;
    }

    public LoginResponse() {}

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}