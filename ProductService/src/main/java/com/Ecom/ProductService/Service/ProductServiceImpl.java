package com.Ecom.ProductService.Service;

import com.Ecom.ProductService.Dtos.ProductRequestDTO;
import com.Ecom.ProductService.Dtos.ProductResponseDTO;
import com.Ecom.ProductService.Dtos.ProductResponseListDTO;
import com.Ecom.ProductService.Exceptions.InvalidTitleException;
import com.Ecom.ProductService.Exceptions.ProductNotFoundException;
import com.Ecom.ProductService.Mapper.ProductMapper;
import com.Ecom.ProductService.Models.Product;
import com.Ecom.ProductService.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public ProductResponseListDTO getAllProducts() {
        List<Product> products = productRepository.findAll();
        return ProductMapper.convertProductsToProductResponseListDTO(products);
    }
    @Override
    public ProductResponseDTO getProductById(String id) {
        return null;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        return null;
    }

    @Override
    public boolean deleteProductById(String id) {
        return true;
    }

    @Override
    public ProductResponseDTO findProductByTitle(String title) throws ProductNotFoundException {
        if(null == title || title.isEmpty()){
            throw new InvalidTitleException("title is invalid");
        }
        Product product = productRepository.findByTitle(title);
        if(product == null){
            throw new ProductNotFoundException("Product with given title is not available");
        }
        return  ProductMapper.convertProductToProductResponseDTO(product);
    }

}
