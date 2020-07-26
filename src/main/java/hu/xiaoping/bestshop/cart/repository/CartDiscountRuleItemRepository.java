package hu.xiaoping.bestshop.cart.repository;

import hu.xiaoping.bestshop.cart.domain.CartDiscountRuleItem;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CartDiscountRuleItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CartDiscountRuleItemRepository extends JpaRepository<CartDiscountRuleItem, Long> {
}
