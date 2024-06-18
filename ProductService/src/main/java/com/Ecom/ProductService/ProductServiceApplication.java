package com.Ecom.ProductService;

import com.Ecom.ProductService.Service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {

	@Autowired
	private InitService initService;
	public static void main(String[] args)  {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//initService.initDB();
	}
}
