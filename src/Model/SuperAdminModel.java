package Model;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
public class SuperAdminModel extends BigModel {

    private Connection connection;



    public SuperAdminModel()
    {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proiect_v1", "root", "hd15mky.Mihibosti28");
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







}
