import database.DatabaseConnection;
import model.*;
import java.sql.*;
import java.util.ArrayList;

public class Preparation {
    public boolean insertPerson(Patient p) {
        String sql = "INSERT INTO people(name, age, department, type, illness) VALUES (?, ?, ?, ?, ?)";

        Connection con = DatabaseConnection.getConnection();
        if (con == null) {
            return false;
        }
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

    public ArrayList<Person> getAllPeople() {
        ArrayList<Person> list = new ArrayList<>();
        String sql = "SELECT * FROM people";

        Connection con = DatabaseConnection.getConnection();
        if (con == null) {
            return list;
        }
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

    public Person extractPerson(ResultSet resultSet) throws SQLException {
        String type = resultSet.getString("type");

        if (type.equalsIgnoreCase("patient")) {
            return new Patient(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("age"),
                    resultSet.getString("department"),
                    resultSet.getString("illness")
            );
        }
        else {
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
    }


}
