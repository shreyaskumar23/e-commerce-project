package com.bazinga.au.service;

import com.bazinga.au.model.Products;

public interface ProductService {
	public Iterable<Products> getAllProducts();
	public Products getProduct(int id);
	public Products save(Products product);
}
