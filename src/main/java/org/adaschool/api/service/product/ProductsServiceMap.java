package org.adaschool.api.service.product;

import org.adaschool.api.exception.ProductNotFoundException;
import org.adaschool.api.repository.product.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceMap implements ProductsService {

    final HashMap<String, Product> hashMap = new HashMap<>();

    @Override
    public Product save(final Product product) {
        hashMap.put(product.getId(), product);
        return product;
    }

    @Override
    public Optional<Product> findById(final String id) {
        final Product product = hashMap.get(id);
        return Optional.of(product);
    }

    @Override
    public List<Product> all() {
        return new ArrayList<>(hashMap.values());
    }

    @Override
    public void deleteById(final String id) {
        hashMap.remove(id);
    }

    @Override
    public Product update(final Product product, final String productId) {
        final Product p = hashMap.get(productId);
        p.setName(product.getName());
        p.setDescription(product.getDescription());
        p.setCategory(product.getCategory());
        p.setTags(product.getTags());
        p.setPrice(product.getPrice());
        p.setImageUrl(product.getImageUrl());
        return p;
    }
}
