package model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToMany
    List<Product> productList;
    @ManyToOne
    User user;
    @Enumerated
    Status status;

    public Order() {
    }

    public Order(List<Product> productList, User user, Status status) {
        this.productList = productList;
        this.user = user;
        this.status = status;
    }
}
