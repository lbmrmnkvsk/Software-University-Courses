package L04_Java_DB_Apps_Exercise;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class P04 {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String user = "root";
        String password = "Password00!";
        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        String[] tokens = scanner.nextLine().split("\\s+");
        String minionName = tokens[1];
        int minionAge = Integer.parseInt(tokens[2]);
        String town = tokens[3];
        String villain = scanner.nextLine().split("\\s+")[1];

        PreparedStatement townStatement = connection.prepareStatement(
                "SELECT id from towns where name = ?");
        townStatement.setString(1, town);
        ResultSet townResult = townStatement.executeQuery();

        if (!townResult.next()) {
            PreparedStatement insertTown = connection.prepareStatement(
                    "INSERT INTO towns (name) VALUES(?)");
            insertTown.setString(1, town);
            insertTown.executeUpdate();
            System.out.printf("Town %s was added to the database.%n", town);
            insertTown.close();
        }
        townResult.close();
        townStatement.close();

        PreparedStatement villainStatement = connection.prepareStatement(
                "SELECT id from villains where name = ?"
        );
        villainStatement.setString(1, villain);
        ResultSet villainResult = villainStatement.executeQuery();
        if (!villainResult.next()) {
            PreparedStatement insertVillain = connection.prepareStatement(
                    "INSERT INTO villains(name, evilness_factor) VALUES (?, 'evil')"
            );
            insertVillain.setString(1, villain);
            insertVillain.executeUpdate();
            System.out.printf("Villain %s was added to the database.%n", villain);
            insertVillain.close();
        }
        villainStatement.close();
        villainResult.close();

        PreparedStatement insertMinion = connection.prepareStatement(
                "INSERT INTO minions(name, age, town_id) VALUES(?, ?, (SELECT id from towns where name = ?))",
                Statement.RETURN_GENERATED_KEYS
        );
        insertMinion.setString(1, minionName);
        insertMinion.setInt(2, minionAge);
        insertMinion.setString(3, town);
        insertMinion.executeUpdate();
        ResultSet minionKeys = insertMinion.getGeneratedKeys();
        minionKeys.next();
        int minionId = minionKeys.getInt(1);
        minionKeys.close();
        insertMinion.close();

        PreparedStatement insertMinionVillain = connection.prepareStatement(
                "INSERT INTO minions_villains(minion_id, villain_id) VALUES (?, (SELECT id from villains where name = ?))"
        );
        insertMinionVillain.setInt(1, minionId);
        insertMinionVillain.setString(2, villain);
        insertMinionVillain.executeUpdate();
        insertMinionVillain.close();

        System.out.printf("Successfully added %s to be minion of %s", minionName, villain);
        connection.close();
    }
}
