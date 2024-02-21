package L03_Java_DB_Apps_Lab;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class P01_Connection {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username default (root): ");
        String user = scanner.nextLine();
        if (user.isEmpty()) {
            user = "root";
        }
        System.out.println("Enter password default (empty):");
        String password = scanner.nextLine().trim();

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni", props);

        PreparedStatement statement = connection.prepareStatement(
                "SELECT first_name, last_name FROM employees WHERE salary > ?"
        );
        double salary = Double.parseDouble(scanner.nextLine());
        statement.setDouble(1, salary);

        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("first_name") + " " + rs.getString("last_name"));
        }

        connection.close();
    }
}
