package org.example.poolista6javafx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBuilder {
    private final static String DB_CLASS = "org.mariadb.jdbc.Driver";
    private final static String DB_URL = "jdbc:mariadb://localhost:3306/alunos";
    private final static String DB_USER = "root";
    private final static String DB_PASS = "1234";

    private Connection con = null;
    private static ConnectionBuilder dbConnection = null;

    private ConnectionBuilder() throws ClassNotFoundException {
        Class.forName(DB_CLASS);
    }

    public static ConnectionBuilder getInstance() throws Exception {
        if (dbConnection == null){
            dbConnection = new ConnectionBuilder();
        }
        return dbConnection;
    }

    public Connection getConnection() throws SQLException {
        if (con == null || !con.isValid(3000)) {
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        }
        return this.con;
    }
}
