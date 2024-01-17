package Model;

import java.sql.*;
import java.util.ArrayList;


public class AdminModel extends BigModel{
    private Connection connection;
    public AdminModel() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proiect_v1", "root", "");
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }
    @Override
    public Object[][] getAngajati(int centru_id)
    {

        Object[][] entries;
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL GetAngajatiByCentruId(?, ?)}");
            callableStatement.setInt(1, centru_id);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.execute();
            ResultSet resultSet = callableStatement.getResultSet();
            int rowCount = callableStatement.getInt(2);

            int k=0;
            entries = new Object[rowCount][6];
            while (resultSet.next()) {

                entries[k][0] =  resultSet.getInt("id_angajat");
                entries[k][1] = resultSet.getString("nume");
                entries[k][2] = resultSet.getString("prenume");
                String functie = resultSet.getString("functie");
                entries[k][3] = functie;
                entries[k][4] = resultSet.getString("numar_contract");
                entries[k][5] = resultSet.getString("nr_telefon");
                //   System.out.println(entries[k][1].toString());
                k++;
            }

            return entries;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as per your requirement
            return null;
        }
    }
    @Override
    public ArrayList<String> getDepts() {
        ArrayList<String> al = new ArrayList<>();

        try {
            CallableStatement callableStatement = connection.prepareCall("SELECT * FROM functie_dept where dept != 'super' ");

            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                String nume_centru = resultSet.getString("dept");
                if(!al.contains(nume_centru))
                    al.add(nume_centru);
            }
            resultSet.close();
        } catch(SQLException e) {
            al.add("");
        }

        return al;
    }

}
