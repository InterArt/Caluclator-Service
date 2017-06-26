package am.profclub.ws.calculation.jexl;

public interface QueryConstants {
	
	/**
	 * "ID" is used to query most items by primary key. This key is the only that will recognize objects that have been created and assigned a new sequence number, but have not yet been committed or added to the database.
	 **/
	public static final String ID = "ID";
	public static final String NAME = "name";

	// Query Limits
	public static final int CURSOR_THRESHOLD = 500;
	public static final int CURSOR_PAGESIZE = 500;
	public static final int MAX_CURSOR_SIZE = 2000;

	// Internally Used Constants
	public static final String INTERNAL_PREFIX = "$$";
	public static final String READ_ONLY_MM_SUFFIX = "MM";
	public static final String READ_ONLY_OM_SUFFIX = "OM";
	public static final String PKIDCOUNT = INTERNAL_PREFIX + "pkIDCount";
	public static final String CI = "ci";

	/**
	 * Indicates that the query should include deleted objects.
	 */
	public static final String INCLUDE_DELETED_OBJECTS = INTERNAL_PREFIX + "includeDeletedObjects";

	/**
	 * Should be used with great caution. In the context of bulk updates will skip the process of updating the fields of
	 * in-memory objects from EclipseLink's identity maps
	 * (see com.attask.biz.BizContext#executeBulkUpdateNow(java.lang.Class, java.util.Map, java.util.Map)).
	 */
	String SKIP_CACHE_UPDATE = INTERNAL_PREFIX + "skipCacheUpdate";

	/**
	 * <P>Wildcard value for midnight (12:00AM) of the current Date. Wildcards are useful for Saved Searches. If this value is passed in for any search value, it is replaced with the current date. The following suffixes can also be used to modify this date: [b/e][+/-][# of units]["h" (hour)|"d" (day)|"w" (week)|"m" (month)|"q" (quarter)|"y" (year)] <BR>
	 * <code>$$TODAY+1d would equal 12:00AM of the next day</code><BR>
	 * <code>$$TODAY-1d would equal 12:00AM of the previous day</code><BR>
	 * <code>$$TODAY+2w would equal 12:00AM of 2 weeks from today</code><BR>
	 * <code>$$TODAY+2m would equal 12:00AM of 2 months from today</code><BR>
	 * <code>$$TODAYb "the beginning of today" would equal 12:00AM today</code><BR>
	 * <code>$$TODAYe "the end of today" would equal 12:00AM tomorrow</code><BR>
	 * <code>$$TODAYbm "the beginning of the month" would equal 12:00AM of the first day of the month</code><BR>
	 * <code>$$TODAYe+1w "the end of next week" would equal 12:00AM Sunday following the Saturday of next week</code><BR>
	 </P>
	 * Value is "$$TODAY"
	 **/
	public static final String TODAY_WILDCARD = INTERNAL_PREFIX + "TODAY";

	/**
	 * <P>Wildcard value for the current time of the current Date. Wildcards are useful for Saved Searches. If this value is passed in for any search value, it is replaced with the current date and time. The following suffixes can also be used to modify this date: [b/e][+/-][# of units]["h" (hour)|"d" (day)|"w" (week)|"m" (month)|"q" (quarter)|"y" (year)] <BR>
	 * <code>$$NOW+1d would equal the same time on the next day</code><BR>
	 * <code>$$NOW-1d would equal the same time on the previous day</code><BR>
	 * <code>$$NOW+2w would equal the same time on 2 weeks from now</code><BR>
	 * <code>$$NOW+2m would equal the same time on 2 months from now</code><BR>
	 </P>
	 * Value is "$$NOW"
	 **/
	public static final String NOW_WILDCARD = INTERNAL_PREFIX + "NOW";

	/**
	 * <P>Wildcard value for the currently authenticated User. Wildcards are useful for Saved Searches. If this value is passed in for any search value, it is replaced with an attribute from the current User. The following suffixes can be used: .[ID(default)|homeGroupID|accessLevelID|categoryID|companyID|roleID|roleIDs|otherGroupIDs|accessLevelRank]
	 * <code>$$USER would equal the current User's ID</code><BR>
	 * <code>$$USER.homeGroupID would equal the current User's Home Group ID</code><BR>
	 * <code>$$USER.accessLevelID would equal the current User's Access Level ID</code><BR>
	 * <code>$$USER.otherGroupIDs would equal the all of the current User's Other Group IDs. This would translate into an "IN" clause for that queried field.</code><BR>
	 * <code>$$USER.roleIDs would equal the all of the current User's Roles. This would translate into an "IN" clause for that queried field.</code><BR>
	 * <code>$$USER.roleID would equal the all of the current User's Primary Role ID.</code><BR>
	 </P>
	 * Value is "$$USER"
	 **/
	public static final String USER_WILDCARD = INTERNAL_PREFIX + "USER";


	
	public static final String ACCOUNTREP_WILDCARD = INTERNAL_PREFIX + "AR";

	public static final String PERSISTENTOBJECT_WILDCARD = INTERNAL_PREFIX + "OBJ";
	public static final String CUSTOMER_WILDCARD = INTERNAL_PREFIX + "CUSTOMER";

	/**
	 * Object User constants for Approvals
	 */
	public static final String PROJECT_OWNER_WILDCARD = INTERNAL_PREFIX + "PROJECT_OWNER";
	public static final String PROJECT_SPONSOR_WILDCARD = INTERNAL_PREFIX + "PROJECT_SPONSOR";
	public static final String PORTFOLIO_OWNER_WILDCARD = INTERNAL_PREFIX + "PORTFOLIO_OWNER";
	public static final String PROGRAM_OWNER_WILDCARD = INTERNAL_PREFIX + "PROGRAM_OWNER";
	public static final String MANAGER_WILDCARD = INTERNAL_PREFIX + "MANAGER";
	public static final String ORIGINATOR_WILDCARD = INTERNAL_PREFIX + "ORIGINATOR";
	public static final String ORIGINATOR_MANAGER_WILDCARD = INTERNAL_PREFIX + "ORIGINATOR_MANAGER";

	/**
	 * Key used to specify the index of the first result to return starting with .
	 */
	public static final String FIRST = INTERNAL_PREFIX + "FIRST";

	/**
	 * Key used to specify a limit on the number of results. If this key is present, the value is used. If this value cannot be parsed or if it is less than or equal to 0, no limit is enforced. 
	 * Value is "$$LIMIT"
	 **/
	public static final String LIMIT = INTERNAL_PREFIX + "LIMIT";
	public static final String MAX_LIMIT = "max";

	/**
	 * If included in the query map, this can be used to turn on relevance based sorting for some object types.  For
	 * example, including it with users will result in the current user, members of the current user's group, or
	 * members of the user's team to be returned first in any search.
	 */
	public static final String SORT_RELEVANCE_KEY = INTERNAL_PREFIX + "relevance";

	/**
	 * Prefix used to embed an actual Expression in the Query framework. Objects bound to this key must be of type <code>org.eclipse.persistence.expressions.Expression</code>. For internal use only.
	 **/
	public static final String EXPRESSION = "EXP:";

	/**
	 * Prefix used to identify an Data Extension parameter in the Query framework. For internal use only.
	 **/
	public static final String DATAEXTENSION = "DE:";

	/**
	 * Prefix used to identify an "OR'd" expression parameter in the Query framework. Objects bound to this key must be of type <code>java.util.Map</code>. For internal use only.
	 **/
	public static final String OR = "OR:";

	/**
	 * Prefix used to identify an "AND'd" expression parameter in the Query framework. This allows for multiple keys of the same name to be included in a search. For internal use only.
	 **/
	public static final String AND = "AND:";

	/**
	 * Prefix used to identify an exists clause in the Query framework. Objects bound to this key must be of type <code>java.util.Map</code>. For internal use only.
	 **/
	public static final String EXISTS = "EXISTS:";
	public static final String EXISTS_OBJCODE = "$$OBJCODE";
	public static final String EXISTS_JEXL = "$$JEXL:";
	public static final String EXISTS_MOD = "$$EXISTSMOD";
	public static final String NOTEXISTS = "NOTEXISTS";

	/**
	 * Prefix used in the value side of the query map for specifying another field to use as a comparison value for the main value. If a field is not found, then the literal value is applied in the expression. Value is "FIELD:".
	 **/
	public static final String FIELD = "FIELD:";


	/**
	 * Suffix for specifying that 1-1, 1-M, or M-M objects should be populated in Batch mode for a particular query. Value is "_BatchRead".
	 **/
	public static final String BATCHREAD = "_BatchRead";

	/**
	 * Key that indicates the type of batch read to use for batch queries.
	 */
	public static final String BATCHREAD_TYPE = INTERNAL_PREFIX + "BATCHREAD_TYPE";

	/**
	 * Value for indicating batch reads should be done with a join.
	 */
	public static final String BATCHREAD_TYPE_JOIN = "join";

	/**
	 * Value for indicating batch reads should be done with an exists clause.
	 */
	public static final String BATCHREAD_TYPE_EXISTS = "exists";

	/**
	 * Value for indicating batch reads should be done with an in clause.
	 */
	public static final String BATCHREAD_TYPE_IN = "in";

	/**
	 * Key that indicates the max size for a batch read (for IN batch reading).
	 */
	public static final String BATCHREAD_SIZE = INTERNAL_PREFIX + "BATCHREAD_SIZE";

	/**
	 * Key to indicate query results should not be cached in the identity map
	 * (remove from the map to disable)
	 */
	public static final String DONT_CACHE = INTERNAL_PREFIX + "DONT_CACHE";

	/**
	 * Key to indicate query results should override entries in the identity map
	 * (remove from the map to disable)
	 */
	public static final String REFRESH_CACHE = INTERNAL_PREFIX + "REFRESH_CACHE";

	/**
	 * Loads transient data into Bean objects. For example, you wouldn't call allowedActions_BatchRead, instead you could call allowedActions_Get.
	 */
	public static final String GET = "_Get";

	public static final String CONNECTBY = "_ConnectBy";
	public static final String START_WITH = "STARTWITH:";

	/**
	 * Suffix for specifying expression operators on a field. Value is "_Mod".
	 **/
	public static final String MOD = "_Mod";

	/**
	 * Suffix for specifying how nested fields should be JOINED in SQL. This suffix must be used on the nested attribute itself. Value must be "ALLOWINGNULL". Value is "_Join".
	 **/
	public static final String JOIN = "_Join";

	/**
	 * Suffix used to send a hint to the database recommending that a given index is used when processing the query.
	 * Value is "$$Index_".  For instance, to hint the database that it should use the NOTES_ENTRYDATE_IDX for the T_NOTES
	 * table in the query, you would put $$Index_T_NOTES = NOTES_ENTRYDATE_IDX in your query map.
	 */
	public static final String INDEX_HINT = INTERNAL_PREFIX + "Index_";


	/**
	 * Suffix for specifying the 2nd argument in a Range of values to be searched. Value is "_Range".
	 **/
	public static final String RANGE = "_Range";

	/**
	 * Suffix for specifying the 2nd argument for a combined field. This is used to split the combinedField value string to generate the search map. Value is "_Split".
	 **/
	public static final String SPLIT = "_Split";



	/**
	 * Suffix for specifying which fields will be added to the GROUP BY clause in a ReportQuery. Value is "_GroupBy".
	 **/
	public static final String GROUPBY = "_GroupBy";
	
	/**
	 * Suffix for specifying force "_GroupBy".
	 **/
	public static final String FORCE_GROUPBY = INTERNAL_PREFIX + "_ForceGroupBy";

	/**
	 * Suffix for specifying aggregate functions in a ReportQuery. Value is "_AggFunc".
	 **/
	public static final String AGGFUNC = "_AggFunc";

	/**
	 * Suffix for specifying comma-separated list of aggregated currency fields for the report
	 **/
	public static final String AGGCURRENCY_FIELDS = INTERNAL_PREFIX + "AggCurr";
	public static final String GROUPCURRENCY_FIELDS = INTERNAL_PREFIX + "GroupCurr";


	public static final String SORTCURRENCY_FIELDS = INTERNAL_PREFIX + "SortCurr";
	public static final String FILTERCURRENCY_FIELDS = INTERNAL_PREFIX + "FilterCurr";

	/**
	 * Suffix for specifying work fields (hours).
	 */
	public static final String AGGCUSTOM_FIELDS = INTERNAL_PREFIX + "AggCust";

	/**
	 * Key used to specify that a GROUP BY query should be done WITH ROLLUP. Value is "$$ROLLUP"
	 **/
	public static final String ROLLUP = INTERNAL_PREFIX + "ROLLUP";


	/**
	 * Prefix for identifying a Sort field. Value is "_1_Sort" for first sort field, "_2_Sort", "_3_Sort" ... "_n_Sort".
	 **/
	public static final String SORT = "_Sort";
	public static final String ORDERDOT = "_";
	public static final String DOT = ":";
	public static final char DOT_CHAR = ':';
	public static final char LEGACY_DOT_CHAR = '.';

	public static final String FILTER_QUERYMAP_KEY = QueryConstants.AND + "filter";

	public static final String DATERANGEPREFIX = "_By";
	public static final String WITHYEARSUFFIX = "WithYear";

	/**
	 * Suffix that specifies the given date field does not have a time associated with it.  Used for time zone purposes.
	 */
	public static final String NOTIME = "_NoTime";


	public static final String DAY = "Day";
	/**
	 * Suffix for grouping date fields within the same day. Value is "_ByDay". This is used in conjunction with the Group By suffixes, so for example, plannedStartDate_ByDay_1_GroupBy
	 **/
	public static final String BYDAY = DATERANGEPREFIX + DAY;
	public static final String BYDAY_WITHYEAR = DATERANGEPREFIX + DAY + WITHYEARSUFFIX;
	public static final int BYDAY_FUNC = -48;
	public static final int BYDAY_WITHYEAR_FUNC = -148;


	public static final String WEEK = "Week";
	/**
	 * Suffix for grouping date fields within the same week. Value is "_ByWeek". This is used in conjunction with the Group By suffixes, so for example, plannedStartDate_ByWeek_1_GroupBy
	 **/
	public static final String BYWEEK = DATERANGEPREFIX + WEEK;
	public static final String BYWEEK_WITHYEAR = DATERANGEPREFIX + WEEK + WITHYEARSUFFIX;
	public static final int BYWEEK_FUNC = -50;
	public static final int BYWEEK_WITHYEAR_FUNC = -150;


	public static final String WEEKFROMDATE = "WeekFromDate";
	/**
	 * Suffix for grouping date fields within the same week starting from an anchor date. Value is "_ByWeekFromDate". This is used in conjunction with the Group By suffixes, so for example, plannedStartDate_ByWeekFromDate_1_GroupBy
	 **/
	public static final String BYWEEKFROMDATE = DATERANGEPREFIX + WEEKFROMDATE;
	public static final int BYWEEKFROMDATE_FUNC = -78;


	public static final String  MONTH = "Month";
	/**
	 * Suffix for grouping date fields within the same month. Value is "_ByMonth". This is used in conjunction with the Group By suffixes, so for example, plannedStartDate_ByMonth_1_GroupBy
	 **/
	public static final String BYMONTH = DATERANGEPREFIX + MONTH;
	public static final String BYMONTH_WITHYEAR = DATERANGEPREFIX + MONTH + WITHYEARSUFFIX;
	public static final int BYMONTH_FUNC = -52;
	public static final int BYMONTH_WITHYEAR_FUNC = -152;


	public static final String QUARTER = "Quarter";
	/**
	 * Suffix for grouping date fields within the same quarter. Value is "_ByQuarter". This is used in conjunction with the Group By suffixes, so for example, plannedStartDate_ByQuarter_1_GroupBy
	 **/
	public static final String  BYQUARTER = DATERANGEPREFIX + QUARTER;
	public static final String  BYQUARTER_WITHYEAR = DATERANGEPREFIX + QUARTER + WITHYEARSUFFIX;
	public static final int BYQUARTER_FUNC = -53;
	public static final int BYQUARTER_WITHYEAR_FUNC = -153;


	public static final String YEAR = "Year";
	/**
	 * Suffix for grouping date fields within the same year. Value is "_ByYear". This is used in conjunction with the Group By suffixes, so for example, plannedStartDate_ByYear_1_GroupBy
	 **/
	public static final String BYYEAR = DATERANGEPREFIX + YEAR;
	public static final int BYYEAR_FUNC = -54;

	public static final int DATE_FUNC_SELECTORS_ARE_BELOW_THIS_VALUE = 0;
	public static final int ORACLE_ARRAY_IN = 5500;
	public static final String DISABLE_ORACLE_ARRAY_IN = INTERNAL_PREFIX + "DISABLE_ORACLE_ARRAY_IN";

	public static final int NULLIFELSE_FUNC = 1601;
	public static final int IF_FUNC = 1602;
	public static final String LIST_CURRENCY = INTERNAL_PREFIX + "CURRENCY";


	/**
	 * Ascending Sort. Value is "asc".
	 **/
	public static final String ASC = "asc";

	/**
	 * Descending Sort. Value is "desc".
	 **/
	public static final String DESC = "desc";

	/**
	 * Quick Search is a special search field that is supported by all objects. In general, it is used to identify objects by ID, name, or description. Value is "quicksearch".
	 **/
	public static final String QUICKSEARCH = "quicksearch";


	/**
	 * Case-Insensitive Ascending Sort. Value is "ciasc".
	 **/
	public static final String CIASC = "ciasc";

	/**
	 * Case-Insensitive Descending Sort. Value is "cidesc".
	 **/
	public static final String CIDESC = "cidesc";

	public static final String ALLOWINGNULL = "allowingnull";


	/**
	 * Produces the SQL expression <code>field < value</code>. Value is "lt".
	 **/
	public static final String LESSTHAN = "lt";

	/**
	 * Produces the SQL expression <code>field <= value</code>. Value is "lte".
	 **/
	public static final String LESSTHANEQUAL = "lte";

	/**
	 * Produces the SQL expression <code>field > value</code>. Value is "gt".
	 **/
	public static final String GREATERTHAN = "gt";

	/**
	 * Produces the SQL expression <code>field >= value</code>. Value is "gte".
	 **/
	public static final String GREATERTHANEQUAL = "gte";

	/**
	 * Produces the SQL expression <code>field = value</code>
	 * Note that this is the default Modifier used when 1 value exists. Value is "eq".
	 **/
	public static final String EQUAL = "eq";

	/**
	 * Produces the SQL expression <code>UPPER(field) = UPPER(value)</code>. Value is "cieq".
	 **/
	public static final String CIEQUAL = "cieq";

	/**
	 * Produces the SQL expression <code>field <> value or field is null</code>. Value is "ne".
	 **/
	public static final String NOTEQUAL = "ne";

	/**
	 * Produces the SQL expression <code>field <> value</code>. This differs from NOTEQUAL in that null results are not
	 * returned. Value is "nee".
	 **/
	public static final String NOTEQUALEXACT = "nee";

	/**
	 * Produces the SQL expression <code>UPPER(field) <> UPPER(value)</code>. Value is "cine".
	 **/
	public static final String CINOTEQUAL = "cine";

	/**
	 * Produces the SQL expression <code>field LIKE '%value%'</code>. Value is "contains".
	 **/
	public static final String CONTAINS = "contains";

	/**
	 * Produces the SQL expression <code>UPPER(field) LIKE UPPER('%value%')</code>. Value is "cicontains".
	 **/
	public static final String CICONTAINS = "cicontains";
	
	/**
	 * Produces the SQL expression <code>UPPER(field) LIKE UPPER('%value1%') OR UPPER(field) LIKE UPPER('%value2%') ... </code> where value1, value2, etc. are the results of value.split(" "). Value is "cicontainsany".
	 **/
	public static final String CICONTAINSANY = "cicontainsany";
	
	/**
	 * Produces the SQL expression <code>UPPER(field) LIKE UPPER('%value1%') AND UPPER(field) LIKE UPPER('%value2%') ... </code> where value1, value2, etc. are the results of value.split(" "). Value is "cicontainsany".
	 **/
	public static final String CICONTAINSALL = "cicontainsall";

	/**
	 * Produces the SQL expression <code>UPPER(field) LIKE UPPER('%value1%') AND UPPER(field) LIKE UPPER('%value2%') ... </code> where value1, value2, etc. are the results of value.split(" "). Value is "cicontainsany".
	 **/
	public static final String CINOTCONTAINSALL = "cinotcontainsall";

	/**
	 * Produces the SQL expression <code>UPPER(field) LIKE UPPER('%value1%') AND UPPER(field) LIKE UPPER('%value2%') ... </code> where value1, value2, etc. are the results of value.split(" "). Value is "cicontainsany".
	 **/
	public static final String CINOTCONTAINSANY = "cinotcontainsany";

    /**
	 * Produces the SQL expression <code>field NOT LIKE '%value%'</code>. Value is "notcontains".
	 **/
	public static final String NOTCONTAINS = "notcontains";
	
	/**
	 * Produces the SQL expression <code>UPPER(field) NOT LIKE UPPER('%value%')</code>. Value is "cinotcontains".
	 **/
	public static final String CINOTCONTAINS = "cinotcontains";

	/**
	 * Produces the SQL expression <code>field LIKE 'value'</code>
	 * where value can contain replacement characters such as % and _. Value is "like".
	 **/
	public static final String LIKE = "like";

	/**
	 * Produces the SQL expression <code>UPPER(field) LIKE UPPER('value')</code>
	 * where value can contain replacement characters such as % and _. Value is "cilike".
	 **/
	public static final String CILIKE = "cilike";

	/**
	 * Produces the SQL expression <code>UPPER(field) LIKE UPPER('value%')</code>. Value is "startswith".
	 **/
	public static final String STARTSWITH = "startswith";

	/**
	 * Produces the SQL expression <code>SOUNDEX(field) = SOUNDEX(value)</code>. Value is "soundex".
	 **/
	public static final String SOUNDEX = "soundex";

	/**
	 * Use of this Modifier requires the inclusion of a <code>value_Range</code> parameter.
	 * Produces the SQL expression <code>field BETWEEN value AND value_Range</code>.
	 * Note that this is the default Modifier used when a <code>_Range</code> value exists. Value is "between".
	 **/
	public static final String BETWEEN = "between";

	/**
	 * Use of this Modifier required the inclusion of a <code>value_Range</code> parameter.
	 * Produces the SQL expression <code>UPPER(field) BETWEEN UPPER(value) AND UPPER(value_Range)</code>. Value is "cibetween".
	 **/
	public static final String CIBETWEEN = "cibetween";

	/**
	 * Use of this Modifier required the inclusion of a <code>value_Range</code> parameter.
	 * Produces the SQL expression <code>fieldNOT BETWEEN value AND value_Range</code>. Value is "notbetween".
	 **/
	public static final String NOTBETWEEN = "notbetween";

	/**
	 * Use of this Modifier required the inclusion of a <code>value_Range</code> parameter.
	 * Produces the SQL expression <code>UPPER(field)NOT BETWEEN UPPER(value) AND UPPER(value_Range)</code>. Value is "cinotbetween".
	 **/
	public static final String CINOTBETWEEN = "cinotbetween";

	/**
	 * Use of this Modifier assumes multiple <code>value</code> fields with different values.
	 * Produces the SQL expression <code>field IN ( value1, value2, ..., valuen)</code>
	 * Note that this is the default Modifier used when multiple <code>value</code> fields exist. Value is "in".
	 **/
	public static final String IN = "in";

	/**
	 * Use of this Modifier assumes multiple <code>value</code> fields with different values.
	 * Produces the SQL expression <code>UPPER(field) IN ( UPPER(value1), UPPER(value2), ..., UPPER(valuen))</code>
	 **/
	public static final String CIIN = "ciin";

	/**
	 * Use of this Modifier assumes multiple <code>value</code> fields with different values.
	 * Produces the SQL expression <code>field NOT IN ( value1, value2, ..., valuen)</code>. Value is "notin".
	 **/
	public static final String NOTIN = "notin";

	/**
	 * Use of this Modifier assumes multiple <code>value</code> fields with different values.
	 * Produces the SQL expression <code>UPPER(field) NOT IN ( UPPER(value1), UPPER(value2), ..., UPPER(valuen))</code>
	 **/
	public static final String CINOTIN = "cinotin";
	
	/**
	 * Produces the SQL expression <code>field & value > 0</code>. Value is "bitwiseor".  Useful for checking if any of a group of bits is set.
	 **/
	public static final String BITWISE_OR = "bitwiseor";

	/**
	 * Produces the SQL expression <code>field & value = value</code>. Value is "bitwiseand".  Useful for checking if all of a group of bits is set.
	 **/
	public static final String BITWISE_AND = "bitwiseand";
	
	/**
	 * Produces the SQL expression <code>field & value = 0</code>. Value is "bitwisenand".  Useful for checking if none of a group of bits is set.
	 **/
	public static final String BITWISE_NAND = "bitwisenand";

	public static final int BITWISE_AND_FUNC = -58;

	/**
	 * By default, searches for a date value generate the following SQL filter <code>field between <00:00:00:000 of day> and <23:59:59:999 of day></code>.
	 * This is for convenience so that date searches return any match on items that fall on that date.
	 * However, if it is desired to find an exact match on a specific time of day as well as the date, this Modifier enforces that rule.
	 * The SQL filter generated by this Modifier is <code>field = value</code>. Value is "exacttime".
	 **/
	public static final String EXACT_TIME = "exacttime";

	/**
	 * Searches based on the string length of the given field.
	 */
	public static final String LENGTH_LT = "length_lt";
	public static final String LENGTH_EQ = "length_eq";
	public static final String LENGTH_GT = "length_gt";

	/**
	 * Used for DE queries that allow multiple values. This Modifier requires that DE fields have all of the specified values. Value is "allof".
	 **/
	public static final String ALLOF = "allof";


	/**
	 * Produces the SQL expression <code>field IS NULL</code>. The <code>value</code> and <code>value_Mod</code> are both required, but the <code>value</code> is ignored. Value is "isnull".
	 **/
	public static final String ISNULL = "isnull";

	/**
	 * Produces the SQL expression <code>field IS NOT NULL</code>. The <code>value</code> and <code>value_Mod</code> are both required, but the <code>value</code> is ignored. Value is "notnull".
	 **/
	public static final String NOTNULL = "notnull";


	/**
	 * Produces the SQL expression <code>field IS NULL OR field = ''</code>. The <code>value</code> and <code>value_Mod</code> are both required, but the <code>value</code> is ignored. Value is "isblank".
	 **/
	public static final String ISBLANK = "isblank";

	/**
	 * Produces the SQL expression <code>field IS NOT NULL AND field <> ''</code>. The <code>value</code> and <code>value_Mod</code> are both required, but the <code>value</code> is ignored. Value is "notblank".
	 **/
	public static final String NOTBLANK = "notblank";


	/**
	 * Query value recognized as <code>true</code>. Value is "true".
	 **/
	public static final String TRUE = "true";


	/**
	 * Query value recognized as <code>false</code>. Value is "false".
	 **/
	public static final String FALSE = "false";


	/**
	 * Maximum value aggregate function. Can only be used with the AGGFUNC suffix. Value is "max".
	 **/
	public static final String MAX = "max";

	/**
	 * Minimum value aggregate function. Can only be used with the AGGFUNC suffix. Value is "min".
	 **/
	public static final String MIN = "min";

	/**
	 * Average value aggregate function. Can only be used with the AGGFUNC suffix. Value is "avg".
	 **/
	public static final String AVG = "avg";

	/**
	 * Summation aggregate function. Can only be used with the AGGFUNC suffix. Value is "sum".
	 **/
	public static final String SUM = "sum";

	/**
	 * Count aggregate function. Can only be used with the AGGFUNC suffix. Value is "count".
	 **/
	public static final String COUNT = "count";

	/**
	 * Standard Deviation aggregate function. Can only be used with the AGGFUNC suffix. Value is "std".
	 **/
	public static final String STD = "std";

	/**
	 * Variance aggregate function. Can only be used with the AGGFUNC suffix. Value is "var".
	 **/
	public static final String VAR = "var";

	/**
	 * Maximum value aggregate function. Can only be used with the AGGFUNC suffix. Value is "max".
	 **/
	public static final String DMAX = "dmax";

	/**
	 * Minimum value aggregate function. Can only be used with the AGGFUNC suffix. Value is "min".
	 **/
	public static final String DMIN = "dmin";

	/**
	 * Average value aggregate function. Can only be used with the AGGFUNC suffix. Value is "avg".
	 **/
	public static final String DAVG = "davg";

	/**
	 * Summation aggregate function. Can only be used with the AGGFUNC suffix. Value is "sum".
	 **/
	public static final String DSUM = "dsum";

	/**
	 * Count aggregate function. Can only be used with the AGGFUNC suffix. Value is "count".
	 **/
	public static final String DCOUNT = "dcount";

	/**
	 * Standard Deviation aggregate function. Can only be used with the AGGFUNC suffix. Value is "std".
	 **/
	public static final String DSTD = "dstd";

	/**
	 * Variance aggregate function. Can only be used with the AGGFUNC suffix. Value is "var".
	 **/
	public static final String DVAR = "dvar";

	public static final String DISTINCT_PREFIX = "d";
	

	/**
	 * Used to create groupby variable name. Value is ".by.".
	 * For ex. entryDate.by.week, entryDate.by.month
	 **/
	public static final String BY = ".by.";
	
	/**
	 * Is Grouping column prefix used to identify ROLLUP rows inserted by the database. Value is "isgrouping".
	 **/
	public static final String ISGROUPING = "isgrouping";

	/**
	 * ISREPORT is used to indicate whether a query is for a report.  If it is for a report the query timeout should use the
	 * reportQueryTimeout instead of queryTimeout
	 **/
	public static final String ISREPORT = INTERNAL_PREFIX + "isreport";

	/**
	 * Gets a formatted value for the AtBean or REST API, for example:   FORMAT:atDate(plannedCompletionDate)
	 */
	public static final String FORMAT = "FORMAT:";

	/**
	 * Gets an evaluated formula value for the REST API, for example: FORMULA:IF({isEnforced}="true","Enforced","Not Enforced")
	 */
	public static final String FORMULA = "FORMULA:";

	/**
	 * Gets all the direct fields on an object for the REST API
	 */
	public static final String ALL_FIELDS = "*";

	/**
	 * Used to specify an alternative class to handle security on object
	 */
	public static final String JEXL_ALTERNATIVE = INTERNAL_PREFIX + "jexl_alternative";

	/**
	 * If this query is for a drilldown report. Such as when a chart is displayed, and a section of that chart is clicked on to list the
	 * items corresponding to that section of the chart. The value is ignored - if this is present, it is considered a drilldown report.
	 */
	public static final String DRILLDOWN_DETAIL = INTERNAL_PREFIX + "isdrilldown";

	/**
	 * If this particular query is for an export.
	 */
	public static final String ISEXPORT = INTERNAL_PREFIX + "isexport";
}
