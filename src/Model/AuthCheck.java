package Model;
import Controller.HashIt;

import java.sql.*;
import java.util.ArrayList;

public class AuthCheck extends BigModel{
    private Connection connection;

    public AuthCheck() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proiect_v1", "root", "hd15mky.Mihibosti28");
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }

    public int getFunctieAngajat(String username)
    {
        int id = getAngajatIdFromUsername(username);
        ArrayList<String> temp = getInfoForAngajat(id);
        currentAngajat = new CurrentAngajat(id,temp.get(0),temp.get(1),temp.get(2),temp.get(3),temp.get(4),temp.get(5),temp.get(6),Integer.parseInt(temp.get(7)),temp.get(8),Integer.parseInt(temp.get(9)),temp.get(10),Integer.parseInt(temp.get(12)));
        String functie= currentAngajat.getFunctie();
        switch(functie)
        {
            case "super_admin":
                return 0;
            case "medic":
                return 1;
            case "receptioner":
                return 2;
        }
        return 0;
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




}
