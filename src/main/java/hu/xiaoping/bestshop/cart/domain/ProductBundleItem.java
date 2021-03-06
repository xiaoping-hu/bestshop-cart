package hu.xiaoping.bestshop.cart.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A ProductBundleItem.
 */
public class ProductBundleItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private Integer quantity;

    @NotNull

    private Integer discountAmount;


    @JsonIgnoreProperties(value = "productBundleItems", allowSetters = true)
    private ProductBundle productBundle;

    @JsonIgnoreProperties(value = "productBundleItems", allowSetters = true)
    private Product product;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ProductBundleItem quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }

    public ProductBundleItem discountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
        return this;
    }

    public void setDiscountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
    }

    public ProductBundle getProductBundle() {
        return productBundle;
    }

    public ProductBundleItem productBundle(ProductBundle productBundle) {
        this.productBundle = productBundle;
        return this;
    }

    public void setProductBundle(ProductBundle productBundle) {
        this.productBundle = productBundle;
    }

    public Product getProduct() {
        return product;
    }

    public ProductBundleItem product(Product product) {
        this.product = product;
        return this;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductBundleItem)) {
            return false;
        }
        return id != null && id.equals(((ProductBundleItem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductBundleItem{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", discountAmount=" + getDiscountAmount() +
            "}";
    }
}
