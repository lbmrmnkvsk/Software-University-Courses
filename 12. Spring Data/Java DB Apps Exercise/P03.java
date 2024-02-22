package L04_Java_DB_Apps_Exercise;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class P03 {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String user = "root";
        String password = "Password00!";
        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);
        int id = Integer.parseInt(scanner.nextLine());

        PreparedStatement villain = connection.prepareStatement(
                "SELECT name FROM villains where id = ?"
        );
        villain.setInt(1, id);
        ResultSet rs1 = villain.executeQuery();

        if (!rs1.next()) {
            System.out.printf("No villain with ID %d exists in the database.%n", id);
            return;
        }

        String villainName = rs1.getString("name");
        System.out.println("Villain: " + villainName);

        PreparedStatement statement = connection.prepareStatement("select m.name, m.age\n" +
                "from minions m\n" +
                "inner join minions_villains mv on m.id = mv.minion_id\n" +
                "where mv.villain_id = ?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        int count = 1;

        while (rs.next()) {
            System.out.printf("%d. %s %d%n",
                    count, rs.getString("name"), rs.getInt("age"));
            count++;
        }
    }
}
