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

    public boolean insertProgramare(int id_pacient, int id_medic, int id_receptioner, java.sql.Date date, java.sql.Time time )
    {
        try{
           /* System.out.println(id_medic);
            System.out.println(id_receptioner);*/
            CallableStatement callableStatement = connection.prepareCall(" CALL InsertProgramare(?, ?, ?, ?,?);");
            callableStatement.setTime(1,time);
            callableStatement.setDate(2, date);
            callableStatement.setInt(3, id_medic);
            callableStatement.setInt(4, id_receptioner);
            callableStatement.setInt(5, id_pacient);
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

    public boolean insertInvestigatieInDB(int idRaport, ArrayList<Integer> idServiciu)
    {
        try{
            CallableStatement callableStatement = connection.prepareCall(" CALL InsertInvestigatie(?,?);");
            callableStatement.setInt(1, idRaport);
            int rowsAffected = callableStatement.executeUpdate();

            Array idServiciiArray = connection.createArrayOf("INT", idServiciu.toArray());
            callableStatement.setArray(2, idServiciiArray);
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Integer> getServiciuIdFromServiciuNume(ArrayList<String> numeServiciu)
    {
        ArrayList<Integer> idServicii = new ArrayList<>();

        try {
            CallableStatement statement = connection.prepareCall("CALL IdFromNumeServiciu(?)");

            for (String nume : numeServiciu) {
                statement.setString(1, nume);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    int idServiciu = resultSet.getInt("id_serviciu");
                    idServicii.add(idServiciu);
                }
                resultSet.close();
            }

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idServicii;
    }


}
