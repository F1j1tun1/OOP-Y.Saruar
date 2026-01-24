package database;

import database.DatabaseConnection;
import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        Connection c = DatabaseConnection.getConnection();

        if (c != null) {
            System.out.println("Connection successful!");
            DatabaseConnection.closeConnection(c);
        }
        else {
            System.out.println("Connection failed!");
        }
    }
}

