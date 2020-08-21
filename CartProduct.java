package com.supermarket.application.supermarketapplications;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name="CartProduct")
@Table(name="cart_product")
public class CartProduct{
    @EmbeddedId
    private CartProductId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cartId")
    private Cart cart;

    @Column(name = "quantity")
    private long quantity;

    private CartProduct() {}

    public CartProduct(Product product, Cart cart) {
        this.product= product;
        this.cart= cart;
        this.id = new CartProductId(product.getId(), cart.getId());
    }


}
