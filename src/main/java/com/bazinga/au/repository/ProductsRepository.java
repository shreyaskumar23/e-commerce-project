package com.bazinga.au.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bazinga.au.model.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer>{

}
