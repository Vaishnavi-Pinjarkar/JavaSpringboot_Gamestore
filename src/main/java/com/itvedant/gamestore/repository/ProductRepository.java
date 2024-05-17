package com.itvedant.gamestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itvedant.gamestore.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
