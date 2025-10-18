package com.example.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.laptopshop.domain.Product;
import com.example.laptopshop.domain.User;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}