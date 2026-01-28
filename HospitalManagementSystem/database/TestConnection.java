package database;

import database.DatabaseConnection;
import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        Connection c = DatabaseConnection.getConnection();

        if (c != null) {
            System.out.println("\nConnection successful!");
            DatabaseConnection.closeConnection(c);
        }
        else {
            System.out.println("\nConnection failed!");
        }
    }
}

