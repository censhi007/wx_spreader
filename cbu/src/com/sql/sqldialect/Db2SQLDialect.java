package com.sql.sqldialect;

import org.hibernate.Hibernate;
import org.hibernate.dialect.DB2Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;

public class Db2SQLDialect extends DB2Dialect {
	public Db2SQLDialect() {
		super();
		registerFunction("str_concat", new SQLFunctionTemplate( Hibernate.STRING, "?1||?2" ) );
		registerFunction("bit_and", new SQLFunctionTemplate(Hibernate.LONG,"BITAND(?1 , ?2)"));
		registerFunction("bit_and_convert", new SQLFunctionTemplate(Hibernate.LONG,"BITAND(cast(?1 as int) , cast(?2 as int))"));
		registerFunction("as", new AsFuctionCommonTemplate( Hibernate.STRING, "?1 " ) );
		registerFunction("int_to_string", new SQLFunctionTemplate( Hibernate.INTEGER, "?1") );
		registerFunction("sqldate", new SQLFunctionTemplate( Hibernate.DATE, "cast(?1 as date)" ) );
		registerFunction("date_add", new SQLFunctionTemplate( Hibernate.STRING, "(?1 + ?2 day)" ) );
		registerFunction("op_div", new SQLFunctionTemplate( Hibernate.INTEGER, "(?1)/(?2)" ) );
		registerFunction("op_mod", new SQLFunctionTemplate( Hibernate.INTEGER, "mod((?1),(?2))" ) );
		registerFunction("dateDiff", new SQLFunctionTemplate( Hibernate.DATE, "?2 - ?3"));
		registerFunction("to_int", new SQLFunctionTemplate( Hibernate.INTEGER, "(?1)"));
	}
}
