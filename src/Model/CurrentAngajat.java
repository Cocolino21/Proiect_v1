package Model;

public class CurrentAngajat extends BigModel{

        private int id;
        private String nume;
        private String prenume;
        private String cnp;
        private String adresa;
        private String email;
        private String nrTelefon;
        private String iban;
        private int nrContract;
        private String functie;
        private int salariu;
        private String username;
        private int id_centru;

    public CurrentAngajat(int id, String nume, String prenume, String cnp, String adresa,String nrTelefon, String email, String iban, int nrContract, String functie, int salariu, String username, int id_centru) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.cnp = cnp;
        this.adresa = adresa;
        this.nrTelefon = nrTelefon;
        this.email = email;
        this.iban = iban;
        this.nrContract = nrContract;
        this.functie = functie;
        this.salariu = salariu;
        this.username = username;
        this.id_centru = id_centru;
    }


    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getCnp() {
        return cnp;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getEmail() {
        return email;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public String getIban() {
        return iban;
    }

    public int getNrContract() {
        return nrContract;
    }

    public String getFunctie() {
        return functie;
    }

    public int getSalariu() {
        return salariu;
    }

    public String getUsername() {
        return username;
    }

    public int getId_centru() {
        return id_centru;
    }
}
