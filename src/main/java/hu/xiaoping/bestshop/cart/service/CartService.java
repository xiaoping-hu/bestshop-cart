package hu.xiaoping.bestshop.cart.service;

import hu.xiaoping.bestshop.cart.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    ProductClient productClient;

    @Autowired
    ProductBundleClient productBundleClient;

    @Autowired
    CartDiscountRuleService cartDiscountRuleService;

    /**
     * Add product into cart, and cache them by user loging
     *
     * @param user user login
     * @param cart user cart with product
     * @param productId productId of product
     * @param quantity qunatity to add
     * @return Updated cart
     */
    @CachePut(value="carts", key="#user")
    public Cart addProduct(String user, Cart cart, Long productId, Integer quantity) {
        Product product = productClient.getProduct(productId);
        CartItem item = cart.getOrAddCartItemByProduct(product);
        item.addQuantity(quantity);
        cartDiscountRuleService.findAllOrderByPriority().stream().filter(cartDiscountRule ->
            cartDiscountRule.getCartDiscountRuleItems().stream().map(CartDiscountRuleItem::getProductId).findFirst().isPresent()
        ).findFirst().ifPresent(item::apply);
        return cart;
    }

    /**
     * Add product bundle into cart, and cache them by userlogin
     *
     * @param user user login
     * @param cart user cart with product
     * @param productBundleId productId of product
     * @param quantity qunatity to add
     * @return Updated cart
     */
    @CachePut(value="carts", key="#user")
    public Cart addProductBundle(String user, Cart cart, Long productBundleId, Integer quantity) {
        ProductBundle bundle = productBundleClient.getProductBundle(productBundleId);
        CartItem item = cart.getOrAddCartItemByProduct(bundle);
        item.addQuantity(quantity);
        return cart;
    }

    /**
     * Get cart by user login, create empty when if no cart
     * @param user user login
     * @return User's cart
     */
    @Cacheable(value="carts", key="#user")
    public Cart getCart(String user){
        return new Cart(user);
    }
}
