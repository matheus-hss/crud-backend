package com.example.crud.control;

import com.example.crud.dto.ProductDTO;
import com.example.crud.model.LabModel;
import com.example.crud.model.ProductModel;
import com.example.crud.service.LabService;
import com.example.crud.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
    @Autowired
    private LabService labService;

    @ApiOperation(value = "Add a new product.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Create a new product and binding a lab to it."),
            @ApiResponse(code = 404, message = "Product not found.")
    })
    @PostMapping("/lab/{id}")
    public ResponseEntity<ProductModel> save(@RequestBody @Valid ProductDTO productDTO, @PathVariable UUID id){
        Optional<LabModel> optional = labService.findById(id);

        if(optional.isEmpty()) return ResponseEntity.notFound().build();

        var productModel = new ProductModel();

        BeanUtils.copyProperties(productDTO, productModel);

        productModel.setLab(optional.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productModel));
    }

    @ApiOperation(value = "Delete a product.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product deleted."),
            @ApiResponse(code = 404, message = "Product not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductModel> delete(@PathVariable UUID id){
        Optional<ProductModel> optional = productService.findById(id);

        if(optional.isEmpty()) return ResponseEntity.notFound().build();

        productService.delete(optional.get());

        return ResponseEntity.ok(optional.get());
    }

    @ApiOperation(value = "Update a product.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product updated."),
            @ApiResponse(code = 404, message = "Product or lab not found.")
    })
    @PutMapping("/{productId}/lab/{labId}")
    public ResponseEntity<?> update(@PathVariable UUID productId,
                                    @PathVariable UUID labId, @RequestBody @Valid ProductDTO productDTO){
        Optional<ProductModel> optionalProductModel = productService.findById(productId);
        Optional<LabModel> optionalLabModel = labService.findById(labId);

        if(optionalProductModel.isEmpty() || optionalLabModel.isEmpty()) return ResponseEntity.notFound().build();

        var productModel = new ProductModel();

        BeanUtils.copyProperties(productDTO, productModel);

        productModel.setId(productId);
        productModel.setLab(optionalLabModel.get());

        productService.save(productModel);

        return ResponseEntity.ok(productModel);
    }

    @ApiOperation(value = "Return a product by id.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product found."),
            @ApiResponse(code = 404, message = "Product not found.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> findById(@PathVariable UUID id){
        Optional<ProductModel> optional = productService.findById(id);

        if(optional.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.status(HttpStatus.OK).body(optional.get());
    }

    @ApiOperation(value = "Return a list of products.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "List found."),
            @ApiResponse(code = 404, message = "No one content found.")
    })
    @GetMapping("/")
    public ResponseEntity<Page<ProductModel>> findAll(@PageableDefault Pageable pageable){
        var list = productService.findAll(pageable);

        if(list.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(list);
    }
}
