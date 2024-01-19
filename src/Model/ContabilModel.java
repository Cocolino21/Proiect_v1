package Model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ContabilModel extends BigModel{
    public class BonFiscal{
    public int idMedic;
    public int sumaIncasata;
    public LocalDate data;
    public int idBon;

    public BonFiscal(int idMedic, int sumaIncasata, LocalDate data, int idBon) {
        this.idMedic = idMedic;
        this.sumaIncasata = sumaIncasata;
        this.data = data;
        this.idBon = idBon;
    }
}
    private Connection connection;
    public ContabilModel() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proiect_v1", "root", "*DaniPeNet_1");
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }
    public int getProfitPeLunaPentruAngajat(int idMedic, String data, String an){
        int nrBonuri = 0;
        Object[][] entries;
        try{
            CallableStatement callableStatement = connection.prepareCall("SELECT * FROM profituri_medic WHERE id_medic = ?");
            callableStatement.setInt(1, idMedic);
            callableStatement.execute();

            ArrayList<BonFiscal> bonFiscalArrayList = new ArrayList<>();
            ResultSet resultSet = callableStatement.getResultSet();

            while (resultSet.next()){
                BonFiscal bonFiscal = new BonFiscal(resultSet.getInt("id_medic"), resultSet.getInt("suma_incasata"), resultSet.getDate("data").toLocalDate(), resultSet.getInt("id_bon"));
                bonFiscalArrayList.add(bonFiscal);
            }
            int rowCount = bonFiscalArrayList.size();
            entries = new Object[rowCount][4];
            int suma = 0;
            int luna;
            int k=0;
            luna = switch (data) {
                case "Ianuarie" -> 1;
                case "Februarie" -> 2;
                case "Martie" -> 3;
                case "Aprilie" -> 4;
                case "Mai" -> 5;
                case "Iunie" -> 6;
                case "Iulie" -> 7;
                case "August" -> 8;
                case "Septembrie" -> 9;
                case "Octombrie" -> 10;
                case "Noiembrie" -> 11;
                case "Decembrie" -> 12;
                default -> 1;
            };
            for(BonFiscal e :bonFiscalArrayList){
                if(e.data.getMonthValue() == luna&& e.data.getYear() == Integer.parseInt(an)){
                    entries[k][0] = e.idMedic;
                    entries[k][1] = e.sumaIncasata;
                    entries[k][2] = e.data;
                    entries[k][3] = e.idBon;
                    suma+=e.sumaIncasata;
                }
            }
            return suma;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public int getProfitPeLunaCentru(String data, String an){
        int nrBonuri = 0;
        Object[][] entries;
        try{
            CallableStatement callableStatement = connection.prepareCall("SELECT * FROM venitlunartotal");
            callableStatement.execute();

            ArrayList<BonFiscal> bonFiscalArrayList = new ArrayList<>();
            ResultSet resultSet = callableStatement.getResultSet();

            while (resultSet.next()){
                BonFiscal bonFiscal = new BonFiscal(-4, resultSet.getInt("suma"), resultSet.getDate("an_luna_zi").toLocalDate(), -1);
                bonFiscalArrayList.add(bonFiscal);
            }
            int rowCount = bonFiscalArrayList.size();
            entries = new Object[rowCount][4];
            int suma = 0;
            int luna;
            int k=0;
            luna = switch (data) {
                case "Ianuarie" -> 1;
                case "Februarie" -> 2;
                case "Martie" -> 3;
                case "Aprilie" -> 4;
                case "Mai" -> 5;
                case "Iunie" -> 6;
                case "Iulie" -> 7;
                case "August" -> 8;
                case "Septembrie" -> 9;
                case "Octombrie" -> 10;
                case "Noiembrie" -> 11;
                case "Decembrie" -> 12;
                default -> 1;
            };
            for(BonFiscal e :bonFiscalArrayList){
                if(e.data.getMonthValue() == luna&& e.data.getYear() == Integer.parseInt(an)){
                    entries[k][0] = e.idMedic;
                    entries[k][1] = e.sumaIncasata;
                    entries[k][2] = e.data;
                    entries[k][3] = e.idBon;
                    suma+=e.sumaIncasata;
                }
            }
            System.out.println(suma);
            return suma;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public int getIdAngajatFromNumePrenume(String fullName)
    {
        String[] nameParts = fullName.split(" ");
        int aId = -1;
        // Check if the split produced at least two parts (first name and last name)
        if (nameParts.length >= 2) {
            String firstName = nameParts[0];
            String lastName = nameParts[1];
            try {
                CallableStatement callableStatement = connection.prepareCall("SELECT * FROM angajat WHERE nume=? AND prenume = ?");
                callableStatement.setString(1, firstName);
                callableStatement.setString(2,lastName);

                ResultSet resultSet = callableStatement.executeQuery();

                while (resultSet.next()) {
                    aId = resultSet.getInt("id_angajat");
                }
                resultSet.close();
                return  aId;
            } catch(SQLException e) {
                return -1;
            }

        }
        else { return -1; }
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
    public int getProfitForMedicId(int idMedic, Date lunaSiAn)
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
    public ArrayList<String> getAllMedicsFromCentru(int idCentru)   {
        ArrayList<String> allMedics = new ArrayList<>();
        try {
            CallableStatement callableStatement = connection.prepareCall("select id_angajat from angajat where functie = 'medic' and id_centru = ?");
            callableStatement.setInt(1, idCentru);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                CallableStatement callableStatement1 = connection.prepareCall("select nume_specializare from specializare_medic where id_medic = ?");
                callableStatement1.setInt(1, resultSet.getInt("id_angajat"));
                ResultSet resultSet1 = callableStatement1.executeQuery();
                while(resultSet1.next()){
                    allMedics.add(resultSet1.getString("nume_specializare"));
                }
            }

        }catch(SQLException e) {
            return null;
        }
        return allMedics;
    }
    public int getProfitForSpecializare(String numeSpecializare, String luna, String an)
    {
        int suma = 0;
        try {
            CallableStatement callableStatement = connection.prepareCall("select id_serviciu from serviciu_specializare where nume_specializare = ?");
            callableStatement.setString(1, numeSpecializare);
            ResultSet resultSet = callableStatement.executeQuery();

            int i=0;

            while(resultSet.next())
            {
                CallableStatement callableStatement1 = connection.prepareCall("select id_investigatie from investigatie where id_serviciu = ?");
                int x = resultSet.getInt("id_serviciu");
                callableStatement1.setInt(1, x);
                ResultSet resultSet1 = callableStatement1.executeQuery();
                while(resultSet1.next())
                {
                    CallableStatement callableStatement2 = connection.prepareCall("select suma_platita from bonurifiscale where id_bon = ?");
                    callableStatement2.setInt(1, resultSet1.getInt("id_investigatie"));
                    ResultSet resultSet2 = callableStatement2.executeQuery();
                    suma += resultSet2.getInt("suma_platita");
                }
                System.out.println(suma);
            }

        } catch(SQLException e) {
            return -1;
        }
        return suma;
    }
    public int getProfitForCentru(int idCentru, String an, String luna )
    {
        int suma = 0;
        try {
            CallableStatement callableStatement = connection.prepareCall("select * from venitlunartotal where venitlunartotal.id_centru = ?");
            callableStatement.setInt(1, idCentru);
            ResultSet resultSet = callableStatement.executeQuery();
            int lunaInt;
            lunaInt = switch (luna) {
                case "Ianuarie" -> 1;
                case "Februarie" -> 2;
                case "Martie" -> 3;
                case "Aprilie" -> 4;
                case "Mai" -> 5;
                case "Iunie" -> 6;
                case "Iulie" -> 7;
                case "August" -> 8;
                case "Septembrie" -> 9;
                case "Octombrie" -> 10;
                case "Noiembrie" -> 11;
                case "Decembrie" -> 12;
                default -> 1;
            };
            while (resultSet.next()) {
                if( resultSet.getDate("an_luna_zi").toLocalDate().getYear() == Integer.parseInt(an) && resultSet.getDate("an_luna_zi").toLocalDate().getMonthValue() == lunaInt)
                {
                    suma = suma + resultSet.getInt("suma");
                }
            }
            CallableStatement callableStatement1 = connection.prepareCall("select * from angajat where id_centru =?");
            callableStatement1.setInt(1,idCentru);
            ResultSet resultSet1 = callableStatement1.executeQuery();
            while (resultSet1.next()) {
                suma = suma - resultSet1.getInt("salariu_lunar");
            }
        } catch(SQLException e) {
            return -1;
        }
        return suma;
    }

}
