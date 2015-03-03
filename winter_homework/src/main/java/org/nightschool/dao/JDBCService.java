package org.nightschool.dao;

import java.sql.*;

public class JDBCService
{
    public ResultSet select(String sql) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException
    {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shopping_mall", "twer", "123456");
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        connection.close();
        return resultSet;
    }

    public boolean insert(String sql) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException
    {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shopping_mall", "twer", "123456");
        connection.createStatement().execute(sql);
        connection.close();
        return true;
    }


}
