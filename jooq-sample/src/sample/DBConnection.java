package sample;

import java.sql.Connection;
import java.sql.DriverManager;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

public class DBConnection {

    private static final String userName = "root";
    private static final String password = "test";
    private static final String url = "jdbc:mysql://localhost:3306/jooq-test";

    public static DSLContext createDSL() throws Exception {
        Connection conn = null;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        conn = DriverManager.getConnection(url, userName, password);

        return DSL.using(conn, SQLDialect.MYSQL);
    }
}
