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
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, p.getName());
            st.setInt(2, p.getAge());
            st.setString(3, p.getDepartment());
            st.setString(4, "patient");
            st.setString(5, p.getIllness());

            return st.executeUpdate() > 0;
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
        String sql = "INSERT INTO people(name, age, department, type, specialization, experience, salary) VALUES(?,?,?,?,?,?,?)";

        Connection con = DatabaseConnection.getConnection();
        if (con == null) return false;

        try {
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, d.getName());
            st.setInt(2, d.getAge());
            st.setString(3, d.getDepartment());
            st.setString(4, "doctor");
            st.setString(5, d.getSpecialization());
            st.setInt(6, d.getExperienceYears());
            st.setDouble(7, d.getSalary());

            return st.executeUpdate() > 0;
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
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(extractPerson(rs));
            }

            rs.close();
            st.close();
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
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(new Patient(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("department"),
                        rs.getString("illness")
                ));
            }

            rs.close();
            st.close();
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
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(new Doctor(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("department"),
                        rs.getString("specialization"),
                        rs.getInt("experience"),
                        rs.getDouble("salary")
                ));
            }

            rs.close();
            st.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(con);
        }

        return list;
    }
    private Person extractPerson(ResultSet rs) throws SQLException {
        String type = rs.getString("type");

        if (type.equalsIgnoreCase("doctor")) {
            return new Doctor(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("department"),
                    rs.getString("specialization"),
                    rs.getInt("experience"),
                    rs.getDouble("salary")
            );
        }
        else {
            return new Patient(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("department"),
                    rs.getString("illness")
            );
        }
    }

    public boolean updatePatient(Patient p) {
        String sql = "UPDATE people SET name=?, age=?, department=?, illness=? WHERE id=? AND type='patient'";

        Connection con = DatabaseConnection.getConnection();
        if (con == null) return false;

        try {
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, p.getName());
            st.setInt(2, p.getAge());
            st.setString(3, p.getDepartment());
            st.setString(4, p.getIllness());
            st.setInt(5, p.getId());

            return st.executeUpdate() > 0;
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
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, d.getName());
            st.setInt(2, d.getAge());
            st.setString(3, d.getDepartment());
            st.setString(4, d.getSpecialization());
            st.setInt(5, d.getExperienceYears());
            st.setDouble(6, d.getSalary());
            st.setInt(7, d.getId());

            return st.executeUpdate() > 0;
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
        String sql = "DELETE FROM people WHERE id=?";

        Connection con = DatabaseConnection.getConnection();
        if (con == null) return false;

        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);

            return st.executeUpdate() > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(con);
        }
        return false;
    }
    public Person getPersonById(int id) {
        String sql = "SELECT * FROM people WHERE id=?";

        Connection con = DatabaseConnection.getConnection();
        if (con == null) return null;

        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) return extractPerson(rs);

            rs.close();
            st.close();
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
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, "%" + name + "%");

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(extractPerson(rs));
            }

            rs.close();
            st.close();
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
            PreparedStatement st = con.prepareStatement(sql);

            st.setDouble(1, min);
            st.setDouble(2, max);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(extractPerson(rs));
            }

            rs.close();
            st.close();
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
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Doctor(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("department"),
                        rs.getString("specialization"),
                        rs.getInt("experience"),
                        rs.getDouble("salary")
                );
            }

            rs.close();
            st.close();
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
