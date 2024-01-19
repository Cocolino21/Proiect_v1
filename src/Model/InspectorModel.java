package Model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class InspectorModel extends BigModel{
    private Connection connection;
    public InspectorModel() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proiect_v1", "root", "*DaniPeNet_1");
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }

    public Object[][] getCereriForResurse(int idAngajat)
    {

        Object[][] entries;
        try {
            CallableStatement callableStatement = connection.prepareCall("CALL GetCereriConcediuByAngajatId(?, ?)");
            callableStatement.setInt(1, idAngajat);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.execute();
            ResultSet resultSet = callableStatement.getResultSet();
            int rowCount = callableStatement.getInt(2);
            //System.out.println(rowCount);
            int k=0;
            entries = new Object[rowCount][4];
            while (resultSet.next()) {

                entries[k][0] = resultSet.getInt("id_angajat");
                entries[k][1] = resultSet.getDate("data_inceput");
                entries[k][2] = resultSet.getDate("data_sfarsit");
                String motiv = resultSet.getString("motiv");
                entries[k][3] = motiv;
                k++;
            }

            return entries;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
