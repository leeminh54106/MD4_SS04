package com.ra.session04.controller;

import com.ra.session04.exception.DataExistException;
import com.ra.session04.model.dto.request.ProductRequest;
import com.ra.session04.model.dto.response.ResponseDtoSuccess;
import com.ra.session04.service.product.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api.example.com/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    //get All
    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(new ResponseDtoSuccess(productService.getAll(), HttpStatus.OK), HttpStatus.OK);
    }

    // them moi
    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody ProductRequest productRequest) throws DataExistException {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(productService.save(productRequest), HttpStatus.CREATED), HttpStatus.CREATED);
    }

    //Sửa
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(productService.update(productRequest, id), HttpStatus.OK), HttpStatus.OK);
    }

    //xoa
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(productService.findById(id), HttpStatus.OK), HttpStatus.OK);
    }
}
