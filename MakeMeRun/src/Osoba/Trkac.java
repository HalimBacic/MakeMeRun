package Osoba;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Trkac extends Osoba{

    private Integer distanca;
    private Integer godine;
    private String drzava;
    private String vrijemePrikaz;
    private String trka;
    private String kontakt;
    private ImageView img;

    public Trkac(Integer id, String ime, String prezime, Integer d, Integer g, String drz, String t, String kontakt, String vrijeme)
    {
        super(id,ime,prezime);
        this.distanca=d;
        this.godine=g;
        this.drzava=drz;
        this.trka = t;
        this.img = new ImageView(new Image (this.getClass ().getResourceAsStream ("/seatchRunner.png")));
        this.kontakt=kontakt;
        this.vrijemePrikaz=vrijeme;
    }

    public Integer getDistanca() {
        return distanca;
    }

    public void setDistanca(Integer distanca) {
        this.distanca = distanca;
    }

    public Integer getGodine() {
        return godine;
    }

    public void setGodine(Integer godine) {
        this.godine = godine;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getTrka () {
        return trka;
    }

    public void setTrka (String trka) {
        this.trka = trka;
    }

    public String getKontakt () {
        return kontakt;
    }

    public void setKontakt (String kontakt) {
        this.kontakt = kontakt;
    }

    public ImageView getImg () {
        return img;
    }

    public void setImg (ImageView img) {
        this.img = img;
    }

    public String getVrijemePrikaz () {
        return vrijemePrikaz;
    }

    public void setVrijemePrikaz (String vrijemePrikaz) {
        this.vrijemePrikaz = vrijemePrikaz;
    }
}
