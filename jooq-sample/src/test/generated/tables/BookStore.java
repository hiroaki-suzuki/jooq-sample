/**
 * This class is generated by jOOQ
 */
package test.generated.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.3.0" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BookStore extends org.jooq.impl.TableImpl<test.generated.tables.records.BookStoreRecord> {

	private static final long serialVersionUID = -1087621417;

	/**
	 * The singleton instance of <code>jooq-test.book_store</code>
	 */
	public static final test.generated.tables.BookStore BOOK_STORE = new test.generated.tables.BookStore();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<test.generated.tables.records.BookStoreRecord> getRecordType() {
		return test.generated.tables.records.BookStoreRecord.class;
	}

	/**
	 * The column <code>jooq-test.book_store.name</code>.
	 */
	public final org.jooq.TableField<test.generated.tables.records.BookStoreRecord, java.lang.String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(250).nullable(false), this, "");

	/**
	 * Create a <code>jooq-test.book_store</code> table reference
	 */
	public BookStore() {
		this("book_store", null);
	}

	/**
	 * Create an aliased <code>jooq-test.book_store</code> table reference
	 */
	public BookStore(java.lang.String alias) {
		this(alias, test.generated.tables.BookStore.BOOK_STORE);
	}

	private BookStore(java.lang.String alias, org.jooq.Table<test.generated.tables.records.BookStoreRecord> aliased) {
		this(alias, aliased, null);
	}

	private BookStore(java.lang.String alias, org.jooq.Table<test.generated.tables.records.BookStoreRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, test.generated.Jooq_test.JOOQ_TEST, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<test.generated.tables.records.BookStoreRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<test.generated.tables.records.BookStoreRecord>>asList(test.generated.Keys.KEY_BOOK_STORE_NAME);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public test.generated.tables.BookStore as(java.lang.String alias) {
		return new test.generated.tables.BookStore(alias, this);
	}

	/**
	 * Rename this table
	 */
	public test.generated.tables.BookStore rename(java.lang.String name) {
		return new test.generated.tables.BookStore(name, null);
	}
}
