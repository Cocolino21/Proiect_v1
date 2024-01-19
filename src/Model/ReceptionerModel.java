package Model;

import java.sql.*;
import java.util.ArrayList;

public class ReceptionerModel extends BigModel{
    private Connection connection;


    public ReceptionerModel() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proiect_v1", "root", "*DaniPeNet_1");
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }

    public boolean insertPacient(String nume, String prenume, String cnp, String nr_telefon, String email)
    {
        try{
            CallableStatement callableStatement = connection.prepareCall(" CALL InsertPacient(?, ?, ?, ?,?);");
            callableStatement.setString(1,nume);
            callableStatement.setString(2, prenume);
            callableStatement.setString(3, cnp);
            callableStatement.setString(4, nr_telefon);
            callableStatement.setString(5, email);
            int rowsAffected = callableStatement.executeUpdate();

            if (rowsAffected > 0) {
                // Successfully inserted
                return true;
            } else {
                // Insertion failed
                return false;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }


    public int getPretServiciuForServiciuIdAndMedic(int id_serviciu, int id_medic)
    {
        int temp = 0;
        try {
            String query = "SELECT * FROM `proiect_v1`.`personalizare_servicii_medic` WHERE `id_medic`=? ANd `id_serviciu` = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_medic);
            preparedStatement.setInt(2,id_serviciu);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                temp = resultSet.getInt("pret");
            }


            return temp;

        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as per your requirement
            return 0;
        }
    }

    public int calculeazaSumaPlatita(int id_raport, int id_medic)
    {
        int sum = 0;
        ArrayList<String> temp = getServiciiForRaport(id_raport);
        ArrayList<Integer> temp2 = new ArrayList<>();
        for(String s : temp)
        {
            temp2.add(getServiciuIdFromServiciuNume(s));
        }
        for(Integer i : temp2)
        {
            sum = sum + getPretServiciuForServiciuIdAndMedic(i, id_medic);
        }

        return sum;

    }
    public boolean insertBon(int id_raport, int suma_platita, java.sql.Date data_emitere)
    {
        try{

           /* System.out.println(id_medic);
            System.out.println(id_receptioner);*/

            CallableStatement callableStatement = connection.prepareCall(" CALL InsertBon(?, ?, ?);");
            callableStatement.setInt(1,id_raport);
            callableStatement.setInt(2, suma_platita);
            callableStatement.setDate(3, data_emitere);
            int rowsAffected = callableStatement.executeUpdate();

            if (rowsAffected > 0) {
                // Successfully inserted
                return true;
            } else {
                // Insertion failed
                return false;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }




    // Stergere programare

    public boolean deleteProgramareFromDB(int id_programare)
    {
        try{
           // System.out.println(id_programare);
            CallableStatement callableStatement = connection.prepareCall(" CALL DeleteProgramare(?);");
            callableStatement.setInt(1, id_programare);
            int rowsAffected = callableStatement.executeUpdate();

            if (rowsAffected > 0) {
                // Successfully deleted

                return true;
            } else {
                // delete failed

                return false;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }


}
