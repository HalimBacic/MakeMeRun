package Osoba;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Osoba {

    private Integer id = 0;
    private String ime="";
    private String prezime="";

    public Osoba()
    {}

    public Osoba(Integer id, String ime, String prezime)
    {
        this.id=id;
        this.ime=ime;
        this.prezime=prezime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
}
