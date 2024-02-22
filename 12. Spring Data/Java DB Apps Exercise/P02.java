package L04_Java_DB_Apps_Exercise;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class P02 {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String user = "root";
        String password = "Password00!";

        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        PreparedStatement statement1 = connection.prepareStatement(
                "SELECT \n" +
                        "    v.name, COUNT(mv.minion_id) AS count\n" +
                        "FROM\n" +
                        "    villains v\n" +
                        "        INNER JOIN\n" +
                        "    minions_villains mv ON v.id = mv.villain_id\n" +
                        "GROUP BY v.name\n" +
                        "HAVING count > 15\n" +
                        "ORDER BY count DESC;"
        );

        ResultSet rs = statement1.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString("name") + " " + rs.getString("count"));
        }
    }
}
