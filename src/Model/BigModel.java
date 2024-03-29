package Model;
import Controller.HashIt;
import com.mysql.cj.protocol.a.LocalDateTimeValueEncoder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class BigModel {
    private Connection connection;

    protected static CurrentAngajat currentAngajat;

    public CurrentAngajat getCurrentAngajat() {
        return currentAngajat;
    }

    public BigModel() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proiect_v1", "root", "*DaniPeNet_1");
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }


    public int getIdMedicFromIdProgramare(int id_programare)
    {
        int id_medic=-1;
        try {
            String query = "SELECT * FROM `proiect_v1`.`programare` WHERE `id_programare` = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_programare);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                id_medic = resultSet.getInt("id_medic");
            }
            return id_medic;



        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as per your requirement
            return -1;
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


    public ArrayList<Time> getOrarForSpecificAngajatId(int id_angajat, String ziua_saptamanii)
    {

        ArrayList<Time> temp = new ArrayList<Time>();
        try {
            String query = "SELECT * FROM `proiect_v1`.`orar` WHERE `id_angajat` = ? AND `ziua_saptamanii` = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_angajat);
            preparedStatement.setString(2,ziua_saptamanii);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                temp.add(resultSet.getTime("moment_inceput"));
                temp.add(resultSet.getTime("moment_sfarsit"));
            }

            return temp;



        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as per your requirement
            return null;
        }
    }

    public LocalDate getConcediuBegin(int idAngajat){
        try {
            String query = "SELECT * FROM `proiect_v1`.`concediu` WHERE `id_angajat` = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idAngajat);
            ResultSet resultSet = preparedStatement.executeQuery();
            LocalDate now = LocalDate.now();
            LocalDate minim = LocalDate.parse("9999-12-23");
            while (resultSet.next()){
                if( resultSet.getDate("data_inceput").toLocalDate().isAfter(now)){
                    if( resultSet.getDate("data_inceput").toLocalDate().isBefore(minim)){
                        minim = resultSet.getDate("data_inceput").toLocalDate();
                    }
                }
            }
            return minim;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as per your requirement
            return null;
        }
    }
    public LocalDate getConcediuEnd(int idAngajat){
        try {
            String query = "SELECT * FROM `proiect_v1`.`concediu` WHERE `id_angajat` = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idAngajat);
            ResultSet resultSet = preparedStatement.executeQuery();
            LocalDate now = LocalDate.now();
            LocalDate minim = LocalDate.parse("9999-12-23");
            LocalDate minimEnd = LocalDate.parse("9999-12-04");
            while (resultSet.next()){
                if( resultSet.getDate("data_inceput").toLocalDate().isAfter(now)){
                    if( resultSet.getDate("data_inceput").toLocalDate().isBefore(minim)){
                        minim = resultSet.getDate("data_inceput").toLocalDate();
                        minimEnd = resultSet.getDate("data_sfarsit").toLocalDate();
                    }
                }
            }
            return minimEnd;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as per your requirement
            return null;
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

    public Object[][] getAngajatiForResurse(int centru_id)
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
            entries = new Object[rowCount][4];
            while (resultSet.next()) {

                entries[k][0] =  resultSet.getInt("id_angajat");
                entries[k][1] = resultSet.getString("nume");
                entries[k][2] = resultSet.getString("prenume");
                String functie = resultSet.getString("functie");
                entries[k][3] = functie;
                k++;
            }

            return entries;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as per your requirement
            return null;
        }
    }



    public ArrayList<String> getPacientNumePrenumeFromId(int id_pacient)
    {
        ArrayList<String> temp = new ArrayList<String>();
        try {
            String query = "SELECT * FROM `proiect_v1`.`pacient` WHERE `id_pacient` = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_pacient);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                temp.add(resultSet.getString("nume"));
                temp.add(resultSet.getString("prenume"));

            }
            return temp;



        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as per your requirement
            return null;
        }
    }






    public void sortTable(JTable table, int columnIndex) {
        TableRowSorter<TableModel> sorter = (TableRowSorter<TableModel>) table.getRowSorter();

        if (sorter == null) {
            sorter = new TableRowSorter<>(table.getModel());
            table.setRowSorter(sorter);
        }

        // Sort by the specified column index
        sorter.setSortKeys(Collections.singletonList(new RowSorter.SortKey(columnIndex, SortOrder.ASCENDING)));

        // Apply the sorting to the table
        sorter.sort();
    }


    public ArrayList<String> getServiciiForRaport(int id_investigatie)
    {
        ArrayList<String> numeServAL = new ArrayList<>();

        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL GetServiciiMedicaleForInvestigatie(?)}");
            callableStatement.setInt(1, id_investigatie);
            callableStatement.execute();

            ResultSet resultSet = callableStatement.getResultSet();
            while(resultSet.next())
            {
                numeServAL.add(resultSet.getString("nume_serviciu_result"));
            }
            return numeServAL;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public int getServiciuIdFromServiciuNume(String serviciuNume)
    {
        int temp = -1;
        try {
            String query = "SELECT * FROM `proiect_v1`.`serviciumedical` WHERE `nume_serviciu` = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, serviciuNume);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                temp = resultSet.getInt("id_serviciu");
            }

            return temp;

        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as per your requirement
            return -1;
        }
    }



    public Object[][] getProgramari(int id_angajat) {
        Object[][] entries;
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL SelectProgramareByReceptioner(?, ?)}");
            callableStatement.setInt(1, id_angajat);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.execute();

            int rowCount = callableStatement.getInt(2);

            ResultSet resultSet = callableStatement.getResultSet();
            int k = 0;
            entries = new Object[rowCount][9];
            while (resultSet.next()) {

                entries[k][0] = resultSet.getInt("id_pacient");
                entries[k][1] = getPacientNumePrenumeFromId((Integer) entries[k][0]).getFirst();
                entries[k][2] = getPacientNumePrenumeFromId((Integer) entries[k][0]).getLast();
                entries[k][3] = resultSet.getDate("data_programare");
                entries[k][4] = resultSet.getTime("ora");
                int id_m = resultSet.getInt("id_medic");
                entries[k][5] = getInfoForAngajat(id_m).get(0);
                entries[k][6] = getInfoForAngajat(id_m).get(1);
                entries[k][7] = resultSet.getBoolean("finalizat");
                entries[k][8] = resultSet.getInt("id_programare");
                k++;
            }


            return entries;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as per your requirement
            return null;
        }
    }

    public Object[][] getProgramariForMedic(int id_angajat) {
        Object[][] entries;
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL SelectProgramareByMedic(?, ?)}");
            callableStatement.setInt(1, id_angajat);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.execute();

            int rowCount = callableStatement.getInt(2);

            ResultSet resultSet = callableStatement.getResultSet();
            int k = 0;
            entries = new Object[rowCount][7];
            while (resultSet.next()) {
                if(!resultSet.getBoolean("finalizat")) {
                    entries[k][0] = resultSet.getInt("id_pacient");
                    entries[k][1] = getPacientNumePrenumeFromId((Integer) entries[k][0]).getFirst();
                    entries[k][2] = getPacientNumePrenumeFromId((Integer) entries[k][0]).getLast();
                    entries[k][3] = resultSet.getDate("data_programare");
                    entries[k][4] = resultSet.getTime("ora");
                    entries[k][5] = resultSet.getBoolean("finalizat");
                    entries[k][6] = resultSet.getInt("id_programare");
                    k++;
                }
            }


            return entries;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as per your requirement
            return null;
        }
    }

    public boolean updateInTablePersonalizareServiciiMedic(int id_medic, int id_serviciu, int pretNou, int durataMinNoua)
    {
        try {
            String query = "UPDATE personalizare_servicii_medic SET pret = ?, durata_min = ? WHERE id_medic = ? AND id_serviciu = ?;\n";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, pretNou);
            preparedStatement.setInt(2,durataMinNoua);
            preparedStatement.setInt(3,id_medic);
            preparedStatement.setInt(4,id_serviciu);

            int rowsAffected = preparedStatement.executeUpdate();

            if(rowsAffected>0)
                return true;
            else
                return false;


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getPretForMedicServiciu(int id_medic, int id_serviciu)
    {
        try {
            String query = "SELECT * FROM personalizare_servicii_medic WHERE id_medic = ? AND id_serviciu = ?;\n";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id_medic);
            preparedStatement.setInt(2,id_serviciu);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while(resultSet.next())
            {
                return resultSet.getInt("pret");
            }

            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getDurataMinForMedicServiciu(int id_medic, int id_serviciu)
    {
        try {
            String query = "SELECT * FROM personalizare_servicii_medic WHERE id_medic = ? AND id_serviciu = ?;\n";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id_medic);
            preparedStatement.setInt(2,id_serviciu);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while(resultSet.next())
            {
                return resultSet.getInt("durata_min");
            }

            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }




    public ArrayList<String>  getServiciiForMedic(int id_medic)
    {
        ArrayList<String> temp = new ArrayList<String>();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL GetServicesForMedic(?)}");
            callableStatement.setInt(1, id_medic);
            callableStatement.execute();
            ResultSet resultSet = callableStatement.getResultSet();

            while (resultSet.next())
            {
                temp.add(resultSet.getString("nume_serviciu_result"));
            }
            return temp;



        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as per your requirement
            return null;
        }
    }




    public String getRezultatFromIdAnaliza(int idAnaliza) // dau push la mihnea
    {
        String temp = new String();
        try {
            String query = "SELECT * FROM `proiect_v1`.`raportanaliza` WHERE `id_analiza` = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idAnaliza);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                temp = resultSet.getString("rezultat");
            }
            return temp;



        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object[][] getRaportAnalizaForMedic(int idPacient) {
        Object[][] entries;
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL SelectRaportAnaliza(?)}");
            callableStatement.setInt(1, idPacient);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.execute();

            int rowCount = callableStatement.getInt(2);

            ResultSet resultSet = callableStatement.getResultSet();
            int k = 0;
            entries = new Object[rowCount][4];
            while (resultSet.next()) {

                entries[k][0] = resultSet.getInt("id_analiza");
                entries[k][1] = resultSet.getInt("id_pacient");
                entries[k][2] = getRezultatFromIdAnaliza((Integer) entries[k][0]);
                entries[k][3] = resultSet.getInt("id_asistent");
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


    public void setCurrentAngajat(CurrentAngajat currentAngajat) {
        this.currentAngajat = currentAngajat;
    }

    public boolean deleteAngajatFromDB(int id_angajat) {
        try {


            CallableStatement callableStatement = connection.prepareCall(" CALL DeleteAngajat(?);");
            callableStatement.setInt(1, id_angajat);

            int rowsAffected = callableStatement.executeUpdate();

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


    public boolean deleteCerereFromDB(int id_angajat, java.sql.Date data_inceput) {
        try {

            CallableStatement callableStatement = connection.prepareCall(" CALL DeleteCerere(?,?);");
            callableStatement.setInt(1, id_angajat);
            callableStatement.setDate(2, data_inceput);
            int rowsAffected = callableStatement.executeUpdate();

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


    public boolean insertCerereConcediu(int currentID, java.sql.Date beginDate, java.sql.Date endDate, String motiv)
    {
        try{
            CallableStatement callableStatement = connection.prepareCall(" CALL InsertCerereConcediu(?, ?, ?, ?);");
            callableStatement.setInt(1,currentID);
            callableStatement.setDate(2, beginDate);
            callableStatement.setDate(3, endDate);
            callableStatement.setString(4, motiv);
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


    public boolean insertInRaportAnaliza(int idPacient, String rezultat,int idAsistent)
    {
        try{
            CallableStatement callableStatement = connection.prepareCall(" CALL InsertInRaportAnaliza(?, ?, ?);");
            callableStatement.setInt(1, idPacient);
            callableStatement.setString(2, rezultat);
            callableStatement.setInt(3, idAsistent);
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



    public ArrayList<String> getInfoForAngajat(int id_angajat)
    {

        ArrayList<String> temp = new ArrayList<String>();
        try {
            String query = "SELECT * FROM `proiect_v1`.`angajat` WHERE `id_angajat` = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_angajat);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                temp.add(resultSet.getString("nume"));
                temp.add(resultSet.getString("prenume"));
                temp.add(resultSet.getString("cnp"));
                temp.add(resultSet.getString("adresa"));
                temp.add(resultSet.getString("nr_telefon"));
                temp.add(resultSet.getString("email"));
                temp.add(resultSet.getString("iban"));
                temp.add(Integer.valueOf(resultSet.getInt("numar_contract")).toString());
                temp.add(resultSet.getString("functie"));
                temp.add(Integer.valueOf(resultSet.getInt("salariu_lunar")).toString());
                temp.add(resultSet.getString("username"));
                temp.add(resultSet.getString("parola"));
                temp.add(Integer.valueOf(resultSet.getInt("id_centru")).toString());

            }
            return temp;



        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as per your requirement
            return null;
        }
    }

    public boolean updateMedic(int id_angajat, String nume, String prenume, String cnp, String adresa, String nrTelefon, String eMail, String iBan, String nrContract, String functie, String salariuLunar, String username, String parola, String id_centru, String codParafa, String titluStiintific, String postDidactic, int procent){
        if(nume.isEmpty()||prenume.isEmpty()||cnp.isEmpty()||adresa.isEmpty()||nrTelefon.isEmpty()||eMail.isEmpty()||iBan.isEmpty()||nrContract.isEmpty()||functie.isEmpty()||salariuLunar.isEmpty()||username.isEmpty()||parola.isEmpty()||id_centru.isEmpty())
            return false;
        else {
            {
                try {
                    CallableStatement callableStatement = connection.prepareCall(" CALL UpdateMedicById(?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
                    callableStatement.setInt(1,id_angajat);
                    callableStatement.setString(2, cnp);
                    callableStatement.setString(3, nume);
                    callableStatement.setString(4, prenume);
                    callableStatement.setString(5, adresa);
                    callableStatement.setString(6, nrTelefon);
                    callableStatement.setString(7, eMail);
                    callableStatement.setString(8, iBan);
                    callableStatement.setInt(9, Integer.parseInt(nrContract));
                    callableStatement.setString(10, functie);
                    callableStatement.setInt(11, Integer.parseInt(salariuLunar));
                    callableStatement.setString(12, username);
                    callableStatement.setString(13, parola);
                    callableStatement.setInt(14, Integer.parseInt(id_centru));
                    callableStatement.setString(15, codParafa);
                    callableStatement.setString(16, titluStiintific);
                    callableStatement.setString(17, postDidactic);
                    callableStatement.setInt(18, procent);
                    int rowsAffected = callableStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        // Successfully inserted
                        return true;
                    } else {
                        // Insertion failed
                        return false;
                    }


                } catch (SQLException e) {
                    return false;
                }
            }
        }
    }
    public boolean updateAngajat(int id_angajat, String nume, String prenume, String cnp, String adresa, String nrTelefon, String eMail, String iBan, String nrContract, String functie, String salariuLunar, String username, String parola, String id_centru)
    {
        if(nume.isEmpty()||prenume.isEmpty()||cnp.isEmpty()||adresa.isEmpty()||nrTelefon.isEmpty()||eMail.isEmpty()||iBan.isEmpty()||nrContract.isEmpty()||functie.isEmpty()||salariuLunar.isEmpty()||username.isEmpty()||parola.isEmpty()||id_centru.isEmpty())
        {
            return false;
        }
        else {
            {
                try {
                    CallableStatement callableStatement = connection.prepareCall(" CALL UpdateAngajatById(?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
                    callableStatement.setInt(1,id_angajat);
                    callableStatement.setString(2, cnp);
                    callableStatement.setString(3, nume);
                    callableStatement.setString(4, prenume);
                    callableStatement.setString(5, adresa);
                    callableStatement.setString(6, nrTelefon);
                    callableStatement.setString(7, eMail);
                    callableStatement.setString(8, iBan);
                    callableStatement.setInt(9, Integer.parseInt(nrContract));
                    callableStatement.setString(10, functie);
                    callableStatement.setInt(11, Integer.parseInt(salariuLunar));
                    callableStatement.setString(12, username);
                    callableStatement.setString(13, parola);
                    callableStatement.setInt(14, Integer.parseInt(id_centru));
                    int rowsAffected = callableStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        // Successfully inserted
                        return true;
                    } else {

                        // Insertion failed
                        return false;
                    }
                } catch (SQLException e) {

                    return false;
                }
            }
        }
    }
    public boolean insertMedicAngajat(String nume, String prenume, String cnp, String adresa, String nrTelefon, String eMail, String iBan, String nrContract, String functie, String salariuLunar, String username, String parola, String id_centru, String cod_parafa, String titlu_stiintific, String post_didactic, String procent )
    {
        if(cod_parafa.isEmpty() || nume.isEmpty()||prenume.isEmpty()||cnp.isEmpty()||adresa.isEmpty()||nrTelefon.isEmpty()||eMail.isEmpty()||iBan.isEmpty()||nrContract.isEmpty()||functie.isEmpty()||salariuLunar.isEmpty()||username.isEmpty()||parola.isEmpty()||id_centru.isEmpty())
            return false;
        else {
            {
                try {
                    CallableStatement callableStatement = connection.prepareCall(" CALL InsertMedicAngajat(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,?);");
                    callableStatement.setString(1, cnp);
                    callableStatement.setString(2, nume);
                    callableStatement.setString(3, prenume);
                    callableStatement.setString(4, adresa);
                    callableStatement.setString(5, nrTelefon);
                    callableStatement.setString(6, eMail);
                    callableStatement.setString(7, iBan);
                    callableStatement.setInt(8, Integer.parseInt(nrContract));
                    callableStatement.setString(9, functie);
                    callableStatement.setInt(10, Integer.parseInt(salariuLunar));
                    callableStatement.setString(11, username);
                    String hashed = HashIt.hashThePass(parola);
                    callableStatement.setString(12, hashed);
                    callableStatement.setString(13, cod_parafa);
                    callableStatement.setString(14, titlu_stiintific);
                    callableStatement.setString(15, post_didactic);
                    callableStatement.setString(16, procent);
                    callableStatement.setInt(17, Integer.parseInt(id_centru));
                    int rowsAffected = callableStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        // Successfully inserted
                        return true;
                    } else {
                        // Insertion failed
                        return false;
                    }


                } catch (SQLException e) {
                    return false;
                }
            }
        }
    }


    public boolean insertAngajat(String nume, String prenume, String cnp, String adresa, String nrTelefon, String eMail, String iBan, String nrContract, String functie, String salariuLunar, String username, String parola, String id_centru)
    {
        if(nume.isEmpty()||prenume.isEmpty()||cnp.isEmpty()||adresa.isEmpty()||nrTelefon.isEmpty()||eMail.isEmpty()||iBan.isEmpty()||nrContract.isEmpty()||functie.isEmpty()||salariuLunar.isEmpty()||username.isEmpty()||parola.isEmpty()||id_centru.isEmpty())
            return false;
        else {
            {
                try {
                    CallableStatement callableStatement = connection.prepareCall(" CALL InsertAngajat(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
                    callableStatement.setString(1, cnp);
                    callableStatement.setString(2, nume);
                    callableStatement.setString(3, prenume);
                    callableStatement.setString(4, adresa);
                    callableStatement.setString(5, nrTelefon);
                    callableStatement.setString(6, eMail);
                    callableStatement.setString(7, iBan);
                    callableStatement.setInt(8, Integer.parseInt(nrContract));
                    callableStatement.setString(9, functie);
                    callableStatement.setInt(10, Integer.parseInt(salariuLunar));
                    callableStatement.setString(11, username);
                    String hashed = HashIt.hashThePass(parola);
                    callableStatement.setString(12, hashed);
                    callableStatement.setInt(13, Integer.parseInt(id_centru));
                    int rowsAffected = callableStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        // Successfully inserted
                        return true;
                    } else {
                        // Insertion failed
                        return false;
                    }


                } catch (SQLException e) {
                    return false;
                }
            }
        }


    }


    public boolean insertInTabelaConcediu(int idAngajat, java.sql.Date data_inceput, java.sql.Date data_sfarsit, String motiv) {

        try {
            CallableStatement callableStatement = connection.prepareCall(" CALL InsertConcediu(?, ?, ?, ?);");
            callableStatement.setInt(1, idAngajat);
            callableStatement.setDate(2, data_inceput);
            callableStatement.setDate(3, data_sfarsit);
            callableStatement.setString(4, motiv);
            int rowsAffected = callableStatement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            return false;
        }
    }

    public void searchInTable(String searchText, JTable table, DefaultTableModel tableModel) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        if (searchText.length() == 0) {
            sorter.setRowFilter(null); // Show all rows if the search field is empty
        } else {
            // Perform case-insensitive search and filter rows based on the search text
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
        }
    }

    public void clearSearch(JTable table) {
        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) table.getRowSorter();
        if(sorter!=null)
            sorter.setRowFilter(null); // Clear the row filter to display all rows

    }

    public String getDeptForFunctie(String functie) {
        String rez = null;
        try {
            CallableStatement callableStatement = connection.prepareCall("SELECT * FROM functie_dept WHERE functie=?");
            callableStatement.setString(1, functie);
            ResultSet resultSet = callableStatement.executeQuery();

            if (resultSet.next()) {

                rez = resultSet.getString("dept");
                return rez;
            }
        return rez;

        } catch (SQLException e) {
            return rez;

        }
    }


        public int getCentruIdFromNumeCentru(String nume_centru)
    {
        int rez=-1;
        try {
            CallableStatement callableStatement = connection.prepareCall("SELECT * FROM `proiect_v1`.`centru` WHERE `nume_centru` = ?");
            callableStatement.setString(1, nume_centru);
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {

                rez = resultSet.getInt("id_centru");
                return rez;
            }
            resultSet.close();
            return -1;
        } catch(SQLException e) {
            return rez;
        }

    }

    public String getCentruNumeFromCentruId(int id_centru)
    {
        String rez=null;
        try {
            CallableStatement callableStatement = connection.prepareCall("SELECT * FROM `proiect_v1`.`centru` WHERE `id_centru` = ?");
            callableStatement.setInt(1, id_centru);
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {

                rez = resultSet.getString("nume_centru");
                return rez;
            }
            resultSet.close();
            return null;
        } catch(SQLException e) {
            return rez;
        }

    }

    public int getAngajatIdFromUsername(String username)
    {
        int rez=-1;
        try {
            CallableStatement callableStatement = connection.prepareCall("SELECT * FROM `proiect_v1`.`angajat` WHERE `username` = ?");
            callableStatement.setString(1, username);
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {

                rez = resultSet.getInt("id_angajat");
                return rez;
            }
            resultSet.close();
            return -1;
        } catch(SQLException e) {
            return rez;
        }

    }



    public ArrayList<String> getFunctionsForDept(String dept) {
        ArrayList<String> al = new ArrayList<>();

        try {
            CallableStatement callableStatement = connection.prepareCall("SELECT * FROM functie_dept WHERE dept=?");
            callableStatement.setString(1, dept);
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                String nume_fct = resultSet.getString("functie");
                al.add(nume_fct);
            }
            resultSet.close();
        } catch(SQLException e) {
            al.add("");
        }

        return al;
    }


    public ArrayList<String> getPacienti() {
        ArrayList<String> al = new ArrayList<>();

        try {
            CallableStatement callableStatement = connection.prepareCall("SELECT * FROM pacient");
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                String nume_pacient = resultSet.getString("nume");
                nume_pacient = nume_pacient + " " + resultSet.getString("prenume");
                if(!al.contains(nume_pacient))
                    al.add(nume_pacient);
            }
            resultSet.close();
        } catch(SQLException e) {
            al.add("");
        }

        return al;
    }

    public int getIdPacientFromNumePrenume(String fullName)
    {
        String[] nameParts = fullName.split(" ");
        int pId = -1;
        // Check if the split produced at least two parts (first name and last name)
        if (nameParts.length >= 2) {
            String firstName = nameParts[0];
            String lastName = nameParts[1];
            try {
                CallableStatement callableStatement = connection.prepareCall("SELECT * FROM pacient WHERE nume=? AND prenume = ?");
                callableStatement.setString(1, firstName);
                callableStatement.setString(2,lastName);
                ResultSet resultSet = callableStatement.executeQuery();

                while (resultSet.next()) {
                    pId = resultSet.getInt("id_pacient");
                }
                resultSet.close();
                return  pId;
            } catch(SQLException e) {
                return -1;
            }

        }
        else { return -1; }
    }


    public int getIdAngajatFromNumePrenumeFunctie(String fullName, String functie)
    {
        String[] nameParts = fullName.split(" ");
        int aId = -1;
        // Check if the split produced at least two parts (first name and last name)
        if (nameParts.length >= 2) {
            String firstName = nameParts[0];
            String lastName = nameParts[1];
            try {
                CallableStatement callableStatement = connection.prepareCall("SELECT * FROM angajat WHERE nume=? AND prenume = ? AND functie=?");
                callableStatement.setString(1, firstName);
                callableStatement.setString(2,lastName);
                callableStatement.setString(3,functie);
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


    public int getIdMedicFromCentruIdSiFunctie(int centruId, String functie)
    {
        int al = 0;
        try {
            CallableStatement callableStatement = connection.prepareCall("SELECT * FROM angajat WHERE functie=? AND id_centru = ?");
            callableStatement.setString(1, functie);
            callableStatement.setInt(2,centruId);
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                al = resultSet.getInt("id_angajat");
            }
            resultSet.close();
        }
        catch(SQLException e) {
            return -1;
        }

        return al;
    }




    public ArrayList<String> getAngajatiNumePrenumeFromFunctieAndCentru(String functie, int id_centru)
    {
        ArrayList<String> al = new ArrayList<>();

        try {
            CallableStatement callableStatement = connection.prepareCall("SELECT * FROM angajat WHERE functie=? AND id_centru = ?");
            callableStatement.setString(1, functie);
            callableStatement.setInt(2,id_centru);
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                String nume_angajat = resultSet.getString("nume");
                nume_angajat = nume_angajat + " " + resultSet.getString("prenume");
                if(!al.contains(nume_angajat))
                    al.add(nume_angajat);
            }
            resultSet.close();
        } catch(SQLException e) {
            al.add("");
        }

        return al;
    }


    public ArrayList<String> getAngajatiNumePrenumeFromCentru(int id_centru)
    {
        ArrayList<String> al = new ArrayList<>();

        try {
            CallableStatement callableStatement = connection.prepareCall("SELECT * FROM angajat WHERE id_centru = ?");
            callableStatement.setInt(1, id_centru);
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                String nume_angajat = resultSet.getString("nume");
                nume_angajat = nume_angajat + " " + resultSet.getString("prenume");
                if(!al.contains(nume_angajat))
                    al.add(nume_angajat);
            }
            resultSet.close();
        } catch(SQLException e) {
            al.add("");
        }
        return al;
    }




    public ArrayList<String> getDepts() {
        ArrayList<String> al = new ArrayList<>();

        try {
            CallableStatement callableStatement = connection.prepareCall("SELECT * FROM functie_dept");
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


    public ArrayList<String> getCenters() {
        ArrayList<String> al = new ArrayList<>();

        try {
            CallableStatement callableStatement = connection.prepareCall("SELECT * FROM Centru");
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                String nume_centru = resultSet.getString("nume_centru");
                al.add(nume_centru);
            }
            resultSet.close();
        } catch(SQLException e) {
            al.add("");
        }

        return al;
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
