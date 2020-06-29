package net.jogueonline.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import net.jogueonline.web.rest.TestUtil;

public class LimiteVendaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LimiteVenda.class);
        LimiteVenda limiteVenda1 = new LimiteVenda();
        limiteVenda1.setId(1L);
        LimiteVenda limiteVenda2 = new LimiteVenda();
        limiteVenda2.setId(limiteVenda1.getId());
        assertThat(limiteVenda1).isEqualTo(limiteVenda2);
        limiteVenda2.setId(2L);
        assertThat(limiteVenda1).isNotEqualTo(limiteVenda2);
        limiteVenda1.setId(null);
        assertThat(limiteVenda1).isNotEqualTo(limiteVenda2);
    }
}
