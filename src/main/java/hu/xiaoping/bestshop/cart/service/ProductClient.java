package hu.xiaoping.bestshop.cart.service;

import hu.xiaoping.bestshop.cart.client.AuthorizedFeignClient;
import hu.xiaoping.bestshop.cart.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@AuthorizedFeignClient(name="product")
public interface ProductClient {
    @RequestMapping(value = "/api/products/{id}")
    Product getProduct(@PathVariable("id") Long productId);
}
