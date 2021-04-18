package com.mironov.image.studio.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ extends com.mironov.image.studio.entities.AEntity_ {

	public static volatile SingularAttribute<User, String> firstName;
	public static volatile SingularAttribute<User, String> lastName;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, Long> phone;
	public static volatile ListAttribute<User, MasterService> masterServices;
	public static volatile ListAttribute<User, Role> roles;
	public static volatile ListAttribute<User, Schedule> schedules;
	public static volatile SingularAttribute<User, Description> description;
	public static volatile ListAttribute<User, Order> orders;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> username;
	public static volatile ListAttribute<User, Order> order;

	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String PASSWORD = "password";
	public static final String PHONE = "phone";
	public static final String MASTER_SERVICES = "masterServices";
	public static final String ROLES = "roles";
	public static final String SCHEDULES = "schedules";
	public static final String DESCRIPTION = "description";
	public static final String ORDERS = "orders";
	public static final String EMAIL = "email";
	public static final String USERNAME = "username";
	public static final String ORDER = "order";

}

