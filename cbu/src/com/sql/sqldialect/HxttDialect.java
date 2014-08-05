package com.sql.sqldialect;

import org.hibernate.Hibernate;
import org.hibernate.MappingException;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.NoArgSQLFunction;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.dialect.function.VarArgsSQLFunction;

/**
 * An common SQL  dialect for HXTT JDBC drivers.
 * Written according to Jboss' dialect samples.
 */
public abstract class HxttDialect extends Dialect {
    
    static final String DEFAULT_BATCH_SIZE = "15";
    static final String NO_BATCH = "0";
    
    
    public HxttDialect() {
        super();
        //Mathematical Functions
        registerFunction("abs", new StandardSQLFunction("abs") );
        registerFunction("ceiling", new StandardSQLFunction("ceiling", Hibernate.INTEGER) );
        registerFunction("ceil", new StandardSQLFunction("ceil", Hibernate.INTEGER) );
        registerFunction("sign", new StandardSQLFunction("sign", Hibernate.INTEGER) );
        registerFunction("degrees", new StandardSQLFunction("degrees", Hibernate.DOUBLE) );
        registerFunction("exp", new StandardSQLFunction("exp", Hibernate.DOUBLE) );
        registerFunction("floor", new StandardSQLFunction("floor", Hibernate.INTEGER) );
        registerFunction("int", new StandardSQLFunction("int", Hibernate.INTEGER) );
        registerFunction("log", new StandardSQLFunction("log", Hibernate.DOUBLE) );
        registerFunction("log10", new StandardSQLFunction("log10", Hibernate.DOUBLE) );
        registerFunction("log2", new StandardSQLFunction("log2", Hibernate.DOUBLE) );
        registerFunction("ln", new StandardSQLFunction("ln", Hibernate.DOUBLE) );
        registerFunction("mod", new StandardSQLFunction("mod", Hibernate.INTEGER) );
        registerFunction("pi", new NoArgSQLFunction("pi", Hibernate.DOUBLE) );
        registerFunction("pow", new StandardSQLFunction("pow", Hibernate.DOUBLE) );
        registerFunction("power", new StandardSQLFunction("power", Hibernate.DOUBLE) );
        registerFunction("padians", new StandardSQLFunction("padians", Hibernate.DOUBLE) );
        registerFunction("radians", new StandardSQLFunction("radians", Hibernate.DOUBLE) );
        registerFunction("rand", new NoArgSQLFunction("rand", Hibernate.DOUBLE) );
        registerFunction("round", new StandardSQLFunction("round", Hibernate.INTEGER) );
        registerFunction("sign", new StandardSQLFunction("sign", Hibernate.INTEGER) );
        registerFunction("sqrt", new StandardSQLFunction("sqrt", Hibernate.DOUBLE) );
        registerFunction("trunc", new StandardSQLFunction("trunc", Hibernate.DOUBLE) );
        registerFunction("truncate", new StandardSQLFunction("truncate", Hibernate.DOUBLE) );
        //Trigonometric Functions
        registerFunction("acos", new StandardSQLFunction("acos", Hibernate.DOUBLE) );
        registerFunction("asin", new StandardSQLFunction("asin", Hibernate.DOUBLE) );
        registerFunction("atan", new StandardSQLFunction("atan", Hibernate.DOUBLE) );
        registerFunction("atan2", new StandardSQLFunction("atan2", Hibernate.DOUBLE) );
        registerFunction("cos", new StandardSQLFunction("cos", Hibernate.DOUBLE) );
        registerFunction("cot", new StandardSQLFunction("cot", Hibernate.DOUBLE) );
        registerFunction("crc32", new StandardSQLFunction("crc32", Hibernate.LONG) );
        registerFunction("sin", new StandardSQLFunction("sin", Hibernate.DOUBLE) );
        registerFunction("tan", new StandardSQLFunction("tan", Hibernate.DOUBLE) );
        
//String Functions
        registerFunction("alltrim", new StandardSQLFunction("alltrim") );
        registerFunction("asc", new StandardSQLFunction("asc", Hibernate.INTEGER) );
        registerFunction("ascii", new StandardSQLFunction("ascii", Hibernate.INTEGER) );
        registerFunction("at", new StandardSQLFunction("at", Hibernate.INTEGER) );
        registerFunction("bin", new StandardSQLFunction("bin", Hibernate.STRING) );
        registerFunction("bit_length", new StandardSQLFunction("bit_length", Hibernate.LONG) );
        registerFunction("char_length", new StandardSQLFunction("char_length", Hibernate.LONG) );
        registerFunction("character_length", new StandardSQLFunction("character_length", Hibernate.LONG) );
        registerFunction("char", new StandardSQLFunction("char", Hibernate.STRING) );
        registerFunction("chr", new StandardSQLFunction("char", Hibernate.STRING) );
        registerFunction("chrtran", new StandardSQLFunction("chrtran", Hibernate.STRING) );
        registerFunction( "concat", new VarArgsSQLFunction(Hibernate.STRING, "", "||", "") );
        registerFunction( "concat_ws", new VarArgsSQLFunction(Hibernate.STRING, "", "||", "") );
        registerFunction( "conv", new StandardSQLFunction("conv", Hibernate.STRING) );
        registerFunction( "difference", new StandardSQLFunction("difference", Hibernate.STRING) );
        registerFunction("hex", new StandardSQLFunction("hex", Hibernate.STRING) );
        registerFunction("initcap", new StandardSQLFunction("initcap") );
//	registerFunction("insert", new StandardSQLFunction("insert") );
        registerFunction("instr", new StandardSQLFunction("instr", Hibernate.INTEGER) );
        registerFunction("lcase", new StandardSQLFunction("lcase") );
        registerFunction("left", new StandardSQLFunction("left", Hibernate.INTEGER) );
        registerFunction("len", new StandardSQLFunction("len", Hibernate.LONG) );
        registerFunction("length", new StandardSQLFunction("length", Hibernate.LONG) );
        registerFunction("locate", new StandardSQLFunction("locate", Hibernate.LONG) );
        registerFunction("lower", new StandardSQLFunction("lower") );
        registerFunction("lpad", new StandardSQLFunction("lpad", Hibernate.STRING) );
        registerFunction("ltrim", new StandardSQLFunction("ltrim") );
        registerFunction("mid", new StandardSQLFunction("mid", Hibernate.STRING) );
        registerFunction("oct", new StandardSQLFunction("oct", Hibernate.STRING) );
        registerFunction("octet_length", new StandardSQLFunction("octet_length", Hibernate.LONG) );
        registerFunction("padc", new StandardSQLFunction("padc", Hibernate.STRING) );
        registerFunction("padl", new StandardSQLFunction("padl", Hibernate.STRING) );
        registerFunction("padr", new StandardSQLFunction("padr", Hibernate.STRING) );
        registerFunction("position", new StandardSQLFunction("position", Hibernate.INTEGER) );
        registerFunction("proper", new StandardSQLFunction("proper") )	;
        registerFunction("repeat", new StandardSQLFunction("repeat", Hibernate.STRING) );
        registerFunction("replicate", new StandardSQLFunction("replicate", Hibernate.STRING) );
        registerFunction("replace", new StandardSQLFunction("replace", Hibernate.STRING) );
        registerFunction("right", new StandardSQLFunction("right", Hibernate.INTEGER) );
        registerFunction("rpad", new StandardSQLFunction("rpad", Hibernate.STRING) );
        registerFunction("rtrim", new StandardSQLFunction("rtrim") );
        registerFunction("soundex", new StandardSQLFunction("soundex") );
        registerFunction("space", new StandardSQLFunction("space", Hibernate.STRING) );
        registerFunction( "strcat", new VarArgsSQLFunction(Hibernate.STRING, "", "||", "") );
        registerFunction("strcmp", new StandardSQLFunction("strcmp", Hibernate.INTEGER) );
        registerFunction("strconv", new StandardSQLFunction("strconv", Hibernate.STRING) );
        registerFunction("strtran", new StandardSQLFunction("strtran", Hibernate.STRING) );
        registerFunction("stuff", new StandardSQLFunction("stuff", Hibernate.STRING) );
        registerFunction("substr", new StandardSQLFunction("stuff", Hibernate.STRING) );
        registerFunction("substring", new StandardSQLFunction("substring", Hibernate.STRING) );
        registerFunction("translate", new StandardSQLFunction("translate", Hibernate.STRING) );
        registerFunction("trim", new StandardSQLFunction("trim") );
        registerFunction("ucase", new StandardSQLFunction("ucase") );
        registerFunction("upper", new StandardSQLFunction("upper") );
        registerFunction("charmirr", new StandardSQLFunction("charmirr") );
        registerFunction("reverse", new StandardSQLFunction("reverse") );
        
        //Date/Time Functions
        registerFunction("addtime",new StandardSQLFunction("addtime",Hibernate.TIMESTAMP));
        registerFunction("cdow",new StandardSQLFunction("cdow",Hibernate.STRING));
        registerFunction("cmonth",new StandardSQLFunction("cmonth",Hibernate.STRING));
        registerFunction("curdate", new NoArgSQLFunction("curdate", Hibernate.DATE) );
        registerFunction("curtime", new NoArgSQLFunction("curtime", Hibernate.TIME) );
        registerFunction("date", new StandardSQLFunction("date", Hibernate.DATE) );
        registerFunction("datediff", new StandardSQLFunction("datediff", Hibernate.INTEGER) );
        registerFunction("datetime",new  NoArgSQLFunction("datetime",Hibernate.TIMESTAMP));
        registerFunction("date_add",new  StandardSQLFunction("date_add",Hibernate.DATE));
        registerFunction("date_sub",new  StandardSQLFunction("date_sub",Hibernate.DATE));
        registerFunction("adddate",new  StandardSQLFunction("adddate",Hibernate.DATE));
        registerFunction("subdate",new  StandardSQLFunction("subdate",Hibernate.DATE));
        registerFunction("day", new StandardSQLFunction("day", Hibernate.INTEGER) );
        registerFunction("dayofmonth", new StandardSQLFunction("dayofmonth", Hibernate.INTEGER) );
        registerFunction("dayname", new StandardSQLFunction("dayname", Hibernate.STRING) );
        registerFunction("dayofweek", new StandardSQLFunction("dayofweek", Hibernate.INTEGER) );
        registerFunction("dayofyear", new StandardSQLFunction("dayofyear", Hibernate.INTEGER) );
        registerFunction("extract",new  StandardSQLFunction("extract",Hibernate.INTEGER));
        registerFunction("dow",new StandardSQLFunction("dow",Hibernate.STRING));
        registerFunction("from_days", new StandardSQLFunction("from_days", Hibernate.DATE) );
        registerFunction("gomonth", new StandardSQLFunction("gomonth", Hibernate.DATE) );
        registerFunction("hour", new StandardSQLFunction("hour", Hibernate.INTEGER) );
        registerFunction("last_day", new StandardSQLFunction("last_day", Hibernate.DATE) );
        registerFunction("minute",new  StandardSQLFunction("minute",Hibernate.INTEGER));
        registerFunction("millisecond",new  StandardSQLFunction("millisecond",Hibernate.INTEGER));
        registerFunction("microsecond",new  StandardSQLFunction("microsecond",Hibernate.INTEGER));
        registerFunction("month",new  StandardSQLFunction("month",Hibernate.INTEGER));
        registerFunction("monthname",new StandardSQLFunction("monthname",Hibernate.STRING));
        registerFunction("now", new NoArgSQLFunction("now", Hibernate.TIMESTAMP) );
        registerFunction("quarter", new StandardSQLFunction("quarter", Hibernate.INTEGER) );
        registerFunction("second", new StandardSQLFunction("second", Hibernate.INTEGER) );
        registerFunction("sub_time", new NoArgSQLFunction("sub_time", Hibernate.TIMESTAMP) );
        registerFunction("sysdate", new NoArgSQLFunction("sysdate", Hibernate.TIMESTAMP) );
        registerFunction("time", new StandardSQLFunction("time", Hibernate.TIME) );
        registerFunction("timediff", new StandardSQLFunction("timediff", Hibernate.TIME) );
        registerFunction("timestamp", new StandardSQLFunction("timestamp", Hibernate.TIMESTAMP) );
        registerFunction("timestampadd", new StandardSQLFunction("timestampadd", Hibernate.TIMESTAMP) );
        registerFunction("timestampdiff", new StandardSQLFunction("timestampdiff", Hibernate.INTEGER) );
        registerFunction("to_days", new StandardSQLFunction("to_days", Hibernate.INTEGER) );
        registerFunction("week", new StandardSQLFunction("week", Hibernate.INTEGER) );
        registerFunction("weekofyear", new StandardSQLFunction("weekofyear", Hibernate.INTEGER) );
        registerFunction("year", new StandardSQLFunction("year", Hibernate.INTEGER) );
        //boolean functions
        registerFunction("empty", new StandardSQLFunction("empty", Hibernate.BOOLEAN) );
        registerFunction("isblank", new StandardSQLFunction("isblank", Hibernate.BOOLEAN) );
        registerFunction("isalpha", new StandardSQLFunction("isalpha", Hibernate.BOOLEAN) );
        registerFunction("isdigit", new StandardSQLFunction("isdigit", Hibernate.BOOLEAN) );
        registerFunction("isnull", new StandardSQLFunction("isnull", Hibernate.BOOLEAN) );
        //Conversion Functions
        registerFunction("cbool", new StandardSQLFunction("cbool", Hibernate.BOOLEAN) );
        registerFunction("cbyte", new StandardSQLFunction("cbyte", Hibernate.BYTE) );
        registerFunction("cdate", new StandardSQLFunction("cdate", Hibernate.DATE) );
        registerFunction("cdbl", new StandardSQLFunction("cdbl", Hibernate.DOUBLE) );
        registerFunction("cint", new StandardSQLFunction("cint", Hibernate.INTEGER) );
        registerFunction("clng", new StandardSQLFunction("clng", Hibernate.LONG) );
        registerFunction("csng", new StandardSQLFunction("csng", Hibernate.FLOAT) );
        registerFunction("cstr", new StandardSQLFunction("cstr", Hibernate.STRING) );
        registerFunction("ctod", new StandardSQLFunction("ctod", Hibernate.DATE) );
        registerFunction("ctot", new StandardSQLFunction("ctot", Hibernate.TIMESTAMP) );
        registerFunction("dtoc", new StandardSQLFunction("dtoc", Hibernate.STRING) );
        registerFunction("dtot", new StandardSQLFunction("dtot", Hibernate.TIMESTAMP) );
        registerFunction("ttoc", new StandardSQLFunction("ttoc", Hibernate.STRING) );
        registerFunction("ttod", new StandardSQLFunction("ttod", Hibernate.DATE) );
        //Security Functions
        registerFunction("compress", new StandardSQLFunction("compress", Hibernate.STRING) );
        registerFunction("uncompress", new StandardSQLFunction("uncompress", Hibernate.STRING) );
        registerFunction("encrypt", new StandardSQLFunction("encrypt", Hibernate.STRING) );
        registerFunction("decrypt", new StandardSQLFunction("decrypt", Hibernate.STRING) );
        registerFunction("encode", new StandardSQLFunction("encode", Hibernate.STRING) );
        registerFunction("decode", new StandardSQLFunction("decode", Hibernate.STRING) );
        registerFunction("md5", new StandardSQLFunction("md5", Hibernate.STRING) );
        registerFunction("crypt3", new StandardSQLFunction("crypt3", Hibernate.STRING) );
        //System Functions
        registerFunction( "database", new NoArgSQLFunction("database", Hibernate.STRING, false) );
        registerFunction( "user", new NoArgSQLFunction("user", Hibernate.STRING, false) );
        registerFunction( "deleted", new NoArgSQLFunction("deleted", Hibernate.BOOLEAN, false) );
        registerFunction( "reccount", new NoArgSQLFunction("reccount", Hibernate.LONG, false) );
        registerFunction( "recno", new NoArgSQLFunction("recno", Hibernate.LONG, false) );
        registerFunction( "rowlocked", new NoArgSQLFunction("rowlocked", Hibernate.BOOLEAN, false) );
        //Miscellaneous Functions
        registerFunction( "nvl", new StandardSQLFunction("nvl") );
        registerFunction( "ifnull", new StandardSQLFunction("ifnull") );
        
        
        getDefaultProperties().setProperty(Environment.MAX_FETCH_DEPTH, "2");
        getDefaultProperties().setProperty(Environment.STATEMENT_BATCH_SIZE, DEFAULT_BATCH_SIZE);
    }
    
    // SEQUENCE support ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    /**
     * Does this dialect support sequences?
     *
     * @return True if sequences supported; false otherwise.
     */
    public final boolean supportsSequences() {
        return true;//false;
    }
    
    /**
     * Does this dialect support "pooled" sequences.  Not aware of a better
     * name for this.  Essentially can we specify the initial and increment values?
     *
     * @return True if such "pooled" sequences are supported; false otherwise.
     * @see #getCreateSequenceStrings(String, int, int)
     * @see #getCreateSequenceString(String, int, int)
     */
    public boolean supportsPooledSequences() {
        return true;//false;
    }
    
    /**
     * Generate the appropriate select statement to to retreive the next value
     * of a sequence.
     * <p/>
     * This should be a "stand alone" select statement.
     *
     * @param sequenceName the name of the sequence
     * @return String The "nextval" select string.
     * @throws MappingException If sequences are not supported.
     */
    public final String getSequenceNextValString(String sequenceName) {
        //SELECT NEXTVAL('SEQUENCENAME')
        //"select next_value of " + sequenceName + " from system.onerow";
        return "select " + getSelectSequenceNextValString( sequenceName ) ;
    }
    
    /**
     * Generate the select expression fragment that will retreive the next
     * value of a sequence as part of another (typically DML) statement.
     * <p/>
     * This differs from {@link #getSequenceNextValString(String)} in that this
     * should return an expression usable within another statement.
     *
     * @param sequenceName the name of the sequence
     * @return The "nextval" fragment.
     * @throws MappingException If sequences are not supported.
     */
    public final String getSelectSequenceNextValString(String sequenceName) {
        return  "nextval('" + sequenceName+"')";
    }           
   
    /**
     * Typically dialects which support sequences can create a sequence
     * with a single command.  This is convenience form of
     * {@link #getCreateSequenceStrings} to help facilitate that.
     * <p/>
     * Dialects which support sequences and can create a sequence in a
     * single command need *only* override this method.  Dialects
     * which support sequences but require multiple commands to create
     * a sequence should instead override {@link #getCreateSequenceStrings}.
     *
     * @param sequenceName The name of the sequence
     * @return The sequence creation command
     * @throws MappingException If sequences are not supported.
     */
    public final String getCreateSequenceString(String sequenceName) {
        // create sequence if not exists userID start WITH 100 increment by 2 maxvalue 2000 cache 5 cycle;
        return "create sequence " + sequenceName;        
    }
    
    /**
     * Typically dialects which support sequences can drop a sequence
     * with a single command.  This is convenience form of
     * {@link #getDropSequenceStrings} to help facilitate that.
     * <p/>
     * Dialects which support sequences and can drop a sequence in a
     * single command need *only* override this method.  Dialects
     * which support sequences but require multiple commands to drop
     * a sequence should instead override {@link #getDropSequenceStrings}.
     *
     * @param sequenceName The name of the sequence
     * @return The sequence drop commands
     * @throws MappingException If sequences are not supported.
     */
    public final String getDropSequenceString(String sequenceName) {
        //drop sequence if exists userID;
        return "drop sequence " + sequenceName;
    }
    
    /**
     * Get the select command used retrieve the names of all sequences.
     *
     * @return The select command; or null if sequences are not supported.
     * @see org.hibernate.tool.hbm2ddl.SchemaUpdate
     */
    public final String getQuerySequencesString() {
        return null;
        //select sequence_name from domain.sequences";
        //"select sequence_schema || '.' || sequence_name from information_schema.ext_sequences";
    }
        
  
    // limit/offset support ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Does this dialect support some form of limiting query results
     * via a SQL clause?
     *
     * @return True if this dialect supports some form of LIMIT.
     */
    public final boolean supportsLimit() {
        return true;//false;
    }


    /**
     * Apply s limit clause to the query.
     * <p/>
     * Typically dialects utilize {@link #supportsVariableLimit() variable}
     * limit caluses when they support limits.  Thus, when building the
     * select command we do not actually need to know the limit or the offest
     * since we will just be using placeholders.
     * <p/>
     * Here we do still pass along whether or not an offset was specified
     * so that dialects not supporting offsets can generate proper exceptions.
     * In general, dialects will override one or the other of this method and
     * {@link #getLimitString(String, int, int)}.
     *
     * @param query The query to which to apply the limit.
     * @param hasOffset Is the query requesting an offset?
     * @return the modified SQL
     */
    public String getLimitString(String sql, boolean hasOffset) {
        return new StringBuffer(sql.length() + 20)
            .append(sql)
            .append(hasOffset ? " limit ?, ?" : " limit ?")
            .toString();

 /*       sql = sql.trim();
        boolean isForUpdate = false;
        if ( sql.toLowerCase().endsWith(" for update") ) {
            sql = sql.substring( 0, sql.length()-11 );
            isForUpdate = true;
        }

        StringBuffer pagingSelect = new StringBuffer( sql.length()+100 );
        if (hasOffset) {
            pagingSelect.append("select * from ( select row_.*, RECNO() rownum_ from ( ");
        }
        else {
            pagingSelect.append("select * from ( ");
        }
        pagingSelect.append(sql);
        if (hasOffset) {
            pagingSelect.append(" ) row_ where RECNO() <= ?) where rownum_ > ?");
        }
        else {
            pagingSelect.append(" ) where RECNO() <= ?");
        }

        if (isForUpdate) pagingSelect.append(" for update");

        return pagingSelect.toString();*/
    }

    
    // IDENTITY support ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Does this dialect support identity column key generation?
     *
     * @return True if IDENTITY columns are supported; false otherwise.
     */
    public boolean supportsIdentityColumns() {
            return true;//false;
    }

    /**
     * Whether this dialect have an Identity clause added to the data type or a
     * completely seperate identity data type
     *
     * @return boolean
     */
/*    public boolean hasDataTypeInIdentityColumn() {
            return true;//false;//true;
    }*/


    
    /**
     * Get the select command to use to retrieve the last generated IDENTITY
     * value for a particuar table
     *
     * @param table The table into which the insert was done
     * @param column The PK column.
     * @param type The {@link java.sql.Types} type code.
     * @return The appropriate select command
     * @throws MappingException If IDENTITY generation is not supported.
     */
    public final String getIdentitySelectString(String table, String column, int type){
        //return getIdentitySelectString();
        return new StringBuffer().append("select currval('")
			.append(table)
			.append("','")
			.append(column)
			.append("')")
			.toString();
/*		return new StringBuffer().append("select currval('")
			.append(table)
			.append('_')
			.append(column)
			.append("_seq')")
			.toString();
 		return type==Types.BIGINT ?
			"select dbinfo('serial8') from systables where tabid=1" :
			"select dbinfo('sqlca.sqlerrd1') from systables where tabid=1";

 */
            
    }

 /*   public String getLimitString(String querySelect, int offset, int limit) {
        int lastIndexOfOrderBy = querySelect.toLowerCase().lastIndexOf("order by ");
        if (lastIndexOfOrderBy < 0 || querySelect.endsWith(")") || offset == 0) {
            return super.getLimitString(querySelect, 0, limit);
        } else {
            String orderby = querySelect.substring(lastIndexOfOrderBy, querySelect.length());
            int indexOfFrom = querySelect.toLowerCase().indexOf("from");
            String selectFld = querySelect.substring(0, indexOfFrom);
            String selectFromTableAndWhere = querySelect.substring(indexOfFrom, lastIndexOfOrderBy);
            StringBuffer sql = new StringBuffer(querySelect.length() + 100);
            sql.append("select * from (")
                    .append(selectFld)
                    .append(",recno() as _page_row_num_hb ")
                    .append(selectFromTableAndWhere).append(" ) temp ")
                    .append(" where  _page_row_num_hb BETWEEN  ")
                    .append(offset + 1).append(" and ").append(limit);
            return sql.toString();
        }
    }    */
    
    /**
     * Get the select command to use to retrieve the last generated IDENTITY
     * value.
     *
     * @return The appropriate select command
     * @throws MappingException If IDENTITY generation is not supported.
     */
/*    public String getIdentitySelectString() {
        //return "select last_insert_id()";
        return "select @@identity";
        //"select identity_val_local() from sysibm.sysdummy1"
        //call identity()"
        //"SELECT LAST_IDENTITY() FROM %TSQL_sys.snf";
    }*/

    /**
     * The syntax used during DDL to define a column as being an IDENTITY of
     * a particular type.
     *
     * @param type The {@link java.sql.Types} type code.
     * @return The appropriate DDL fragment.
     * @throws MappingException If IDENTITY generation is not supported.
     */
/*    public String getIdentityColumnString(int type) {
            return getIdentityColumnString();
/*		return type==Types.BIGINT ?
			"bigserial not null" :
			"serial not null";
 		return type==Types.BIGINT ?
			"serial8 not null" :
			"serial not null";

 * /
            
    }*/

    /**
     * The syntax used during DDL to define a column as being an IDENTITY.
     *
     * @return The appropriate DDL fragment.
     * @throws MappingException If IDENTITY generation is not supported.
     */
    public final String getIdentityColumnString() {
         return "auto_increment not null"; //starts with 1, implicitly
         //return "identity not null"; //starts with 1, implicitly
         //"autoincrement";
         //identity";
         //return "generated by default as identity (start with 1)"; //not null is implicit
    }


    /**
     * The keyword used to insert a generated value into an identity column (or null).
     * Need if the dialect does not support inserts that specify no column values.
     *
     * @return The appropriate keyword.
     */
    public final String getIdentityInsertString() {
        //return null;
        return "null";
    }
    
    // lock acquisition support ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Does this dialect support <tt>FOR UPDATE</tt> in conjunction with
     * outer joined rows?
     *
     * @return True if outer joined rows can be locked via <tt>FOR UPDATE</tt>.
     */
    public final boolean supportsOuterJoinForUpdate() {
            return false;//true;//???           
    }
    
    
    // current timestamp support ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Does this dialect support a way to retrieve the database's current
     * timestamp value?
     *
     * @return True if the current timestamp can be retrieved; false otherwise.
     */
    public final boolean supportsCurrentTimestampSelection() {
            return true;//false;
    }

    /**
     * Should the value returned by {@link #getCurrentTimestampSelectString}
     * be treated as callable.  Typically this indicates that JDBC escape
     * sytnax is being used...
     *
     * @return True if the {@link #getCurrentTimestampSelectString} return
     * is callable; false otherwise.
     */
    public final boolean isCurrentTimestampSelectStringCallable() {
            return true;//???
    }

    /**
     * Retrieve the command used to retrieve the current timestammp from the
     * database.
     *
     * @return The command.
     */
    public final String getCurrentTimestampSelectString() {
        return "select now()";
        //"select systimestamp from dual"
        //"select now()";
        //"call current_timestamp()"
    }
    
    /**
     * The name of the database-specific SQL function for retrieving the
     * current timestamp.
     *
     * @return The function name.
     */
    public final String getCurrentTimestampSQLFunctionName() {
        // the standard SQL function name is current_timestamp...
        return "now";//current_timestamp";
    }    
    
    // union subclass support ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Does this dialect support UNION ALL, which is generally a faster
     * variant of UNION?
     *
     * @return True if UNION ALL is supported; false otherwise.
     */
    public final boolean supportsUnionAll() {
        return true;//false;
    }

    // miscellaneous support ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~    
    
    /**
     * The fragment used to insert a row without specifying any column values.
     * This is not possible on some databases.
     *
     * @return The appropriate empty values clause.
     */
    public final String getNoColumnsInsertString() {
            return "values ( )";//???Doesn't support now.'
            //return "default values";
    }
    
    /**
     * The SQL literal value to which this database maps boolean values.
     *
     * @param bool The boolean value
     * @return The appropriate SQL literal.
     */
    public final String toBooleanValueString(boolean bool) {
        return bool ? "true" : "false";
    }    

    // DDL support ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Do we need to drop constraints before dropping tables in this dialect?
     *
     * @return True if constraints must be dropped prior to dropping
     * the table; false otherwise.
     */
    public final boolean dropConstraints() {
            return false;//true;
    }
    
    /**
     * Do we need to qualify index names with the schema name?
     *
     * @return boolean
     */
    public final boolean qualifyIndexName() {
            return false;//true;
    }

    /**
     * The syntax used to add a column to a table (optional).
     *
     * @return The "add column" fragment.
     */
    public final String getAddColumnString() {
        //ALTER table TBNAME ADD COLUMN FIELDNAME FIELDTYPE
        return "add column";
    }

    public final String getDropForeignKeyString() {
        throw new UnsupportedOperationException("No drop foreign key foreign supported by Hxtt Dialect");
        //return " drop constraint ";
    }

    /**
     * The syntax used to add a primary key constraint to a table.
     *
     * @param constraintName The name of the PK constraint.
     * @return The "add PK" fragment
     */
    public final String getAddPrimaryKeyConstraintString(String constraintName) {
        return " primary key ";
        //return " add constraint " + constraintName + " primary key ";
    }
    
    
    /**
     * The keyword used to specify a nullable column.
     *
     * @return String
     */
    public final String getNullColumnString() {
        return " null";
    }

    public final boolean supportsIfExistsBeforeTableName() {
        return true;//false;
    }
    
    /**
     * Does this dialect support column-level check constraints?
     *
     * @return True if column-level CHECK constraints are supported; false
     * otherwise.
     */
    public final boolean supportsColumnCheck() {
        return false;//true;//Support little
    }

    /**
     * Does this dialect support table-level check constraints?
     *
     * @return True if table-level CHECK constraints are supported; false
     * otherwise.
     */
    public final boolean supportsTableCheck() {
        return false;//true;
    }
    
    public boolean supportsCascadeDelete() {
        return false;//true; HXTT Access supports
    }    
    
    /**
     * Is this dialect known to support what ANSI-SQL terms "row value
     * constructor" syntax; sometimes called tuple syntax.
     * <p/>
     * Basically, does it support syntax like
     * "... where (FIRST_NAME, LAST_NAME) = ('Steve', 'Ebersole') ...".
     *
     * @return True if this SQL dialect is known to support "row value
     * constructor" syntax; false otherwise.
     * @since 3.2
     */
    public final boolean supportsRowValueConstructorSyntax() {
        // return false here, as most databases do not properly support this construct...
        return true;//false;
    }
   
    /**
     * If the dialect supports {@link #supportsRowValueConstructorSyntax() row values},
     * does it offer such support in IN lists as well?
     * <p/>
     * For example, "... where (FIRST_NAME, LAST_NAME) IN ( (?, ?), (?, ?) ) ..."
     *
     * @return True if this SQL dialect is known to support "row value
     * constructor" syntax in the IN list; false otherwise.
     * @since 3.2
     */
    public final boolean supportsRowValueConstructorSyntaxInInList() {
            return true;//false;
    }

    /**
     * Should LOBs (both BLOB and CLOB) be bound using stream operations (i.e.
     * {@link java.sql.PreparedStatement#setBinaryStream}).
     *
     * @return True if BLOBs and CLOBs should be bound using stream operations.
     * @since 3.2
     */
    public final boolean useInputStreamToInsertBlob() {
            return false;//true;//???
    }
    

    /**
     * Does this dialect support definition of cascade delete constraints
     * which can cause circular chains?
     *
     * @return True if circular cascade delete constraints are supported; false
     * otherwise.
     * @since 3.2
     */
    public final boolean supportsCircularCascadeDeleteConstraints() {
            return false;//true; //??? MS Access doesn't support too?
    }
    
    
}
