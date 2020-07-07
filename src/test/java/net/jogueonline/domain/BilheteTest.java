package net.jogueonline.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import net.jogueonline.web.rest.TestUtil;

public class BilheteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bilhete.class);
        Bilhete bilhete1 = new Bilhete();
        bilhete1.setId(1L);
        Bilhete bilhete2 = new Bilhete();
        bilhete2.setId(bilhete1.getId());
        assertThat(bilhete1).isEqualTo(bilhete2);
        bilhete2.setId(2L);
        assertThat(bilhete1).isNotEqualTo(bilhete2);
        bilhete1.setId(null);
        assertThat(bilhete1).isNotEqualTo(bilhete2);
    }
}
