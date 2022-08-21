package com.interview.american_express.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interview.american_express.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
