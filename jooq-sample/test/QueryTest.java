import static org.jooq.impl.DSL.*;
import static org.junit.Assert.*;
import static test.generated.tables.Author.*;
import static test.generated.tables.Book.*;
import static test.generated.tables.BookStore.*;
import static test.generated.tables.BookToBookStore.*;

import org.jooq.DSLContext;
import org.jooq.Record3;
import org.jooq.Result;
import org.junit.Before;
import org.junit.Test;

import sample.DBConnection;
import test.generated.tables.Author;
import test.generated.tables.Book;
import test.generated.tables.BookStore;
import test.generated.tables.BookToBookStore;

public class QueryTest {

    DSLContext create;

    @Before
    public void setup() throws Exception {
        create = DBConnection.createDSL();
    }

    @Test
    public void test1() throws Exception {
        Book b = BOOK.as("b");
        Author a = AUTHOR.as("a");
        BookStore s = BOOK_STORE.as("s");
        BookToBookStore t = BOOK_TO_BOOK_STORE.as("t");

        Result<Record3<String, String, Integer>> result =
                create.select(a.FIRST_NAME, a.LAST_NAME, countDistinct(s.NAME))
                        .from(a)
                        .join(b).on(b.AUTHOR_ID.equal(a.ID))
                        .join(t).on(t.BOOK_ID.equal(b.ID))
                        .join(s).on(t.NAME.equal(s.NAME))
                        .groupBy(a.FIRST_NAME, a.LAST_NAME)
                        .orderBy(countDistinct(s.NAME).desc())
                        .fetch();

        assertEquals(2, result.size());
        assertEquals("Paulo", result.getValue(0, a.FIRST_NAME));
        assertEquals("George", result.getValue(1, a.FIRST_NAME));

        assertEquals("Coelho", result.getValue(0, a.LAST_NAME));
        assertEquals("Orwell", result.getValue(1, a.LAST_NAME));

        assertEquals(Integer.valueOf(3), result.getValue(0, countDistinct(s.NAME)));
        assertEquals(Integer.valueOf(2), result.getValue(1, countDistinct(s.NAME)));

    }

    @Test
    public void test2() throws Exception {
        String sql = create
                .select(AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME, count())
                .from(AUTHOR)
                .join(BOOK).on(BOOK.AUTHOR_ID.equal(AUTHOR.ID))
                .where(BOOK.LANGUAGE_ID.equal(1))
                .and(BOOK.PUBLISHED_IN.greaterThan(1))
                .groupBy(AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME)
                .having(count().greaterThan(5))
                .orderBy(AUTHOR.LAST_NAME.asc().nullsFirst())
                .limit(1)
                .offset(2)
                .forUpdate().getSQL();
    }

    @Test
    public void test3() throws Exception {
        String sql = create
                .select(field("BOOK.TITLE"), field("AUTHOR.FIRST_NAME"), field("AUTHOR.LAST_NAME"))
                .from(table("BOOK"))
                .join(table("AUTHOR"))
                .on(field("BOOK.AUTHOR_ID").equal(field("AUTHOR.ID")))
                .where(field("BOOK.PUBLISHED_IN").equal(1948))
                .getSQL();
    }
}
