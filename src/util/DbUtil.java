package util;

import java.sql.*;

public class DbUtil {
    private static final String url = "jdbc:mysql://localhost:3306/userdb?useUnicode=true&characterEncoding=UTF-8";
    private static final String user = "root";
    private static final String password = "xkmSql2019";
//    private static final String password = "312430.xy";
    private static Connection connection = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    public static synchronized Connection getConnection() throws SQLException {
        if (connection != null)
            close();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static Statement getStatement() throws SQLException {
        statement = getConnection().createStatement();
        return statement;
    }

    private static PreparedStatement getPrepareStatement(String sql, Object... args) throws SQLException {
        preparedStatement = getConnection().prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(1 + i, args[i]);
        }
        return preparedStatement;
    }

    public static int executeUpdate(String sql) throws SQLException {
        return getStatement().executeUpdate(sql);
    }

    public static int executeUpdate(String sql, Object... args) throws SQLException {
        return getPrepareStatement(sql, args).executeUpdate();
    }

    public static ResultSet executeQuery(String sql) throws SQLException {
        resultSet = getStatement().executeQuery(sql);
        return resultSet;
    }

    public static ResultSet executeQuery(String sql, Object... args) throws SQLException {
        resultSet = getPrepareStatement(sql, args).executeQuery();
        return resultSet;
    }

    //事务回滚
    public static void rollback() throws SQLException {
        getConnection().rollback();
    }

    public static void close() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}