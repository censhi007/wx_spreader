package com.sql.sqldialect;

import org.hibernate.Hibernate;
import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;

public class OracleDialect extends Oracle10gDialect {
	public OracleDialect() {
		super();
		registerFunction("bit_and", new SQLFunctionTemplate(Hibernate.LONG,"bitand(?1,?2)"));
		registerFunction("date_add", new SQLFunctionTemplate( Hibernate.DATE, "next_day(?1,?2)" ) );
		registerFunction("as", new AsFunctionOracleTemplate( Hibernate.STRING, "?1" ));
		registerFunction("ifnull", new AsFuctionCommonTemplate( Hibernate.STRING, "NVL(?1, ?2)" ) );
		registerFunction("op_mod", new SQLFunctionTemplate( Hibernate.INTEGER, "mod(?1,?2)" ) );
		registerFunction("sqldate", new SQLFunctionTemplate( Hibernate.DATE, "?1" ) );
		registerFunction("int_to_string", new SQLFunctionTemplate( Hibernate.INTEGER, "?1") );
		registerFunction("str_concat", new SQLFunctionTemplate( Hibernate.STRING, "?1||?2" ) );
		registerFunction("bit_and_convert", new SQLFunctionTemplate(Hibernate.LONG,"bitand(cast(?1 as int) , cast(?2 as int))"));
		registerFunction("dateDiff", new SQLFunctionTemplate( Hibernate.DATE, "?2 - ?3"));	
		registerFunction("op_div", new SQLFunctionTemplate( Hibernate.INTEGER, "TRUNC((?1)/(?2))" ) );
		//registerFunction("to_date", new SQLFunctionTemplate( Hibernate.STRING, "" ) );
		registerFunction("to_int", new SQLFunctionTemplate( Hibernate.INTEGER, "(?1)"));
	}
}