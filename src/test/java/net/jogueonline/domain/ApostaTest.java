package net.jogueonline.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import net.jogueonline.web.rest.TestUtil;

public class ApostaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Aposta.class);
        Aposta aposta1 = new Aposta();
        aposta1.setId(1L);
        Aposta aposta2 = new Aposta();
        aposta2.setId(aposta1.getId());
        assertThat(aposta1).isEqualTo(aposta2);
        aposta2.setId(2L);
        assertThat(aposta1).isNotEqualTo(aposta2);
        aposta1.setId(null);
        assertThat(aposta1).isNotEqualTo(aposta2);
    }
}
