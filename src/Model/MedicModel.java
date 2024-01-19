package Model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MedicModel extends BigModel{

    Connection connection;
    public MedicModel() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proiect_v1", "root", "*DaniPeNet_1");
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }
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

    public class RaportEntry {
        public int idRaport;
        public int idPacient;
        public int idAsistent;
        public String recomandari;
        public String istoricRelevant;
        public String diagnostic;
        public LocalDate dataCompletare;

        // Constructor
        public RaportEntry(int idRaport, int idPacient, int idAsistent,
                           String recomandari, String istoricRelevant,
                           String diagnostic, LocalDate dataCompletare) {
            this.idRaport = idRaport;
            this.idPacient = idPacient;
            this.idAsistent = idAsistent;
            this.recomandari = recomandari;
            this.istoricRelevant = istoricRelevant;
            this.diagnostic = diagnostic;
            this.dataCompletare = dataCompletare;
        }
    }

    public int getProfitPeLuna(int idMedic, String data, String an){
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
    public Object[][] getIstoricRapoarteForMedic(int idPacient) {
        Object[][] entries;
        try {
            //wtf
            CallableStatement callableStatement = connection.prepareCall("SELECT * FROM raport WHERE id_pacient = ?");
            callableStatement.setInt(1, idPacient);
            callableStatement.execute();


            ArrayList<RaportEntry> raportEntryArrayList = new ArrayList<>();
            ResultSet resultSet = callableStatement.getResultSet();

            while (resultSet.next()) {
                RaportEntry raportEntry = new RaportEntry(resultSet.getInt("id_raport"),resultSet.getInt("id_pacient"),resultSet.getInt("id_asistent"), resultSet.getString("recomandari"),resultSet.getString("istoric_relev"),resultSet.getString("diagonistic"),resultSet.getDate("data_completare").toLocalDate());
                raportEntryArrayList.add(raportEntry);
            }
            int rowCount = raportEntryArrayList.size();
            entries = new Object[rowCount][7];
            int k=0;
            for(RaportEntry e : raportEntryArrayList)
            {
                entries[k][0] = e.idRaport;
                entries[k][1] = e.idPacient;
                entries[k][2] = e.idAsistent;
                entries[k][3] = e.recomandari;
                entries[k][4] = e.istoricRelevant;
                entries[k][5] = e.diagnostic;
                entries[k][6] = e.dataCompletare;
                k++;
            }

            return entries;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as per your requirement
            return null;
        }
    }

    public boolean insertRaport(int id_raport, int id_pacient, int id_asistent, String recomandari, String istoric_relev, String diagnostic, java.sql.Date dataCompletare, ArrayList<Integer> id_investigatii)
    {
        try{
            CallableStatement callableStatement = connection.prepareCall(" CALL InsertIntoRaport(?, ?, ?, ?,?,?,?);");
            callableStatement.setInt(1,id_raport);
            callableStatement.setInt(2,id_pacient);
            callableStatement.setInt(3,id_asistent);
            callableStatement.setString(4,recomandari);
            callableStatement.setString(5,istoric_relev);
            callableStatement.setString(6,diagnostic);
            callableStatement.setDate(7, dataCompletare);
            int rowsAffected = callableStatement.executeUpdate();

            if (rowsAffected > 0) {
                for(Integer i : id_investigatii)
                {
                    CallableStatement callableStatement2 = connection.prepareCall(" CALL InsertInvestigatie(?, ?);");
                    callableStatement2.setInt(1,id_raport);
                    callableStatement2.setInt(2,i);
                    int rowsAffected2 = callableStatement2.executeUpdate();
                    if(rowsAffected2==0)
                    {
                        return false;
                    }
                }
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



}
