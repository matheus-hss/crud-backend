package com.example.crud.control;

import com.example.crud.dto.ProductDTO;
import com.example.crud.model.Product;
import com.example.crud.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {
    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Add a new product")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Create a new product")
    })
    @PostMapping("/")
    public ResponseEntity<Product> save(@RequestBody @Valid ProductDTO productDTO){
        var product = new Product();

        BeanUtils.copyProperties(productDTO, product);

        return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.save(product));
    }

    @ApiOperation(value = "Delete a product")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product deleted"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable UUID id){
        Optional<Product> optionalId = this.productService.findById(id);

        if(optionalId.isEmpty()) return ResponseEntity.notFound().build();

        this.productService.delete(optionalId.get());

        return ResponseEntity.ok(optionalId.get());
    }

    @ApiOperation(value = "Update a product")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product updated"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody @Valid ProductDTO productDTO){
        Optional<Product> optionalId = this.productService.findById(id);

        if(optionalId.isEmpty()) return ResponseEntity.notFound().build();

        var product = new Product();

        BeanUtils.copyProperties(productDTO, product);

        product.setId(id);

        return ResponseEntity.ok(this.productService.save(product));
    }

    @ApiOperation(value = "Return a product by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product found"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable UUID id){
        Optional<Product> optionalId = this.productService.findById(id);

        if(optionalId.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(optionalId.get());
    }

    @ApiOperation(value = "Return a list of products")
    @ApiResponses({
            @ApiResponse(code = 200, message = "List found"),
            @ApiResponse(code = 404, message = "No one content found")
    })
    @GetMapping("/")
    public ResponseEntity<Page<Product>> findAll(@PageableDefault Pageable pageable){
        var listProduct = this.productService.findAll(pageable);

        if(listProduct.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(listProduct);
    }
}
