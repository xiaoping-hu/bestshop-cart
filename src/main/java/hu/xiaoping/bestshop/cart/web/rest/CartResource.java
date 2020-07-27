package hu.xiaoping.bestshop.cart.web.rest;

import hu.xiaoping.bestshop.cart.domain.Cart;
import hu.xiaoping.bestshop.cart.domain.CartDiscountRule;
import hu.xiaoping.bestshop.cart.security.SecurityUtils;
import hu.xiaoping.bestshop.cart.service.CartService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

/**
 * REST controller for managing {@link CartDiscountRule}.
 */
@RestController
@RequestMapping("/api")
public class CartResource {

    private final Logger log = LoggerFactory.getLogger(CartResource.class);

    private static final String ENTITY_NAME = "cartCart";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CartService cartService;


    public CartResource(CartService cartService) {
        this.cartService = cartService;
    }

    @PutMapping("/carts/add-product/{id}")
    public ResponseEntity<Cart> addProduct(@PathVariable Long id) {
        log.debug("REST request to add product to ShoppingCart");
        String user = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new EntityNotFoundException("User not found"));
        Cart result = cartService.addProduct(user, cartService.getCart(user), id, 1);
        return ResponseEntity.ok()
            .body(result);
    }

    @PutMapping("/carts/add-product-bundle/{id}")
    public ResponseEntity<Cart> addProductBundle(@PathVariable Long id) {
        log.debug("REST request to add product to ShoppingCart");
        String user = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new EntityNotFoundException("User not found"));
        Cart result = cartService.addProductBundle(user, cartService.getCart(user), id, 1);
        return ResponseEntity.ok()
            .body(result);
    }

    @GetMapping("/carts/current-user")
    public ResponseEntity<Cart> getCurrentUserCart() {
        String user = SecurityUtils.getCurrentUserLogin().orElse("");
        log.debug("REST request to get ShoppingCart for user: {}", user);
        Optional<Cart> cart = Optional.ofNullable(cartService.getCart(user));
        return ResponseUtil.wrapOrNotFound(cart);
    }

}
