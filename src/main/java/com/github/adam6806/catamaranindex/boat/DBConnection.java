package com.github.adam6806.catamaranindex.boat;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() throws NamingException, SQLException {
        Context context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/catamarans");
        Connection connection = dataSource.getConnection();
        return connection;
    }
}
