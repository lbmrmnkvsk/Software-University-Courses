package L04_Java_DB_Apps_Exercise;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class P09 {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine());
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "Password00!");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        CallableStatement statement = connection.prepareCall("CALL usp_get_older(?)");
        statement.setInt(1, id);
        statement.execute();

        PreparedStatement select = connection.prepareStatement(
                "SELECT name, age from minions where id = ?");
        select.setInt(1, id);
        ResultSet rs = select.executeQuery();

        if (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getInt(2));
        } else {
            System.out.println("No such minion");
        }
    }
}
