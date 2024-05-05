package com.Ecom.ProductService.Service;

import com.Ecom.ProductService.Models.Category;
import com.Ecom.ProductService.Models.Order;
import com.Ecom.ProductService.Models.Price;
import com.Ecom.ProductService.Models.Product;
import com.Ecom.ProductService.Repository.CategoryRepository;
import com.Ecom.ProductService.Repository.OrderRepository;
import com.Ecom.ProductService.Repository.PriceRepository;
import com.Ecom.ProductService.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitServiceImpl implements InitService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final OrderRepository orderRepository;
    private final PriceRepository priceRepository;

    public InitServiceImpl(ProductRepository productRepository,CategoryRepository categoryRepository,
                           OrderRepository orderRepository, PriceRepository priceRepository){
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }
    @Override
    public void initDB() {
        Category category = new Category();
        category.setCategoryName("electronics");
        categoryRepository.save(category);

        Price watchPrice = new Price();
        watchPrice.setAmount(50000);
        watchPrice.setDiscount(0);
        watchPrice.setCurrency("dollars");

        Price mobilePrice = new Price();
        mobilePrice.setAmount(500000);
        mobilePrice.setDiscount(0);
        mobilePrice.setCurrency("dollars");

        priceRepository.save(watchPrice);
        priceRepository.save(mobilePrice);

        Product watch = new Product();
        watch.setCategory(category);
        watch.setDescription("very expensive watch");
        watch.setImage("www.someveryexpensiveWatch.com/");
        watch.setPrice(watchPrice);
        watch.setTitle("Rolex");

        Product mobile = new Product();
        mobile.setCategory(category);
        mobile.setDescription("very expensive mobile");
        mobile.setImage("www.iphone.com/");
        mobile.setPrice(mobilePrice);
        mobile.setTitle("apple");

        productRepository.save(watch);
        productRepository.save(mobile);

        Order order = new Order();
        order.setProductList(List.of(watch,mobile));
        orderRepository.save(order);
    }
}
