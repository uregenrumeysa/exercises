package com.supermarket.application.supermarketapplications;

import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
@Entity(name="Cart")
@Table(name="cart")
@NaturalIdCache
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Cart {

    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    private Long product_id;


    @OneToMany(
            mappedBy = "cart",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CartProduct> products = new ArrayList<>();
    public Cart() {
    }

    public Cart(Long product_id) {
        this.product_id = product_id;
    }

    public void addProduct(Product product) {
        CartProduct cartProduct = new CartProduct(product, this);
        products.add(cartProduct);
        product.getCarts().add(cartProduct);
    }

    public void removeProduct(Product product) {
        for (Iterator<CartProduct> iterator = products.iterator();
             iterator.hasNext(); ) {
            CartProduct cartProduct = iterator.next();

            if (cartProduct.getCart().equals(this) &&
                    cartProduct.getProduct().equals(product)) {
                iterator.remove();
                cartProduct.getCart().getProducts().remove(cartProduct);
                cartProduct.setProduct(null);
                cartProduct.setCart(null);
            }
        }
    }


}
