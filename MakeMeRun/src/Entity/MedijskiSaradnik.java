package Entity;

public class MedijskiSaradnik {

    String ime;
    String adresa;
    String kontakt;
    String tipSaradnje;
    String termin;
    String trka;

    public MedijskiSaradnik(String i,String a,String k,String tS,String t,String trka)
    {
        ime=i; adresa=a; kontakt=k; tipSaradnje=tS; termin=t; this.trka=trka;
    }

    public String getIme () {
        return ime;
    }

    public void setIme (String ime) {
        this.ime = ime;
    }

    public String getAdresa () {
        return adresa;
    }

    public void setAdresa (String adresa) {
        this.adresa = adresa;
    }

    public String getKontakt () {
        return kontakt;
    }

    public void setKontakt (String kontakt) {
        this.kontakt = kontakt;
    }

    public String getTipSaradnje () {
        return tipSaradnje;
    }

    public void setTipSaradnje (String tipSaradnje) {
        this.tipSaradnje = tipSaradnje;
    }

    public String getTermin () {
        return termin;
    }

    public void setTermin (String termin) {
        this.termin = termin;
    }
}
