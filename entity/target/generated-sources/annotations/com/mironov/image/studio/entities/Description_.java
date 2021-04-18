package com.mironov.image.studio.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Description.class)
public abstract class Description_ extends com.mironov.image.studio.entities.AEntity_ {

	public static volatile SingularAttribute<Description, String> shortDescription;
	public static volatile SingularAttribute<Description, Tournament> tournament;
	public static volatile SingularAttribute<Description, String> fullDescription;
	public static volatile SingularAttribute<Description, User> user;

	public static final String SHORT_DESCRIPTION = "shortDescription";
	public static final String TOURNAMENT = "tournament";
	public static final String FULL_DESCRIPTION = "fullDescription";
	public static final String USER = "user";

}

