package Model;
import Controller.HashIt;

import java.sql.*;
import java.util.ArrayList;

public class AuthCheck {
    private Connection connection;

    public AuthCheck() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proiect_v1", "root", "hd15mky.Mihibosti28");
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }

    public boolean CheckLoginInformation(String username, String password, String centru)
    {
        String hashed = HashIt.hashThePass(password);

        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL VerifyUser(?, ?, ?,?)}");
            callableStatement.setString(1, username);
            callableStatement.setString(2, hashed);
            callableStatement.setString(3,centru);

            callableStatement.registerOutParameter(4, Types.BOOLEAN);
            callableStatement.execute();
            return callableStatement.getBoolean(4);
        }
        catch(SQLException e) {
            return false;
        }

    }


    public ArrayList<String> getCenters() {
        ArrayList<String> al = new ArrayList<>();

        try {
            CallableStatement callableStatement = connection.prepareCall("SELECT * FROM Centru");
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                String nume_centru = resultSet.getString("nume_centru");
                al.add(nume_centru);
            }
            resultSet.close();
        } catch(SQLException e) {
            al.add("");
        }

        return al;
    }

}
