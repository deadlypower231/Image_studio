package com.mironov.image.studio.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tournament.class)
public abstract class Tournament_ extends com.mironov.image.studio.entities.AEntity_ {

	public static volatile SingularAttribute<Tournament, String> date;
	public static volatile ListAttribute<Tournament, Schedule> schedule;
	public static volatile SingularAttribute<Tournament, String> address;
	public static volatile SingularAttribute<Tournament, String> name;
	public static volatile SingularAttribute<Tournament, Description> description;
	public static volatile ListAttribute<Tournament, User> users;
	public static volatile ListAttribute<Tournament, Order> order;

	public static final String DATE = "date";
	public static final String SCHEDULE = "schedule";
	public static final String ADDRESS = "address";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String USERS = "users";
	public static final String ORDER = "order";

}

