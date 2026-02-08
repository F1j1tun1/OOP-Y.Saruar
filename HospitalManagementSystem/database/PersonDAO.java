package database;

import model.*;
import java.sql.*;
import java.util.ArrayList;

public class PersonDAO {
    public boolean insertPatient(Patient p) {
        String sql = "INSERT INTO people(name, age, department, type, illness) VALUES(?,?,?,?,?)";

        Connection con = DatabaseConnection.getConnection();
        if (con == null) return false;

        try {
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, p.getName());
            statement.setInt(2, p.getAge());
            statement.setString(3, p.getDepartment());
            statement.setString(4, "patient");
            statement.setString(5, p.getIllness());

            return statement.executeUpdate() > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(con);
        }
        return false;
    }
    public boolean insertDoctor(Doctor d) {
        String sql = "INSERT INTO people(name, age, department, type, specialization, experience, salary) VALUES (?, ?, ?, ?, ?, ?, ?)";

        Connection con = DatabaseConnection.getConnection();
        if (con == null) {
            return false;
        }
        try {
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, d.getName());
            statement.setInt(2, d.getAge());
            statement.setString(3, d.getDepartment());
            statement.setString(4, "doctor");
            statement.setString(5, d.getSpecialization());
            statement.setInt(6, d.getExperienceYears());
            statement.setDouble(7, d.getSalary());

            return statement.executeUpdate() > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(con);
        }
        return false;
    }
    public boolean updatePatient(Patient p) {
        String sql = "UPDATE people SET name=?, age=?, department=?, illness=? WHERE id=? AND type='patient'";

        Connection con = DatabaseConnection.getConnection();
        if (con == null) return false;

        try {
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, p.getName());
            statement.setInt(2, p.getAge());
            statement.setString(3, p.getDepartment());
            statement.setString(4, p.getIllness());
            statement.setInt(5, p.getId());

            return statement.executeUpdate() > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(con);
        }
        return false;
    }
    public boolean updateDoctor(Doctor d) {
        String sql = "UPDATE people SET name=?, age=?, department=?, specialization=?, experience=?, salary=? WHERE id=? AND type='doctor'";

        Connection con = DatabaseConnection.getConnection();
        if (con == null) return false;

        try {
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, d.getName());
            statement.setInt(2, d.getAge());
            statement.setString(3, d.getDepartment());
            statement.setString(4, d.getSpecialization());
            statement.setInt(5, d.getExperienceYears());
            statement.setDouble(6, d.getSalary());
            statement.setInt(7, d.getId());

            return statement.executeUpdate() > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(con);
        }
        return false;
    }
    public boolean deletePerson(int id) {
        if (getPersonById(id) == null) {
            return false;
        }
        String sql = "DELETE FROM people WHERE id = ?";
        Connection con = DatabaseConnection.getConnection();
        if (con == null) {
            return false;
        }
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);

            return statement.executeUpdate() > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(con);
        }
        return false;
    }

    public ArrayList<Person> getAllPeople() {
        ArrayList<Person> list = new ArrayList<>();
        String sql = "SELECT * FROM people";

        Connection con = DatabaseConnection.getConnection();
        if (con == null) return list;

        try {
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                list.add(extractPerson(resultSet));
            }

            resultSet.close();
            statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(con);
        }
        return list;
    }
    public ArrayList<Patient> getAllPatients() {
        ArrayList<Patient> list = new ArrayList<>();
        String sql = "SELECT * FROM people WHERE type='patient'";

        Connection con = DatabaseConnection.getConnection();
        if (con == null) return list;

        try {
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Person person = extractPerson(resultSet);
                if (person instanceof Patient) {
                    list.add((Patient) person);
                }
            }

            resultSet.close();
            statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(con);
        }

        return list;
    }
    public ArrayList<Doctor> getAllDoctors() {
        ArrayList<Doctor> list = new ArrayList<>();
        String sql = "SELECT * FROM people WHERE type='doctor'";

        Connection con = DatabaseConnection.getConnection();
        if (con == null) return list;

        try {
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Person person = extractPerson(resultSet);
                if (person instanceof Doctor) {
                    list.add((Doctor) person);
                }
            }

            resultSet.close();
            statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(con);
        }

        return list;
    }

    private Person extractPerson(ResultSet resultSet) throws SQLException {
        String type = resultSet.getString("type");

        if (type.equalsIgnoreCase("doctor")) {
            return new Doctor(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("age"),
                    resultSet.getString("department"),
                    resultSet.getString("specialization"),
                    resultSet.getInt("experience"),
                    resultSet.getDouble("salary")
            );
        }
        else {
            return new Patient(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("age"),
                    resultSet.getString("department"),
                    resultSet.getString("illness")
            );
        }
    }

    public Person getPersonById(int id) {
        String sql = "SELECT * FROM people WHERE id=?";

        Connection con = DatabaseConnection.getConnection();
        if (con == null) return null;

        try {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) return extractPerson(resultSet);

            resultSet.close();
            statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(con);
        }
        return null;
    }
    public ArrayList<Person> searchByName(String name) {
        ArrayList<Person> list = new ArrayList<>();
        String sql = "SELECT * FROM people WHERE name ILIKE ?";

        Connection con = DatabaseConnection.getConnection();
        if (con == null) return list;

        try {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                list.add(extractPerson(resultSet));
            }

            resultSet.close();
            statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(con);
        }
        return list;
    }
    public ArrayList<Person> searchBySalaryRange(double min, double max) {
        ArrayList<Person> list = new ArrayList<>();
        String sql = "SELECT * FROM people WHERE salary BETWEEN ? AND ? ORDER BY salary DESC";

        Connection con = DatabaseConnection.getConnection();
        if (con == null) return list;

        try {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setDouble(1, min);
            statement.setDouble(2, max);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                list.add(extractPerson(resultSet));
            }

            resultSet.close();
            statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(con);
        }
        return list;
    }
    public Doctor getMaxSalaryDoctor() {
        String sql = "SELECT * FROM people WHERE type='doctor' ORDER BY salary DESC LIMIT 1";

        Connection con = DatabaseConnection.getConnection();
        if (con == null) return null;

        try {
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Person person = extractPerson(resultSet);
                if (person instanceof Doctor) {
                    return (Doctor) person;
                }
            }

            resultSet.close();
            statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(con);
        }
        return null;
    }
}
