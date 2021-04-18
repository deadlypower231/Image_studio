package com.mironov.image.studio.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MasterService.class)
public abstract class MasterService_ extends com.mironov.image.studio.entities.AEntity_ {

	public static volatile SingularAttribute<MasterService, Double> price;
	public static volatile SingularAttribute<MasterService, String> name;
	public static volatile SingularAttribute<MasterService, User> user;
	public static volatile ListAttribute<MasterService, Order> order;

	public static final String PRICE = "price";
	public static final String NAME = "name";
	public static final String USER = "user";
	public static final String ORDER = "order";

}

