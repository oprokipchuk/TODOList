package dao;

import java.sql.Connection;

public class DAO {

    protected static Connection connection;

    public static void setConnection(Connection connection) {
        DAO.connection = connection;
    }

}
