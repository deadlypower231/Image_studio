package com.mironov.image.studio.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Order.class)
public abstract class Order_ extends com.mironov.image.studio.entities.AEntity_ {

	public static volatile SingularAttribute<Order, Schedule> schedule;
	public static volatile SingularAttribute<Order, Double> price;
	public static volatile SingularAttribute<Order, String> submitDate;
	public static volatile SingularAttribute<Order, Tournament> tournament;
	public static volatile SingularAttribute<Order, MasterService> masterService;
	public static volatile SingularAttribute<Order, User> master;

	public static final String SCHEDULE = "schedule";
	public static final String PRICE = "price";
	public static final String SUBMIT_DATE = "submitDate";
	public static final String TOURNAMENT = "tournament";
	public static final String MASTER_SERVICE = "masterService";
	public static final String MASTER = "master";

}

