package com.supermarket.application.supermarketapplications;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

}