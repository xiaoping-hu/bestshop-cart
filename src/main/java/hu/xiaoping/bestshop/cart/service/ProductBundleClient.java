package hu.xiaoping.bestshop.cart.service;

import hu.xiaoping.bestshop.cart.client.AuthorizedFeignClient;
import hu.xiaoping.bestshop.cart.domain.Product;
import hu.xiaoping.bestshop.cart.domain.ProductBundle;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@AuthorizedFeignClient(name="product")
public interface ProductBundleClient {
    @RequestMapping(value = "/api/product-bundles/{id}")
    ProductBundle getProductBundle(@PathVariable("id") Long productBundleId);
}
