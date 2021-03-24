package Entity;

import java.text.SimpleDateFormat;

public class Trka {

    private String naziv;
    private Integer distanca;
    private SimpleDateFormat datum;
    private String aimsNo;

    public Trka(String n,Integer d,String dh,String an)
    {
        naziv=n;
        distanca=d;
        aimsNo=an;
        datum=new SimpleDateFormat (dh);
    }

    public String getNaziv () {
        return naziv;
    }

    public void setNaziv (String naziv) {
        this.naziv = naziv;
    }

    public Integer getDistanca () {
        return distanca;
    }

    public void setDistanca (Integer distanca) {
        this.distanca = distanca;
    }

    public SimpleDateFormat getDatum () {
        return datum;
    }

    public void setDatum (SimpleDateFormat datum) {
        this.datum = datum;
    }

    public String getAimsNo () {
        return aimsNo;
    }

    public void setAimsNo (String aimsNo) {
        this.aimsNo = aimsNo;
    }
}
