package Model;
import Controller.HashIt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.sql.*;
import java.util.ArrayList;

public class BigModel {
    private Connection connection;

    protected static CurrentAngajat currentAngajat;

    public CurrentAngajat getCurrentAngajat() {
        return currentAngajat;
    }

    public BigModel() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proiect_v1", "root", "?");
        } catch(SQLException se) {
            se.printStackTrace();
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


    public boolean updateAngajat(int id_angajat, String nume, String prenume, String cnp, String adresa, String nrTelefon, String eMail, String iBan, String nrContract, String functie, String salariuLunar, String username, String parola, String id_centru)
    {
        if(nume.isEmpty()||prenume.isEmpty()||cnp.isEmpty()||adresa.isEmpty()||nrTelefon.isEmpty()||eMail.isEmpty()||iBan.isEmpty()||nrContract.isEmpty()||functie.isEmpty()||salariuLunar.isEmpty()||username.isEmpty()||parola.isEmpty()||id_centru.isEmpty())
            return false;
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

}
