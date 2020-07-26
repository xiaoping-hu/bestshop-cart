package hu.xiaoping.bestshop.cart.web.rest;

import hu.xiaoping.bestshop.cart.domain.CartDiscountRuleItem;
import hu.xiaoping.bestshop.cart.service.CartDiscountRuleItemService;
import hu.xiaoping.bestshop.cart.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link hu.xiaoping.bestshop.cart.domain.CartDiscountRuleItem}.
 */
@RestController
@RequestMapping("/api")
public class CartDiscountRuleItemResource {

    private final Logger log = LoggerFactory.getLogger(CartDiscountRuleItemResource.class);

    private static final String ENTITY_NAME = "cartCartDiscountRuleItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CartDiscountRuleItemService cartDiscountRuleItemService;

    public CartDiscountRuleItemResource(CartDiscountRuleItemService cartDiscountRuleItemService) {
        this.cartDiscountRuleItemService = cartDiscountRuleItemService;
    }

    /**
     * {@code POST  /cart-discount-rule-items} : Create a new cartDiscountRuleItem.
     *
     * @param cartDiscountRuleItem the cartDiscountRuleItem to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cartDiscountRuleItem, or with status {@code 400 (Bad Request)} if the cartDiscountRuleItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cart-discount-rule-items")
    public ResponseEntity<CartDiscountRuleItem> createCartDiscountRuleItem(@Valid @RequestBody CartDiscountRuleItem cartDiscountRuleItem) throws URISyntaxException {
        log.debug("REST request to save CartDiscountRuleItem : {}", cartDiscountRuleItem);
        if (cartDiscountRuleItem.getId() != null) {
            throw new BadRequestAlertException("A new cartDiscountRuleItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CartDiscountRuleItem result = cartDiscountRuleItemService.save(cartDiscountRuleItem);
        return ResponseEntity.created(new URI("/api/cart-discount-rule-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cart-discount-rule-items} : Updates an existing cartDiscountRuleItem.
     *
     * @param cartDiscountRuleItem the cartDiscountRuleItem to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cartDiscountRuleItem,
     * or with status {@code 400 (Bad Request)} if the cartDiscountRuleItem is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cartDiscountRuleItem couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cart-discount-rule-items")
    public ResponseEntity<CartDiscountRuleItem> updateCartDiscountRuleItem(@Valid @RequestBody CartDiscountRuleItem cartDiscountRuleItem) throws URISyntaxException {
        log.debug("REST request to update CartDiscountRuleItem : {}", cartDiscountRuleItem);
        if (cartDiscountRuleItem.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CartDiscountRuleItem result = cartDiscountRuleItemService.save(cartDiscountRuleItem);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cartDiscountRuleItem.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cart-discount-rule-items} : get all the cartDiscountRuleItems.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cartDiscountRuleItems in body.
     */
    @GetMapping("/cart-discount-rule-items")
    public ResponseEntity<List<CartDiscountRuleItem>> getAllCartDiscountRuleItems(Pageable pageable) {
        log.debug("REST request to get a page of CartDiscountRuleItems");
        Page<CartDiscountRuleItem> page = cartDiscountRuleItemService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cart-discount-rule-items/:id} : get the "id" cartDiscountRuleItem.
     *
     * @param id the id of the cartDiscountRuleItem to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cartDiscountRuleItem, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cart-discount-rule-items/{id}")
    public ResponseEntity<CartDiscountRuleItem> getCartDiscountRuleItem(@PathVariable Long id) {
        log.debug("REST request to get CartDiscountRuleItem : {}", id);
        Optional<CartDiscountRuleItem> cartDiscountRuleItem = cartDiscountRuleItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cartDiscountRuleItem);
    }

    /**
     * {@code DELETE  /cart-discount-rule-items/:id} : delete the "id" cartDiscountRuleItem.
     *
     * @param id the id of the cartDiscountRuleItem to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cart-discount-rule-items/{id}")
    public ResponseEntity<Void> deleteCartDiscountRuleItem(@PathVariable Long id) {
        log.debug("REST request to delete CartDiscountRuleItem : {}", id);
        cartDiscountRuleItemService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
