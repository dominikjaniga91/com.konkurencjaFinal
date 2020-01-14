package util;

import java.sql.*;

public class Database {

    public static void addToHistory(String action){

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionProvider.getConnection();

            String sqlGetEmployees = "INSERT INTO history" +
                    "( action, time, data)" +
                    "VALUES (?, ?, ?) ";

            preparedStatement = connection.prepareStatement(sqlGetEmployees);

            preparedStatement.setString(1, action);
            preparedStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setDate(3, new Date(System.currentTimeMillis()));
            preparedStatement.executeUpdate();

        } catch (SQLException ex){

            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }finally {
            ConnectionProvider.closeConnection(connection, preparedStatement, null);
        }

    }

}
