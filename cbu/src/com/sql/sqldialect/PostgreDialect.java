package com.sql.sqldialect;

import org.hibernate.Hibernate;
import org.hibernate.dialect.PostgreSQLDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;

public class PostgreDialect extends PostgreSQLDialect {
	public PostgreDialect() {
		super();
		registerFunction("str_concat", new SQLFunctionTemplate( Hibernate.STRING, "?1||?2" ) );
		registerFunction("bit_and", new SQLFunctionTemplate(Hibernate.LONG,"(?1 & ?2)"));
		registerFunction("bit_and_convert", new SQLFunctionTemplate(Hibernate.LONG,"(?1::bigint & ?2::bigint)"));
		registerFunction("as", new SQLFunctionTemplate( Hibernate.STRING, "?1 " ) );
		registerFunction("int_to_string", new SQLFunctionTemplate( Hibernate.INTEGER, "cast(?1 as text)" ) );
		registerFunction("sqldate", new SQLFunctionTemplate( Hibernate.DATE, "cast(?1 as date)" ) );
		registerFunction("date_add", new SQLFunctionTemplate( Hibernate.STRING, "(?1 + ?2)" ) );
		registerFunction("op_div", new SQLFunctionTemplate( Hibernate.INTEGER, "(?1)::int / (?2)::int" ) );
		registerFunction("op_mod", new SQLFunctionTemplate( Hibernate.INTEGER, "mod(?1::int,?2 ::int)" ) );
		registerFunction("locate", new SQLFunctionTemplate( Hibernate.STRING, "position(?1 in ?2)" ) );
		registerFunction("date_diff", new SQLFunctionTemplate( Hibernate.INTEGER, "?2 - ?3"));		
		registerFunction("to_int", new SQLFunctionTemplate( Hibernate.INTEGER, "(?1 :: int)"));
	}
}
