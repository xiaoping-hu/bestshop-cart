package hu.xiaoping.bestshop.cart.service;

import hu.xiaoping.bestshop.cart.domain.CartDiscountRule;
import hu.xiaoping.bestshop.cart.repository.CartDiscountRuleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link CartDiscountRule}.
 */
@Service
@Transactional
public class CartDiscountRuleService {

    private final Logger log = LoggerFactory.getLogger(CartDiscountRuleService.class);

    private final CartDiscountRuleRepository cartDiscountRuleRepository;

    public CartDiscountRuleService(CartDiscountRuleRepository cartDiscountRuleRepository) {
        this.cartDiscountRuleRepository = cartDiscountRuleRepository;
    }

    /**
     * Save a cartDiscountRule.
     *
     * @param cartDiscountRule the entity to save.
     * @return the persisted entity.
     */
    public CartDiscountRule save(CartDiscountRule cartDiscountRule) {
        log.debug("Request to save CartDiscountRule : {}", cartDiscountRule);
        return cartDiscountRuleRepository.save(cartDiscountRule);
    }

    /**
     * Get all the cartDiscountRules.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CartDiscountRule> findAll(Pageable pageable) {
        log.debug("Request to get all CartDiscountRules");
        return cartDiscountRuleRepository.findAll(pageable);
    }

    /**
     * Get all the cartDiscountRules order by priority
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CartDiscountRule> findAllOrderByPriority() {
        log.debug("Request to get all CartDiscountRules");
        return cartDiscountRuleRepository.findAllByOrderByPriorityAsc();
    }

    /**
     * Get one cartDiscountRule by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CartDiscountRule> findOne(Long id) {
        log.debug("Request to get CartDiscountRule : {}", id);
        return cartDiscountRuleRepository.findById(id);
    }

    /**
     * Delete the cartDiscountRule by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CartDiscountRule : {}", id);
        cartDiscountRuleRepository.deleteById(id);
    }
}
