package com.example.crud.service;

import com.example.crud.model.ProductModel;
import com.example.crud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public ProductModel save(ProductModel productModel){
        return productRepository.save(productModel);
    }

    @Transactional
    public void delete(ProductModel productModel){
        productRepository.delete(productModel);
    }

    public Page<ProductModel> findAll(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    public Optional<ProductModel> findById(UUID id){
        return productRepository.findById(id);
    }
}
