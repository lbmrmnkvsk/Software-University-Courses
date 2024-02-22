package L04_Java_DB_Apps_Exercise;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class P06 {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine());
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "Password00!");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        connection.setAutoCommit(false);
        String name = null;
        PreparedStatement select = connection.prepareStatement(
                "SELECT name from villains where id = ?");
        select.setInt(1, id);
        ResultSet rs = select.executeQuery();
        if (rs.next()) {
            name = rs.getString("name");
        }

        if (name == null) {
            System.out.println("No such villain was found");
        } else {
            try {
                PreparedStatement releaseMinions = connection.prepareStatement(
                        "DELETE FROM minions_villains where villain_id = ?");
                releaseMinions.setInt(1, id);
                int releasedMinions = releaseMinions.executeUpdate();

                PreparedStatement deleteVillain = connection.prepareStatement(
                        "DELETE FROM villains where id = ?");
                deleteVillain.setInt(1, id);
                deleteVillain.executeUpdate();

                connection.commit();
                System.out.printf("%s was deleted%n", name);
                System.out.printf("%d minions released%n", releasedMinions);
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }

        connection.close();
    }
}
