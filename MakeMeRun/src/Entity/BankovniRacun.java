package Entity;

public class BankovniRacun {

    private String imeBanke;
    private String brojRacuna;
    private Integer stanje;

    public BankovniRacun(String imeBanke,String brojRacuna,Integer stanje)
    {
        this.imeBanke=imeBanke;
        this.brojRacuna=brojRacuna;
        this.stanje=stanje;
    }

    public String getImeBanke () {
        return imeBanke;
    }

    public void setImeBanke (String imeBanke) {
        this.imeBanke = imeBanke;
    }

    public String getBrojRacuna () {
        return brojRacuna;
    }

    public void setBrojRacuna (String brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    public Integer getStanje () {
        return stanje;
    }

    public void setStanje (Integer stanje) {
        this.stanje = stanje;
    }
}
