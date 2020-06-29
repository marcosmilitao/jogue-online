package net.jogueonline.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import net.jogueonline.web.rest.TestUtil;

public class ModalidadeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Modalidade.class);
        Modalidade modalidade1 = new Modalidade();
        modalidade1.setId(1L);
        Modalidade modalidade2 = new Modalidade();
        modalidade2.setId(modalidade1.getId());
        assertThat(modalidade1).isEqualTo(modalidade2);
        modalidade2.setId(2L);
        assertThat(modalidade1).isNotEqualTo(modalidade2);
        modalidade1.setId(null);
        assertThat(modalidade1).isNotEqualTo(modalidade2);
    }
}
