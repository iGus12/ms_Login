package ms_login.ms_login;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MsLoginApplicationTests {

    @Test
    void debeExistirClasePrincipal() {
        assertThat(MsLoginApplication.class).isNotNull();
    }
}