package Entity;

public class Promocija {

    private String imeTrke;
    private String imePromotora;
    private String prezimePromotora;
    private String aktivnost;
    private String termin;

    public Promocija(String it,String ip,String pp,String a,String t)
    {
        imeTrke=it;
        imePromotora=ip;
        prezimePromotora=pp;
        aktivnost=a;
        termin=t;
    }

    public String getImeTrke () {
        return imeTrke;
    }

    public void setImeTrke (String imeTrke) {
        this.imeTrke = imeTrke;
    }

    public String getImePromotora () {
        return imePromotora;
    }

    public void setImePromotora (String imePromotora) {
        this.imePromotora = imePromotora;
    }

    public String getPrezimePromotora () {
        return prezimePromotora;
    }

    public void setPrezimePromotora (String prezimePromotora) {
        this.prezimePromotora = prezimePromotora;
    }

    public String getAktivnost () {
        return aktivnost;
    }

    public void setAktivnost (String aktivnost) {
        this.aktivnost = aktivnost;
    }

    public String getTermin () {
        return termin;
    }

    public void setTermin (String termin) {
        this.termin = termin;
    }
}
