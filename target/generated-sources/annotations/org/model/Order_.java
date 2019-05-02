package org.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Order.class)
public abstract class Order_ {

	public static volatile SingularAttribute<Order, Supplier> supplier;
	public static volatile SingularAttribute<Order, Long> Id;
	public static volatile SingularAttribute<Order, Date> OrderDate;
	public static volatile SingularAttribute<Order, Customer> customer;
	public static volatile SetAttribute<Order, Product> products;

	public static final String SUPPLIER = "supplier";
	public static final String ID = "Id";
	public static final String ORDER_DATE = "OrderDate";
	public static final String CUSTOMER = "customer";
	public static final String PRODUCTS = "products";

}

