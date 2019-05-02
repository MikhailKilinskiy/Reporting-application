package org.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Customer.class)
public abstract class Customer_ extends org.model.Organisation_ {

	public static volatile SingularAttribute<Customer, Date> ContractDate;
	public static volatile SingularAttribute<Customer, String> ContractName;

	public static final String CONTRACT_DATE = "ContractDate";
	public static final String CONTRACT_NAME = "ContractName";

}

