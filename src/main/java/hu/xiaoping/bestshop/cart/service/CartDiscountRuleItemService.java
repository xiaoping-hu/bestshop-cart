package hu.xiaoping.bestshop.cart.service;

import hu.xiaoping.bestshop.cart.domain.CartDiscountRuleItem;
import hu.xiaoping.bestshop.cart.repository.CartDiscountRuleItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CartDiscountRuleItem}.
 */
@Service
@Transactional
public class CartDiscountRuleItemService {

    private final Logger log = LoggerFactory.getLogger(CartDiscountRuleItemService.class);

    private final CartDiscountRuleItemRepository cartDiscountRuleItemRepository;

    public CartDiscountRuleItemService(CartDiscountRuleItemRepository cartDiscountRuleItemRepository) {
        this.cartDiscountRuleItemRepository = cartDiscountRuleItemRepository;
    }

    /**
     * Save a cartDiscountRuleItem.
     *
     * @param cartDiscountRuleItem the entity to save.
     * @return the persisted entity.
     */
    public CartDiscountRuleItem save(CartDiscountRuleItem cartDiscountRuleItem) {
        log.debug("Request to save CartDiscountRuleItem : {}", cartDiscountRuleItem);
        return cartDiscountRuleItemRepository.save(cartDiscountRuleItem);
    }

    /**
     * Get all the cartDiscountRuleItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CartDiscountRuleItem> findAll(Pageable pageable) {
        log.debug("Request to get all CartDiscountRuleItems");
        return cartDiscountRuleItemRepository.findAll(pageable);
    }


    /**
     * Get one cartDiscountRuleItem by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CartDiscountRuleItem> findOne(Long id) {
        log.debug("Request to get CartDiscountRuleItem : {}", id);
        return cartDiscountRuleItemRepository.findById(id);
    }

    /**
     * Delete the cartDiscountRuleItem by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CartDiscountRuleItem : {}", id);
        cartDiscountRuleItemRepository.deleteById(id);
    }
}
