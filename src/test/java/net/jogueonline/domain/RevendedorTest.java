package net.jogueonline.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import net.jogueonline.web.rest.TestUtil;

public class RevendedorTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Revendedor.class);
        Revendedor revendedor1 = new Revendedor();
        revendedor1.setId(1L);
        Revendedor revendedor2 = new Revendedor();
        revendedor2.setId(revendedor1.getId());
        assertThat(revendedor1).isEqualTo(revendedor2);
        revendedor2.setId(2L);
        assertThat(revendedor1).isNotEqualTo(revendedor2);
        revendedor1.setId(null);
        assertThat(revendedor1).isNotEqualTo(revendedor2);
    }
}
