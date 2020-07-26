package hu.xiaoping.bestshop.cart.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.xiaoping.bestshop.cart.web.rest.TestUtil;

public class CartDiscountRuleItemTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CartDiscountRuleItem.class);
        CartDiscountRuleItem cartDiscountRuleItem1 = new CartDiscountRuleItem();
        cartDiscountRuleItem1.setId(1L);
        CartDiscountRuleItem cartDiscountRuleItem2 = new CartDiscountRuleItem();
        cartDiscountRuleItem2.setId(cartDiscountRuleItem1.getId());
        assertThat(cartDiscountRuleItem1).isEqualTo(cartDiscountRuleItem2);
        cartDiscountRuleItem2.setId(2L);
        assertThat(cartDiscountRuleItem1).isNotEqualTo(cartDiscountRuleItem2);
        cartDiscountRuleItem1.setId(null);
        assertThat(cartDiscountRuleItem1).isNotEqualTo(cartDiscountRuleItem2);
    }
}
