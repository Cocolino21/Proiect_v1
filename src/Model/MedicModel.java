package Model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicModel extends BigModel{
    private Connection connection;
    public MedicModel() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proiect_v1", "root", "*DaniPeNet_1");
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }

    public boolean insertInvestigatieInDB(int idRaport, ArrayList<Integer> idServiciu)
    {
        try{
            CallableStatement callableStatement ;
            int rowsAffected;
            for(Integer is : idServiciu) {
                callableStatement = connection.prepareCall(" CALL InsertInvestigatie(?,?);");
                callableStatement.setInt(1, idRaport);
                callableStatement.setInt(2, is);
                rowsAffected = callableStatement.executeUpdate();
                if(rowsAffected == 0){
                    return false;
                }
            }
            return true;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
