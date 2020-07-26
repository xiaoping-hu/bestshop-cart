package hu.xiaoping.bestshop.cart.web.rest;

import hu.xiaoping.bestshop.cart.domain.CartDiscountRule;
import hu.xiaoping.bestshop.cart.service.CartDiscountRuleService;
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
 * REST controller for managing {@link hu.xiaoping.bestshop.cart.domain.CartDiscountRule}.
 */
@RestController
@RequestMapping("/api")
public class CartDiscountRuleResource {

    private final Logger log = LoggerFactory.getLogger(CartDiscountRuleResource.class);

    private static final String ENTITY_NAME = "cartCartDiscountRule";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CartDiscountRuleService cartDiscountRuleService;

    public CartDiscountRuleResource(CartDiscountRuleService cartDiscountRuleService) {
        this.cartDiscountRuleService = cartDiscountRuleService;
    }

    /**
     * {@code POST  /cart-discount-rules} : Create a new cartDiscountRule.
     *
     * @param cartDiscountRule the cartDiscountRule to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cartDiscountRule, or with status {@code 400 (Bad Request)} if the cartDiscountRule has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cart-discount-rules")
    public ResponseEntity<CartDiscountRule> createCartDiscountRule(@Valid @RequestBody CartDiscountRule cartDiscountRule) throws URISyntaxException {
        log.debug("REST request to save CartDiscountRule : {}", cartDiscountRule);
        if (cartDiscountRule.getId() != null) {
            throw new BadRequestAlertException("A new cartDiscountRule cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CartDiscountRule result = cartDiscountRuleService.save(cartDiscountRule);
        return ResponseEntity.created(new URI("/api/cart-discount-rules/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cart-discount-rules} : Updates an existing cartDiscountRule.
     *
     * @param cartDiscountRule the cartDiscountRule to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cartDiscountRule,
     * or with status {@code 400 (Bad Request)} if the cartDiscountRule is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cartDiscountRule couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cart-discount-rules")
    public ResponseEntity<CartDiscountRule> updateCartDiscountRule(@Valid @RequestBody CartDiscountRule cartDiscountRule) throws URISyntaxException {
        log.debug("REST request to update CartDiscountRule : {}", cartDiscountRule);
        if (cartDiscountRule.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CartDiscountRule result = cartDiscountRuleService.save(cartDiscountRule);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cartDiscountRule.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cart-discount-rules} : get all the cartDiscountRules.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cartDiscountRules in body.
     */
    @GetMapping("/cart-discount-rules")
    public ResponseEntity<List<CartDiscountRule>> getAllCartDiscountRules(Pageable pageable) {
        log.debug("REST request to get a page of CartDiscountRules");
        Page<CartDiscountRule> page = cartDiscountRuleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cart-discount-rules/:id} : get the "id" cartDiscountRule.
     *
     * @param id the id of the cartDiscountRule to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cartDiscountRule, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cart-discount-rules/{id}")
    public ResponseEntity<CartDiscountRule> getCartDiscountRule(@PathVariable Long id) {
        log.debug("REST request to get CartDiscountRule : {}", id);
        Optional<CartDiscountRule> cartDiscountRule = cartDiscountRuleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cartDiscountRule);
    }

    /**
     * {@code DELETE  /cart-discount-rules/:id} : delete the "id" cartDiscountRule.
     *
     * @param id the id of the cartDiscountRule to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cart-discount-rules/{id}")
    public ResponseEntity<Void> deleteCartDiscountRule(@PathVariable Long id) {
        log.debug("REST request to delete CartDiscountRule : {}", id);
        cartDiscountRuleService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
