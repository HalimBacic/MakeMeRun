package Entity;

public class Kontakt {

    private String ime;
    private String opis;
    private String kontakt;

    public Kontakt(String ime,String opis,String kontakt)
    {
        this.ime=ime;
        this.opis=opis;
        this.kontakt=kontakt;
    }

    public String getIme () {
        return ime;
    }

    public void setIme (String ime) {
        this.ime = ime;
    }

    public String getOpis () {
        return opis;
    }

    public void setOpis (String opis) {
        this.opis = opis;
    }

    public String getKontakt () {
        return kontakt;
    }

    public void setKontakt (String kontakt) {
        this.kontakt = kontakt;
    }
}
