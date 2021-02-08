package model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
public class ShoppingCart {
    @Id
    Long id;
    @ManyToMany
    List<Product> productList;
    @ManyToOne
    User user;
    @Enumerated(EnumType.STRING)
    ShoppingCartStatus status;

    public ShoppingCart() {
    }

    public ShoppingCart(List<Product> productList, User user) {
        this.productList = productList;
        this.user = user;
    }
}
