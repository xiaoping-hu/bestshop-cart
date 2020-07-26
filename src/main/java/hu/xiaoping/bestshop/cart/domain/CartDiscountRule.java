package hu.xiaoping.bestshop.cart.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A CartDiscountRule.
 */
@Entity
@Table(name = "cart_discount_rule")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CartDiscountRule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 256)
    @Column(name = "name", length = 256, nullable = false)
    private String name;

    @NotNull
    @Column(name = "minimum_quantity", nullable = false)
    private Integer minimumQuantity;

    @NotNull
    @Column(name = "discount_quantity", nullable = false)
    private Integer discountQuantity;

    @NotNull
    @Column(name = "discount_amount", nullable = false)
    private Integer discountAmount;

    @OneToMany(mappedBy = "cartDiscountRule")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<CartDiscountRuleItem> cartDiscountRuleItems = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public CartDiscountRule name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinimumQuantity() {
        return minimumQuantity;
    }

    public CartDiscountRule minimumQuantity(Integer minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
        return this;
    }

    public void setMinimumQuantity(Integer minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public Integer getDiscountQuantity() {
        return discountQuantity;
    }

    public CartDiscountRule discountQuantity(Integer discountQuantity) {
        this.discountQuantity = discountQuantity;
        return this;
    }

    public void setDiscountQuantity(Integer discountQuantity) {
        this.discountQuantity = discountQuantity;
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }

    public CartDiscountRule discountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
        return this;
    }

    public void setDiscountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Set<CartDiscountRuleItem> getCartDiscountRuleItems() {
        return cartDiscountRuleItems;
    }

    public CartDiscountRule cartDiscountRuleItems(Set<CartDiscountRuleItem> cartDiscountRuleItems) {
        this.cartDiscountRuleItems = cartDiscountRuleItems;
        return this;
    }

    public CartDiscountRule addCartDiscountRuleItem(CartDiscountRuleItem cartDiscountRuleItem) {
        this.cartDiscountRuleItems.add(cartDiscountRuleItem);
        cartDiscountRuleItem.setCartDiscountRule(this);
        return this;
    }

    public CartDiscountRule removeCartDiscountRuleItem(CartDiscountRuleItem cartDiscountRuleItem) {
        this.cartDiscountRuleItems.remove(cartDiscountRuleItem);
        cartDiscountRuleItem.setCartDiscountRule(null);
        return this;
    }

    public void setCartDiscountRuleItems(Set<CartDiscountRuleItem> cartDiscountRuleItems) {
        this.cartDiscountRuleItems = cartDiscountRuleItems;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CartDiscountRule)) {
            return false;
        }
        return id != null && id.equals(((CartDiscountRule) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CartDiscountRule{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", minimumQuantity=" + getMinimumQuantity() +
            ", discountQuantity=" + getDiscountQuantity() +
            ", discountAmount=" + getDiscountAmount() +
            "}";
    }
}
