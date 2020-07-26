package hu.xiaoping.bestshop.cart.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.xiaoping.bestshop.cart.web.rest.TestUtil;

public class CartDiscountRuleTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CartDiscountRule.class);
        CartDiscountRule cartDiscountRule1 = new CartDiscountRule();
        cartDiscountRule1.setId(1L);
        CartDiscountRule cartDiscountRule2 = new CartDiscountRule();
        cartDiscountRule2.setId(cartDiscountRule1.getId());
        assertThat(cartDiscountRule1).isEqualTo(cartDiscountRule2);
        cartDiscountRule2.setId(2L);
        assertThat(cartDiscountRule1).isNotEqualTo(cartDiscountRule2);
        cartDiscountRule1.setId(null);
        assertThat(cartDiscountRule1).isNotEqualTo(cartDiscountRule2);
    }
}
