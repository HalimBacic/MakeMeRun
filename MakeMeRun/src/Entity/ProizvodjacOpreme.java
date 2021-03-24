package Entity;

public class ProizvodjacOpreme {

    private String id;
    private String ime;
    private String tipProizvoda;
    private String kontakt;

    public ProizvodjacOpreme(String id,String i,String tp,String k)
    {
        this.id=id;
        ime=i;
        tipProizvoda=tp;
        kontakt=k;
    }

    public String getId () {
        return id;
    }

    public void setId (String id) {
        this.id = id;
    }

    public String getIme () {
        return ime;
    }

    public void setIme (String ime) {
        this.ime = ime;
    }

    public String getTipProizvoda () {
        return tipProizvoda;
    }

    public void setTipProizvoda (String tipProizvoda) {
        this.tipProizvoda = tipProizvoda;
    }

    public String getKontakt () {
        return kontakt;
    }

    public void setKontakt (String kontakt) {
        this.kontakt = kontakt;
    }
}
