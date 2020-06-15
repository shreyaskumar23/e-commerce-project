package com.bazinga.au.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bazinga.au.exception.ResourceNotFoundException;
import com.bazinga.au.model.Products;
import com.bazinga.au.repository.ProductsRepository;
import com.bazinga.au.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductsRepository productsRepository;
	
	@Override
	public List<Products> getAllProducts() {
		return productsRepository.findAll();
	}

	@Override
	public Products getProduct(int id) {
		return productsRepository
		          .findById(id)
		          .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
	}

	@Override
	public Products save(Products product) {
		return productsRepository.save(product);
	}

}
