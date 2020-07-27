package hu.xiaoping.bestshop.cart.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;


public class CartItem implements Serializable {
    private CartItemProduct product;
    private Integer quantity;
    private BigDecimal total;

    public CartItem(CartItemProduct product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public void apply(CartDiscountRule rule){
        this.setTotal(quantity >= rule.getMinimumQuantity()?
            product.getPrice()
            .multiply(BigDecimal.valueOf(rule.getDiscountAmount()/100 * rule.getDiscountQuantity() + quantity - rule.getDiscountQuantity())) :
            product.getPrice().multiply(BigDecimal.valueOf(quantity)));
    }

    public void addQuantity(Integer quantityToAdd){
        this.quantity += quantityToAdd;
        this.total = this.getProduct().getPrice().multiply(BigDecimal.valueOf(this.quantity));
    }

    public CartItemProduct getProduct() {
        return product;
    }

    public void setProduct(CartItemProduct product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return product.equals(cartItem.product) &&
            total.equals(cartItem.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, total);
    }
}
