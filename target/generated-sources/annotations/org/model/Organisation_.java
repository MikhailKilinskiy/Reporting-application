package org.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Organisation.class)
public abstract class Organisation_ {

	public static volatile SingularAttribute<Organisation, OrganisationType> Type;
	public static volatile SingularAttribute<Organisation, Long> Version;
	public static volatile SingularAttribute<Organisation, String> FullName;
	public static volatile SingularAttribute<Organisation, String> Region;
	public static volatile SingularAttribute<Organisation, String> ShortName;
	public static volatile SingularAttribute<Organisation, Long> Id;
	public static volatile SingularAttribute<Organisation, Date> lastModified;

	public static final String TYPE = "Type";
	public static final String VERSION = "Version";
	public static final String FULL_NAME = "FullName";
	public static final String REGION = "Region";
	public static final String SHORT_NAME = "ShortName";
	public static final String ID = "Id";
	public static final String LAST_MODIFIED = "lastModified";

}

