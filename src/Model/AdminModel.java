package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdminModel extends BigModel{
    private Connection connection;
    public AdminModel() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proiect_v1", "root", "");
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }

}
