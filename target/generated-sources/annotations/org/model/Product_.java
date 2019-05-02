package org.model;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ {

	public static volatile SingularAttribute<Product, BigDecimal> Price;
	public static volatile SingularAttribute<Product, String> name;
	public static volatile SingularAttribute<Product, Integer> Quantity;
	public static volatile SingularAttribute<Product, Long> Id;
	public static volatile SingularAttribute<Product, Order> order;

	public static final String PRICE = "Price";
	public static final String NAME = "name";
	public static final String QUANTITY = "Quantity";
	public static final String ID = "Id";
	public static final String ORDER = "order";

}

