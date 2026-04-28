package ms_login.ms_login.dto;

public class LoginResponse {

    private String mensaje;
    private boolean success;

    public LoginResponse() {
    }

    public LoginResponse(String mensaje, boolean success) {
        this.mensaje = mensaje;
        this.success = success;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}