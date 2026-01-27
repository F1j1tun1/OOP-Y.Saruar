package database;

import model.Patient;
import java.sql.*;

public class PatientDAO {
    public void insertPatient(Patient p) {
        String sql = "INSERT INTO patient(name, age, department, illness) VALUES (?, ?, ?, ?)";
        Connection c = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setString(1, p.getName());
            statement.setInt(2, p.getAge());
            statement.setString(3, p.getDepartment());
            statement.setString(4, p.getIllness());

            statement.executeUpdate();
            statement.close();

            System.out.println("Patient saved to database.");

        } catch (SQLException e) {
            System.out.println("Insert failed.");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    public void getAllPatients() {
        String sql = "SELECT * FROM patient";
        Connection c = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = c.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String department = resultSet.getString("department");
                String illness = resultSet.getString("illness");

                System.out.println("Patient (ID: " + id + ", Name: " + name + ", Age: " + age + ", Department: " + department + ", Illness: " + illness + ").");

            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println("Select failed.");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }
}
