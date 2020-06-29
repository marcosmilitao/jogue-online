package net.jogueonline.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import net.jogueonline.web.rest.TestUtil;

public class BancaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Banca.class);
        Banca banca1 = new Banca();
        banca1.setId(1L);
        Banca banca2 = new Banca();
        banca2.setId(banca1.getId());
        assertThat(banca1).isEqualTo(banca2);
        banca2.setId(2L);
        assertThat(banca1).isNotEqualTo(banca2);
        banca1.setId(null);
        assertThat(banca1).isNotEqualTo(banca2);
    }
}
