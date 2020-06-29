package net.jogueonline.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import net.jogueonline.web.rest.TestUtil;

public class DiasFuncionamentoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DiasFuncionamento.class);
        DiasFuncionamento diasFuncionamento1 = new DiasFuncionamento();
        diasFuncionamento1.setId(1L);
        DiasFuncionamento diasFuncionamento2 = new DiasFuncionamento();
        diasFuncionamento2.setId(diasFuncionamento1.getId());
        assertThat(diasFuncionamento1).isEqualTo(diasFuncionamento2);
        diasFuncionamento2.setId(2L);
        assertThat(diasFuncionamento1).isNotEqualTo(diasFuncionamento2);
        diasFuncionamento1.setId(null);
        assertThat(diasFuncionamento1).isNotEqualTo(diasFuncionamento2);
    }
}
