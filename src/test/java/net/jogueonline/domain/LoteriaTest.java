package net.jogueonline.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import net.jogueonline.web.rest.TestUtil;

public class LoteriaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Loteria.class);
        Loteria loteria1 = new Loteria();
        loteria1.setId(1L);
        Loteria loteria2 = new Loteria();
        loteria2.setId(loteria1.getId());
        assertThat(loteria1).isEqualTo(loteria2);
        loteria2.setId(2L);
        assertThat(loteria1).isNotEqualTo(loteria2);
        loteria1.setId(null);
        assertThat(loteria1).isNotEqualTo(loteria2);
    }
}
