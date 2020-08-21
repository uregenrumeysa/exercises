package com.supermarket.application.supermarketapplications;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
@Entity(name="Product")
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Double prize;
// insert tuşuna basar mısın insert tunerede ya :( f12 yanında ben burdan basınca oldu neyse
    @OneToMany(
            mappedBy = "product", // koyalarken postu burda unutmuşşsun :)
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CartProduct> carts = new ArrayList<>();

    public Product(String name, Double prize) {
        this.name = name;
        this.prize = prize;
    }



}
