package hu.xiaoping.bestshop.cart.repository;

import hu.xiaoping.bestshop.cart.domain.CartDiscountRule;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CartDiscountRule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CartDiscountRuleRepository extends JpaRepository<CartDiscountRule, Long> {
}
