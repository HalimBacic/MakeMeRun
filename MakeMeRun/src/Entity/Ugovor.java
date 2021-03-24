package Entity;

public class Ugovor {

    private String ime;
    private String sadrzaj;
    private Integer broj;
    private Integer cijena;

    public Ugovor(Integer b,String i,String s,Integer c)
    {
        broj=b;
        ime=i;
        sadrzaj=s;
        cijena=c;
    }

    public String getIme () {
        return ime;
    }

    public void setIme (String ime) {
        this.ime = ime;
    }

    public String getSadrzaj () {
        return sadrzaj;
    }

    public void setSadrzaj (String sadrzaj) {
        this.sadrzaj = sadrzaj;
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
}
