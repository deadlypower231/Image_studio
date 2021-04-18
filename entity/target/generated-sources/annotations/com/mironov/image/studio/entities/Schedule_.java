package com.mironov.image.studio.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Schedule.class)
public abstract class Schedule_ extends com.mironov.image.studio.entities.AEntity_ {

	public static volatile SingularAttribute<Schedule, String> time;
	public static volatile SingularAttribute<Schedule, Tournament> tournament;
	public static volatile ListAttribute<Schedule, Order> order;

	public static final String TIME = "time";
	public static final String TOURNAMENT = "tournament";
	public static final String ORDER = "order";

}

