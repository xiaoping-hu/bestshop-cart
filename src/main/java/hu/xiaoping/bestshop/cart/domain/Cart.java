package hu.xiaoping.bestshop.cart.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Cart implements Serializable {
    private String user;

    private List<CartItem> items = new ArrayList<>();

    public Cart(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public CartItem getOrAddCartItemByProduct(CartItemProduct cartItemProduct) {
        return this.getItems().stream().filter(cartItem -> {
            CartItemProduct itemProduct = cartItem.getProduct();
            return itemProduct.getId().equals(cartItemProduct.getId())
                && cartItem.getProduct().getType().equals(cartItemProduct.getType());
        }).findFirst().orElseGet(() -> {
            CartItem newItem = new CartItem(cartItemProduct, 0);
            this.getItems().add(newItem);
            return newItem;
        });
    }

    public BigDecimal getTotal() {
        return this.getItems().stream().map(CartItem::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
