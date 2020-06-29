package net.jogueonline.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import net.jogueonline.web.rest.TestUtil;

public class PremioTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Premio.class);
        Premio premio1 = new Premio();
        premio1.setId(1L);
        Premio premio2 = new Premio();
        premio2.setId(premio1.getId());
        assertThat(premio1).isEqualTo(premio2);
        premio2.setId(2L);
        assertThat(premio1).isNotEqualTo(premio2);
        premio1.setId(null);
        assertThat(premio1).isNotEqualTo(premio2);
    }
}
