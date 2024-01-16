package Model;

import java.sql.*;

public class ContabilModel extends BigModel{
    private Connection connection;
    public ContabilModel() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proiect_v1", "root", "");
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }

    public int getSalariuAngajatFromNumePrenume (String nume_prenume) {
        String[] nameParts = nume_prenume.split(" ");
        int aId = -1;
        // Check if the split produced at least two parts (first name and last name)
        if (nameParts.length >= 2) {
            String firstName = nameParts[0];
            String lastName = nameParts[1];
            try {
                CallableStatement callableStatement = connection.prepareCall("SELECT * FROM angajat WHERE nume=? AND prenume = ?");
                callableStatement.setString(1, firstName);
                callableStatement.setString(2, lastName);
                ResultSet resultSet = callableStatement.executeQuery();

                while (resultSet.next()) {
                    aId = resultSet.getInt("salariu_lunar");
                }
                resultSet.close();
                return aId;
            } catch (SQLException e) {
                return -1;
            }
        }
        else
            return -1;
    }
}
