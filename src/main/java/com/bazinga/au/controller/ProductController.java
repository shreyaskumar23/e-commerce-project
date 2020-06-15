package com.bazinga.au.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bazinga.au.model.Products;
import com.bazinga.au.serviceImpl.ProductServiceImpl;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	@Autowired
	ProductServiceImpl productServices;

	@GetMapping("/getAll")
	public List<Products> getAllProducts() {
		return productServices.getAllProducts();
	}
}
