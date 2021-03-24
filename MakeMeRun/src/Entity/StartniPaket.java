package Entity;

public class StartniPaket {

    private Integer broj;
    private Integer cijena;
    private Boolean majica;
    private Boolean znojnica;
    private Boolean torba;
    private Boolean carape;
    private Boolean pokloni;
        //Obrada startnih paketa
    public StartniPaket(Integer broj,Integer cijena,Boolean majica,Boolean znojnica,Boolean torba,Boolean carape,Boolean pokloni)
    {
        this.broj=broj;
        this.cijena=cijena;
        this.majica=majica;
        this.znojnica=znojnica;
        this.torba=torba;
        this.carape=carape;
        this.pokloni=pokloni;
    }

    public Integer getBroj () {
        return broj;
    }

    public void setBroj (Integer broj) {
        this.broj = broj;
    }

    public Integer getCijena () {
        return cijena;
    }

    public void setCijena (Integer cijena) {
        this.cijena = cijena;
    }

    public Boolean getMajica () {
        return majica;
    }

    public void setMajica (Boolean majica) {
        this.majica = majica;
    }

    public Boolean getZnojnica () {
        return znojnica;
    }

    public void setZnojnica (Boolean znojnica) {
        this.znojnica = znojnica;
    }

    public Boolean getTorba () {
        return torba;
    }

    public void setTorba (Boolean torba) {
        this.torba = torba;
    }

    public Boolean getCarape () {
        return carape;
    }

    public void setCarape (Boolean carape) {
        this.carape = carape;
    }

    public Boolean getPokloni () {
        return pokloni;
    }

    public void setPokloni (Boolean pokloni) {
        this.pokloni = pokloni;
    }
}
