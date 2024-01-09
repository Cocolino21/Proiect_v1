package Model;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
public class SuperAdminModel extends BigModel {

    private Connection connection;



    public SuperAdminModel()
    {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proiect_v1", "root", "");
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }

    public boolean deleteCentruFromDB(int id_centru) {
        try {
            String query = "DELETE FROM `proiect_v1`.`centru` WHERE `id_centru` = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_centru);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                // Successfully deleted
                return true;
            } else {
                // fails
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as per your requirement
            return false;
        }
    }



    public boolean insertCentruIntoDB(String nume_centru, String adresa_centru, String program)
    {
        try {
            String query = "INSERT INTO `proiect_v1`.`centru` (`nume_centru`, `adresa`, `program`) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nume_centru);
            preparedStatement.setString(2, adresa_centru);
            preparedStatement.setString(3, program);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                // Successfully inserted
                return true;
            } else {
                // Insertion failed
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as per your requirement
            return false;
        }
    }




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
            entries = new Object[rowCount][9];
            while (resultSet.next()) {

                entries[k][0] =  resultSet.getInt("id_angajat");
                entries[k][1] = resultSet.getString("nume");
                entries[k][2] = resultSet.getString("prenume");
                String functie = resultSet.getString("functie");
                entries[k][3] = functie;
                entries[k][4] = getDeptForFunctie(functie);
                entries[k][5] = resultSet.getString("username");
                entries[k][6] = resultSet.getString("numar_contract");
                entries[k][7] = resultSet.getString("nr_telefon");
                entries[k][8] = resultSet.getInt("id_centru");
                //   System.out.println(entries[k][1].toString());
                k++;
            }

            return entries;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as per your requirement
            return null;
        }
    }



    public Object[][] getCentre()
    {

        Object[][] entries;
        try {

            String query = "SELECT * FROM centru";

            String countQuery = "SELECT COUNT(*) AS row_count FROM centru";
            PreparedStatement countStatement = connection.prepareStatement(countQuery);
            ResultSet countResultSet = countStatement.executeQuery();

            int rowCount = 0;
            if (countResultSet.next()) {
                rowCount = countResultSet.getInt("row_count");
            }
            entries = new Object[rowCount][4];

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();


            int k=0;

            while (resultSet.next()) {

                entries[k][0] =  resultSet.getInt("id_centru");
                entries[k][1] = resultSet.getString("nume_centru");
                entries[k][2] = resultSet.getString("adresa");
                entries[k][3] = resultSet.getString("program");

             //   System.out.println(entries[k][1].toString());
                k++;
            }

            return entries;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as per your requirement
            return null;
        }
    }


}
