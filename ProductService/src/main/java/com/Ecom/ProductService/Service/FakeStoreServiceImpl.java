package com.Ecom.ProductService.Service;

import com.Ecom.ProductService.Clients.FakeStoreApiClient;
import com.Ecom.ProductService.Dtos.FakeStoreResponseDTO;
import com.Ecom.ProductService.Dtos.ProductRequestDTO;
import com.Ecom.ProductService.Dtos.ProductResponseDTO;
import com.Ecom.ProductService.Dtos.ProductResponseListDTO;
import com.Ecom.ProductService.Exceptions.ProductNotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// importing static means we can call static methods directly with method name instead of className.method()
import static com.Ecom.ProductService.Mapper.ProductMapper.productRequestToFakeStoreRequest;
import static com.Ecom.ProductService.Mapper.ProductMapper.fakeStoreRequestToProductRequest;
import static com.Ecom.ProductService.Utils.ProductUtils.isNull;

@Service("fakeStoreService")
public class FakeStoreServiceImpl implements ProductService{

    private final FakeStoreApiClient fakeStoreApiClient;

    public FakeStoreServiceImpl(FakeStoreApiClient fakeStoreApiClient){
        this.fakeStoreApiClient = fakeStoreApiClient;
    }

    @Override
    public ProductResponseDTO getProductById(String id) throws ProductNotFoundException{
        FakeStoreResponseDTO fakeStoreResponseDTO = fakeStoreApiClient.getProductById(id);
        if(isNull(fakeStoreResponseDTO)){
            throw new ProductNotFoundException("Product not found for the id: "+id);
        }
        return fakeStoreRequestToProductRequest(fakeStoreResponseDTO);
    }

    @Override
    public ProductResponseListDTO getAllProducts() {
        List<FakeStoreResponseDTO> fakeStoreResponseDTOList = fakeStoreApiClient.getAllProducts();
        ProductResponseListDTO productResponseDTOList = new ProductResponseListDTO();
        for(FakeStoreResponseDTO fakeStoreResponseDTO : fakeStoreResponseDTOList){
            productResponseDTOList.getProducts().add(fakeStoreRequestToProductRequest(fakeStoreResponseDTO));
        }
        return productResponseDTOList;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        FakeStoreResponseDTO fakeStoreResponseDTO = fakeStoreApiClient.createProduct(productRequestToFakeStoreRequest(productRequestDTO));
        return fakeStoreRequestToProductRequest(fakeStoreResponseDTO);
    }

    @Override
    public boolean deleteProductById(String id) {
        return fakeStoreApiClient.deleteProductById(id);
    }

    @Override
    public ProductResponseDTO findProductByTitle(String title) throws ProductNotFoundException {
        return null;
    }
}
