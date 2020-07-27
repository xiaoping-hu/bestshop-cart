package hu.xiaoping.bestshop.cart.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A Product.
 */
public class Product implements Serializable, CartItemProduct {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    @Size(max = 256)
    private String title;

    private String description;

    @NotNull
    private BigDecimal price;

    @Size(max = 256)
    private String imageUrl;

    private Set<ProductBundleItem> productBundleItems = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    @Override
    public CartItemType getType() {
        return CartItemType.PRODUCT;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Product title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public Product description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Product imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<ProductBundleItem> getProductBundleItems() {
        return productBundleItems;
    }

    public Product productBundleItems(Set<ProductBundleItem> productBundleItems) {
        this.productBundleItems = productBundleItems;
        return this;
    }

    public Product addProductBundleItems(ProductBundleItem productBundleItem) {
        this.productBundleItems.add(productBundleItem);
        productBundleItem.setProduct(this);
        return this;
    }

    public Product removeProductBundleItems(ProductBundleItem productBundleItem) {
        this.productBundleItems.remove(productBundleItem);
        productBundleItem.setProduct(null);
        return this;
    }

    public void setProductBundleItems(Set<ProductBundleItem> productBundleItems) {
        this.productBundleItems = productBundleItems;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return id != null && id.equals(((Product) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", price=" + getPrice() +
            ", imageUrl='" + getImageUrl() + "'" +
            "}";
    }
}
