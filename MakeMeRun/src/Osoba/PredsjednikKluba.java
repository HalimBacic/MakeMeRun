package Osoba;

public class PredsjednikKluba extends Osoba {

    private String jbmg;

    public PredsjednikKluba(Integer id,String ime,String prezime,String jmbg)
    {
        super(id,ime,prezime);
        this.jbmg=jmbg;
    }

    public String getJbmg() {
        return jbmg;
    }

    public void setJbmg(String jbmg) {
        this.jbmg = jbmg;
    }
}
