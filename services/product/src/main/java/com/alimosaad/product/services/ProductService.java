package com.alimosaad.product.services;

import com.alimosaad.product.dto.ProductMapper;
import com.alimosaad.product.exceptions.ProductPurchaseException;
import com.alimosaad.product.repositories.ProductRepository;
import com.alimosaad.product.requests.ProductPurchaseRequest;
import com.alimosaad.product.requests.ProductPurchaseResponse;
import com.alimosaad.product.requests.ProductRequest;
import com.alimosaad.product.requests.ProductResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
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


    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> productPurchaseRequest) {
        /// todo implement purchase product method.
        /// 1. we need to create record or class that contains id and quantity
        /// 2. extract id's
        var productIds=productPurchaseRequest
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        /// 3. check if this id's exist in data base or not.
        var storedProducts =productRepository.findAllByIdInOrderById(productIds);
        if(productIds.size()!= storedProducts.size()){
            throw new ProductPurchaseException("One or more products doesn't exists");
        }
        ///  if all products that user need to purchase in data base
        ///  create var storedReq and sort our purchase request and sort them byId
        var sortedRequest=productPurchaseRequest
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        /// start purchases different product
        var purchasedProduct=new ArrayList<ProductPurchaseResponse>();
        for (int i =0 ; i<storedProducts.size();i++){
            var product=storedProducts.get(i);
            var productRequest=sortedRequest.get(i);
            if (product.getAvailableQuantity()<productRequest.quantity()){
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: "
                        +productRequest.productId());
            }
            var newAvailableQuantity=product.getAvailableQuantity()-productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProduct.add(productMapper.toProductpurchaseResponse(product , productRequest.quantity()));
        }
        return purchasedProduct;
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