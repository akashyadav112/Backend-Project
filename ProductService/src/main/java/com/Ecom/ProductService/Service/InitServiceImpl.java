package com.Ecom.ProductService.Service;

import com.Ecom.ProductService.Demo.Author;
import com.Ecom.ProductService.Demo.AuthorRepository;
import com.Ecom.ProductService.Demo.Book;
import com.Ecom.ProductService.Demo.BookRepository;
import com.Ecom.ProductService.Models.Category;
import com.Ecom.ProductService.Models.Order;
import com.Ecom.ProductService.Models.Price;
import com.Ecom.ProductService.Models.Product;
import com.Ecom.ProductService.Repository.CategoryRepository;
import com.Ecom.ProductService.Repository.OrderRepository;
import com.Ecom.ProductService.Repository.PriceRepository;
import com.Ecom.ProductService.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitServiceImpl implements InitService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final OrderRepository orderRepository;
    private final PriceRepository priceRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;


    public InitServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
                           OrderRepository orderRepository, PriceRepository priceRepository, AuthorRepository authorRepository, BookRepository bookRepository){
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
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

        /* will understand the combo cascade + fetchType */
        Author author = new Author("John_Green",null);
        Book book1 = new Book("The fault in our stars",author);
        Book book2 = new Book("Paper Town",author);

       author.setBooks(List.of(book1,book2));
       authorRepository.save(author);

        // logic code
       Author presentAuthor = authorRepository.findById(1).get();
       List<Book> books = presentAuthor.getBooks();
       System.out.println(books);

        /* Case 1: fetchType not set(by default it is lazy) and caseCadeType.ALL */

        /* showing Lazy initialise Exception as by default it is lazy loading
           and when lazy loading child class should be saved first and then author in order to access it
        * */

        /*Case 2: fetchType is eager & caseCadeType.ALL */

        // everything will work fine as in Eagar loading all dependent classes will be also loaded

        /*Case 3: fetchType is lazy & caseCadeType.ALL + @transactional annotation */
            /* when we add transactional, instead of all queries being singly dependent, all queires in transaction
               get dependent in one transaction only so all queries have to be executed like in eagar type so till will give
               same result like in eager case..

               Q) no need of lazy + transactional ??
            * */
    }
}
