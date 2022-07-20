package com.example.crud.service;

import com.example.crud.model.Product;
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
    public Product save(Product product){
        return this.productRepository.save(product);
    }

    @Transactional
    public void delete(Product product){
        this.productRepository.delete(product);
    }

    public Page<Product> findAll(Pageable pageable){
        return this.productRepository.findAll(pageable);
    }

    public Optional<Product> findById(UUID id){
        return this.productRepository.findById(id);
    }
}
