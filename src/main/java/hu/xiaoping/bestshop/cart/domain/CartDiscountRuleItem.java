package hu.xiaoping.bestshop.cart.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A CartDiscountRuleItem.
 */
@Entity
@Table(name = "cart_discount_rule_item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CartDiscountRuleItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @ManyToOne
    @JsonIgnoreProperties(value = "cartDiscountRuleItems", allowSetters = true)
    private CartDiscountRule cartDiscountRule;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public CartDiscountRuleItem productId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public CartDiscountRule getCartDiscountRule() {
        return cartDiscountRule;
    }

    public CartDiscountRuleItem cartDiscountRule(CartDiscountRule cartDiscountRule) {
        this.cartDiscountRule = cartDiscountRule;
        return this;
    }

    public void setCartDiscountRule(CartDiscountRule cartDiscountRule) {
        this.cartDiscountRule = cartDiscountRule;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CartDiscountRuleItem)) {
            return false;
        }
        return id != null && id.equals(((CartDiscountRuleItem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CartDiscountRuleItem{" +
            "id=" + getId() +
            ", productId=" + getProductId() +
            "}";
    }
}
