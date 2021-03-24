package Entity;

public class AIMSCertifikat {

        private String broj;
        private String certifikator;
        private String pocetak;
        private String kraj;
        private String trka;
        private String duzina;
        private String nazivFajla;

        public AIMSCertifikat(String br,String c,String p,String k,String t,String d,String nf)
        {
            broj=br;
            certifikator=c;
            pocetak=p;
            kraj=k;
            trka=t;
            duzina=d;
            nazivFajla=nf;
        }

    public String getBroj () {
        return broj;
    }

    public void setBroj (String broj) {
        this.broj = broj;
    }

    public String getCertifikator () {
        return certifikator;
    }

    public void setCertifikator (String certifikator) {
        this.certifikator = certifikator;
    }

    public String getPocetak () {
        return pocetak;
    }

    public void setPocetak (String pocetak) {
        this.pocetak = pocetak;
    }

    public String getKraj () {
        return kraj;
    }

    public void setKraj (String kraj) {
        this.kraj = kraj;
    }

    public String getTrka () {
        return trka;
    }

    public void setTrka (String trka) {
        this.trka = trka;
    }

    public String getDuzina () {
        return duzina;
    }

    public void setDuzina (String duzina) {
        this.duzina = duzina;
    }

    public String getNazivFajla () {
        return nazivFajla;
    }

    public void setNazivFajla (String nazivFajla) {
        this.nazivFajla = nazivFajla;
    }
}
