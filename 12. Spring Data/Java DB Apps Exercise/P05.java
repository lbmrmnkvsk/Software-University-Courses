package L04_Java_DB_Apps_Exercise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class P05 {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties properties = new Properties();
        String user = "root";
        String password = "Password00!";
        properties.setProperty("user", user);
        properties.setProperty("password", password);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);
        String country = scanner.nextLine();

        PreparedStatement updateStatement = connection.prepareStatement(
                "UPDATE towns SET name = upper(name) where country = ?");
        updateStatement.setString(1, country);
        int townsAffected = updateStatement.executeUpdate();

        if (townsAffected <= 0) {
            System.out.println("No town names were affected.");
        } else {
            System.out.printf("%d town names were affected.%n", townsAffected);
            PreparedStatement select = connection.prepareStatement(
                    "SELECT name FROM towns where country = ?");
            select.setString(1, country);
            ResultSet rs = select.executeQuery();

            List<String> result = new ArrayList<>();
            while (rs.next()) {
                result.add(rs.getString("name"));
            }
            System.out.println(String.join(", ", result));
        }

        connection.close();
    }
}
