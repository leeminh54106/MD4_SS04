package com.ra.session04.service.product;

import com.ra.session04.exception.DataExistException;
import com.ra.session04.model.dto.request.ProductRequest;
import com.ra.session04.model.entity.Product;
import com.ra.session04.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(ProductRequest productRequest) throws DataExistException{
        Product product = modelMapper.map(productRequest, Product.class);
        if(productRepository.existsByName(productRequest.getName())){
            throw new DataExistException("Product Name is exists","name");
        }
        return productRepository.save(product);
    }

    @Override
    public Product update(ProductRequest productRequest, Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found"));
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        return productRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found"));
        productRepository.deleteById(id);
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found"));
    }
}
