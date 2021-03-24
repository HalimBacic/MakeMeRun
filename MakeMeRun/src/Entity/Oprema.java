package Entity;

public class Oprema {

    private Integer id;
    private String tip;
    private Integer cijena;
    private Integer kolicina;
    private String proizvodjac;

    public Oprema(Integer id,String t,Integer c,Integer k,String p)
    {
        this.id=id;
        tip=t;
        cijena=c;
        kolicina=k;
        kolicina=k;
        proizvodjac=p;
    }

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getTip () {
        return tip;
    }

    public void setTip (String tip) {
        this.tip = tip;
    }

    public Integer getCijena () {
        return cijena;
    }

    public void setCijena (Integer cijena) {
        this.cijena = cijena;
    }

    public Integer getKolicina () {
        return kolicina;
    }

    public void setKolicina (Integer kolicina) {
        this.kolicina = kolicina;
    }

    public String getProizvodjac () {
        return proizvodjac;
    }

    public void setProizvodjac (String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }
}
