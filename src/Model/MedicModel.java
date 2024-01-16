package Model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MedicModel extends BigModel{

    Connection connection;
    public MedicModel() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proiect_v1", "root", "");
        } catch(SQLException se) {
            se.printStackTrace();
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

}
