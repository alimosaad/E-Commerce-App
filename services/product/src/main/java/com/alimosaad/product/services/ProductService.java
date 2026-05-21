package com.alimosaad.product.services;

import com.alimosaad.product.dto.ProductMapper;
import com.alimosaad.product.repositories.ProductRepository;
import com.alimosaad.product.requests.ProductPurchaseRequest;
import com.alimosaad.product.requests.ProductPurchaseResponse;
import com.alimosaad.product.requests.ProductRequest;
import com.alimosaad.product.requests.ProductResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    /// todo implement product service.
    /// 1. inject product repository interface.
    /// 2. inject product mapper (DTO) service.
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Integer createProduct(@Valid ProductRequest productRequest) {
        /// 1. create product var that map request to product that we can save it
        /// 2. save req
        /// 3. return id for product.
        /// we brief 2 , 3 in return in one line.
        /// todo implement create product method.
        var product=productMapper.toProduct(productRequest);
        return productRepository.save(product).getId();
    }


    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> request) {
        /// todo implement purchase product method.
        return null;
    }

    public ProductResponse findById(Integer productId) {
        /// todo implement findById method.
        return productRepository.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(()->new EntityNotFoundException("Product not found with the ID: "+productId));
    }

    public List<ProductResponse> findAll() {
        /// todo implement findAll method.
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
}