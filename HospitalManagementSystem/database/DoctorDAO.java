package database;

import model.Doctor;
import java.sql.*;

public class DoctorDAO {

    public void insertDoctor(Doctor d) {
        String sql = "INSERT INTO doctor(name, age, department, specialization, experience_years) VALUES (?, ?, ?, ?, ?)";
        Connection c = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setString(1, d.getName());
            statement.setInt(2, d.getAge());
            statement.setString(3, d.getDepartment());
            statement.setString(4, d.getSpecialization());
            statement.setInt(5, d.getExperienceYears());

            statement.executeUpdate();
            statement.close();

            System.out.println("Doctor saved to database.");

        } catch (SQLException e) {
            System.out.println("Insert failed.");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    public void getAllDoctors() {
        String sql = "SELECT * FROM doctor";
        Connection c = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = c.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String department = resultSet.getString("department");
                String specialization = resultSet.getString("specialization");
                int experience = resultSet.getInt("experience_years");

                System.out.println("Doctor (ID: " + id + ", Name: " + name + ", Age: " + age + ", Department: " + department + ", Specialization: " + specialization + ", Experience: " + experience + ").");
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
