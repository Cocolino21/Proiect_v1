package Model;

import java.sql.*;
import java.util.ArrayList;

public class ContabilModel extends BigModel{
    private Connection connection;
    public ContabilModel() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proiect_v1", "root", "");
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }

    public int getSalariuAngajatFromNumePrenume (String nume_prenume) {
        String[] nameParts = nume_prenume.split(" ");
        int aId = -1;
        // Check if the split produced at least two parts (first name and last name)
        if (nameParts.length >= 2) {
            String firstName = nameParts[0];
            String lastName = nameParts[1];
            try {
                CallableStatement callableStatement = connection.prepareCall("SELECT * FROM angajat WHERE nume=? AND prenume = ?");
                callableStatement.setString(1, firstName);
                callableStatement.setString(2, lastName);
                ResultSet resultSet = callableStatement.executeQuery();

                while (resultSet.next()) {
                    aId = resultSet.getInt("salariu_lunar");
                }
                resultSet.close();
                return aId;
            } catch (SQLException e) {
                return -1;
            }
        }
        else
            return -1;
    }

    public ArrayList<String> getSpecializariFromMedicId(int idMedic)
    {
        ArrayList<String> al = new ArrayList<>();

        try {
            CallableStatement callableStatement = connection.prepareCall("SELECT * FROM specializare_medic WHERE id_medic = ?");
            callableStatement.setInt(1, idMedic);
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                String nume_angajat = resultSet.getString("nume_specializare");
                if(!al.contains(nume_angajat))
                    al.add(nume_angajat);
            }
            resultSet.close();
        } catch(SQLException e) {
            al.add("");
        }
        return al;
    }
    public int getProfitForMedicId(int idMedic, java.sql.Date lunaSiAn)
    {
        int suma = 0;

        try {
            CallableStatement callableStatement1 = connection.prepareCall("CALL GetProfituriMedicByData(?, ?)");
            callableStatement1.setInt(1, idMedic);
            callableStatement1.setDate(2, lunaSiAn);
            ResultSet resultSet1 = callableStatement1.executeQuery();
            while (resultSet1.next()) {
                CallableStatement callableStatement2 = connection.prepareCall("select suma_platita from bonurifiscale where id_bon = ?");
                callableStatement2.setInt(1, resultSet1.getInt("id_bon"));
                ResultSet resultSet2 = callableStatement2.executeQuery();
                suma = suma + resultSet2.getInt("suma_platita");
                suma = suma - resultSet1.getInt("suma_incasata");
                resultSet2.close();
            }
            CallableStatement callableStatement3 = connection.prepareCall("select * from angajat where id_angajat = ?");
            callableStatement3.setInt(1,idMedic);
            ResultSet resultSet3 = callableStatement3.executeQuery();
            suma = suma - resultSet3.getInt("salariu");
            resultSet3.close();
            resultSet1.close();
        } catch(SQLException e) {
            return -1;
        }
        return suma;
    }

    public int getProfitForSpecializare(String numeSpec, java.sql.Date lunaSiAn)
    {
        int suma = 0;

        try {
            CallableStatement callableStatement = connection.prepareCall("select * from specializare_medic where nume_specializare = ?");
            callableStatement.setString(1, numeSpec);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                suma = suma + getProfitForMedicId(resultSet.getInt("id_medic"),lunaSiAn);
            }
            resultSet.close();
        } catch(SQLException e) {
            return -1;
        }
        return suma;
    }

    public int getProfitForCentru(int idCentru, java.sql.Date lunaSiAn)
    {
        int suma = 0;

        try {
            CallableStatement callableStatement = connection.prepareCall("CALL GetProfituriForCentru(?,?)");
            callableStatement.setInt(1, idCentru);
            callableStatement.setDate(2,lunaSiAn);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                suma = suma + resultSet.getInt("suma");
            }
            CallableStatement callableStatement1 = connection.prepareCall("select * from angajat where id_centru =?");
            callableStatement1.setInt(1,idCentru);
            ResultSet resultSet1 = callableStatement1.executeQuery();
            while (resultSet1.next()) {
                suma = suma - resultSet.getInt("salariu");
            }
            resultSet.close();
            resultSet1.close();
        } catch(SQLException e) {
            return -1;
        }
        return suma;
    }

}
