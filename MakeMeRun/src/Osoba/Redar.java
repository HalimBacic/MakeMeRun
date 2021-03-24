package Osoba;

public class Redar extends Osoba {

    private String jmbgradanina;
    private String uloga;
    private String brojTelefona;

    public Redar(String jmbg, String ime, String prezime, String uloga,String brojTelefona)
    {
        super(0,ime,prezime);
        this.jmbgradanina=jmbg;
        this.uloga=uloga;
        this.brojTelefona=brojTelefona;
    }

    public String getJmbg() {
        return jmbgradanina;
    }

    public void setJmbg(String jmbg) {
        this.jmbgradanina = jmbg;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }

    public String getBrojTelefona () {
        return brojTelefona;
    }

    public void setBrojTelefona (String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }
}
