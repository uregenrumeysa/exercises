package com.supermarket.application.supermarketapplications;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
@Data
@Embeddable
public class CartProductId implements Serializable {

    @Column(name = "cart_id")
    private Long cartId;

    @Column(name = "product_id")
    private Long productId;

    private CartProductId() {}

    public CartProductId(
            Long cartId,
            Long productId) {
        this.cartId = cartId;
        this.productId = productId;
    }


}
