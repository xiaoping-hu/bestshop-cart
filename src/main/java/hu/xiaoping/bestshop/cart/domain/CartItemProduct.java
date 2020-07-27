package hu.xiaoping.bestshop.cart.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public interface CartItemProduct extends Serializable {
    /**
     * Get id of product or product bundle in cart
     * @return id of item
     */
    Long getId();

    /**
     * Get type of cart item, product or product bundle for now
     * @return type of item
     */
    CartItemType getType();

    /**
     * Get title of product
     * @return
     */
    String getTitle();

    /**
     * Get price of product
     * @return
     */
    BigDecimal getPrice();
}
