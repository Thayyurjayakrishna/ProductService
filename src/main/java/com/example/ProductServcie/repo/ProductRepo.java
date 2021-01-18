package com.example.ProductServcie.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProductServcie.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{

}
