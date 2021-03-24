package Osoba;

public class Promotor extends Osoba{

    private String imeTrke;

    public Promotor(Integer id,String ime, String prezime,String it)
    {
        super(id,ime,prezime);
        imeTrke=it;
    }

    public String getImeTrke () {
        return imeTrke;
    }

    public void setImeTrke (String imeTrke) {
        this.imeTrke = imeTrke;
    }
}
