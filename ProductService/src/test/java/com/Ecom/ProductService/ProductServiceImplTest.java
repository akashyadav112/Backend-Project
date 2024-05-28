package com.Ecom.ProductService;

import com.Ecom.ProductService.Dtos.ProductResponseDTO;
import com.Ecom.ProductService.Exceptions.InvalidTitleException;
import com.Ecom.ProductService.Exceptions.ProductNotFoundException;
import com.Ecom.ProductService.Models.Category;
import com.Ecom.ProductService.Models.Price;
import com.Ecom.ProductService.Models.Product;
import com.Ecom.ProductService.Repository.ProductRepository;
import com.Ecom.ProductService.Service.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.Mockito.when;

public class ProductServiceImplTest {
    @Mock // we need a mock object of the given attribute
    private ProductRepository productRepository;
    @InjectMocks // this is the class we want to test, and this is where we would inject the mock objects
    private ProductServiceImpl productService;

    @BeforeEach
    public void setup(){
        System.out.println("HelloWorld, from BeforeEach");
        MockitoAnnotations.openMocks(this); // creates auto closeable resources for each test method
    }

    @Test
    public void testFindProductByTitleSuccess() throws ProductNotFoundException {
        //Arrange
        Price mockPrice = new Price();
        mockPrice.setAmount(100);

        Category mockCategory = new Category();
        mockCategory.setCategoryName("mockCategory");

        String testTitle = "testProductTitle";
        Product mockProduct = new Product();
        mockProduct.setId(UUID.randomUUID());
        mockProduct.setTitle(testTitle);
        mockProduct.setDescription("testDescription");
        mockProduct.setPrice(mockPrice);
        mockProduct.setCategory(mockCategory);
        when(productRepository.findByTitle(testTitle)).thenReturn(mockProduct);
        //Act
        ProductResponseDTO actualResponse = productService.findProductByTitle(testTitle);
        //Assert -> below line tells what we are expecting, vs what we got
        Assertions.assertEquals(actualResponse.getTitle(), mockProduct.getTitle());
        Assertions.assertEquals(actualResponse.getDescription(), mockProduct.getDescription());
        Assertions.assertEquals(actualResponse.getId(), mockProduct.getId());
        Assertions.assertEquals(actualResponse.getPrice(), mockProduct.getPrice().getAmount());
    }

    @Test
    public void testFindByProductByTitle_RepoRespondsWithNullObject() throws ProductNotFoundException {
        //Arrange
        String testTitle = "testProductTitle";
        when(productRepository.findByTitle(testTitle)).thenReturn(null);
        // below line tells what we are expecting, vs what we got
        Assertions.assertThrows(ProductNotFoundException.class, () -> productService.findProductByTitle(testTitle));
    }

    @Test
    public void testFindProductByTitle_NullTitle() throws ProductNotFoundException {
        //Arrange
        Price mockPrice = new Price();
        mockPrice.setAmount(100);

        Category mockCategory = new Category();
        mockCategory.setCategoryName("mockCategory");

        String testTitle = null;
        Product mockProduct = new Product();
        mockProduct.setId(UUID.randomUUID());
        mockProduct.setTitle(testTitle);
        mockProduct.setDescription("testDescription");
        mockProduct.setPrice(mockPrice);
        mockProduct.setCategory(mockCategory);
        when(productRepository.findByTitle(testTitle)).thenReturn(mockProduct);
        //Assert and Act
        Assertions.assertThrows(InvalidTitleException.class, () -> productService.findProductByTitle(testTitle) );
    }

    @Test
    public void testFindProductByTitle_EmptyTitle() throws ProductNotFoundException {
        //Arrange
        Price mockPrice = new Price();
        mockPrice.setAmount(100);

        Category mockCategory = new Category();
        mockCategory.setCategoryName("mockCategory");

        String testTitle = "";
        Product mockProduct = new Product();
        mockProduct.setId(UUID.randomUUID());
        mockProduct.setTitle(testTitle);
        mockProduct.setDescription("testDescription");
        mockProduct.setPrice(mockPrice);
        mockProduct.setCategory(mockCategory);
        when(productRepository.findByTitle(testTitle)).thenReturn(mockProduct);
        //Assert and Act -> below line tells what we are expecting, vs what we got
        Assertions.assertThrows(InvalidTitleException.class, () -> productService.findProductByTitle(testTitle) );
    }
    /**
     *
     * Task ->
     * Connect to DB
     * Get the system up and running
     * Check all APIs for User and Roles are working*/

}
