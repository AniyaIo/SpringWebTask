package com.example.springwebtask;

import com.example.springwebtask.service.IProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringwebtaskApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(SpringwebtaskApplication.class, args);
		var userService = context.getBean(IProductService.class);
	}

}
