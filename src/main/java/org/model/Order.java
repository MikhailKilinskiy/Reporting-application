package org.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long Id;

    public long getId() {
        return Id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    protected Date OrderDate;

    @ManyToOne(
            targetEntity = Customer.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
            )
    @JoinColumn(foreignKey = @ForeignKey(name = "CustomerId"), nullable = false)
    protected Customer customer;

    @ManyToOne(
            targetEntity = Supplier.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(foreignKey = @ForeignKey(name = "SupplierId"), nullable = false)
    protected Supplier supplier;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            targetEntity = Product.class
    )
    protected Set products = new HashSet<Product>();

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date orderDate) {
        OrderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Set getProducts() {
        return products;
    }

    public void setProducts(Set products) {
        this.products = products;
    }

    public Order(Date orderDate, Customer customer, Supplier supplier, Set products) {
        OrderDate = orderDate;
        this.customer = customer;
        this.supplier = supplier;
        this.products = products;
    }

    public Order() {
    }
}
