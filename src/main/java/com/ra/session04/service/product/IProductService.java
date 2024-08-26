package com.ra.session04.service.product;

import com.ra.session04.exception.DataExistException;
import com.ra.session04.model.dto.request.ProductRequest;
import com.ra.session04.model.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    Product save(ProductRequest productRequest) throws DataExistException;
    Product update(ProductRequest productRequest, Integer id);
    void delete(Integer id);
    Product findById(Integer id);
}
