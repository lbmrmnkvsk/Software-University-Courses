package L03_Java_DB_Apps_Lab;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class P02_DataRetrieval {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String user = scanner.nextLine();
        String password = scanner.nextLine();

        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/diablo", properties);
        String username = scanner.nextLine();

        PreparedStatement statement = connection.prepareStatement(
                "SELECT u.user_name, u.first_name, u.last_name, count(ug.id) as count " +
                        "FROM users u " +
                "inner join users_games ug on u.id = ug.user_id " +
                        "WHERE u.user_name = ? " +
                        "GROUP BY u.user_name, u.first_name, u.last_name"

        );
        statement.setString(1, username);

        ResultSet rs = statement.executeQuery();

        if (!rs.isBeforeFirst()) {
            System.out.println("No such user exists");
        } else {
            while (rs.next()) {
                System.out.println("User: " + rs.getString("user_name"));
                System.out.printf("%s %s has played %d games%n",
                        rs.getString("u.first_name"), rs.getString("u.last_name"), rs.getInt("count"));
            }
        }

        connection.close();
    }
}
