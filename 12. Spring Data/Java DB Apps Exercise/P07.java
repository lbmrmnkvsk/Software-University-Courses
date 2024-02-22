package L04_Java_DB_Apps_Exercise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class P07 {
    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "Password00!");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        PreparedStatement statement = connection.prepareStatement(
                "SELECT name from minions");
        ResultSet rs = statement.executeQuery();
        List<String> names = new ArrayList<>();
        while (rs.next()) {
            names.add(rs.getString(1));
        }

        int n = 2;
        while (!names.isEmpty()) {
            if (n % 2 == 0) {
                System.out.println(names.remove(0));
            } else {
                System.out.println(names.remove(names.size() - 1));
            }
            n++;
        }
    }
}
