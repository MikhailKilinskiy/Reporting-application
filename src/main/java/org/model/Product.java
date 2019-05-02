package org.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long Id;

    public long getId() {
        return Id;
    }

    @ManyToOne(
            targetEntity = Order.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(foreignKey = @ForeignKey(name = "OrderId"), nullable = false)
    protected Order order;

    protected String name;

    protected int Quantity;

    protected BigDecimal Price;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal price) {
        Price = price;
    }

    public Product(long id, Order order, String name, int quantity, BigDecimal price) {
        Id = id;
        this.order = order;
        this.name = name;
        Quantity = quantity;
        Price = price;
    }

    public Product() {
    }
}
