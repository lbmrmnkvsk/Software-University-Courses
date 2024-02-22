package L04_Java_DB_Apps_Exercise;

import java.sql.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

public class P08 {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int[] ids = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "Password00!");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        PreparedStatement update = connection.prepareStatement(
                "UPDATE minions SET age = age + 1, name = lower(name) where id = ?");
        for (int id : ids) {
            update.setInt(1, id);
            update.executeUpdate();
        }

        PreparedStatement select = connection.prepareStatement(
                "SELECT name, age FROM minions");
        ResultSet rs = select.executeQuery();
        while (rs.next()) {
            System.out.printf("%s %d%n", rs.getString(1), rs.getInt(2));
        }
    }
}
