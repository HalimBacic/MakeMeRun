package Main.Forms;

import Main.Controller;
import Main.MakeMeRun;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class PaketControl {

    @FXML
    private CheckBox carape;

    @FXML
    private CheckBox znojnica;

    @FXML
    private CheckBox torba;

    @FXML
    private CheckBox majica;

    @FXML
    private CheckBox gel;

    @FXML
    private CheckBox rucak;

    @FXML
    private CheckBox vrecica;

    @FXML
    private CheckBox bandana;

    @FXML
    private CheckBox pokloni;

    @FXML
    private CheckBox muzej;

    @FXML
    private CheckBox bon;

    @FXML
    private TextField cijena;

    @FXML
    private Button ureduBtn;

    private ArrayList<CheckBox> boxes = new ArrayList<> ();

    static Stage stage;
    static Controller ctrl;
    static MakeMeRun makeMeRun;
    static String nazivTrke;

    public PaketControl()
    {
    }

    public static void setMe(MakeMeRun mmr,Stage stg,String naziv)
    {
        nazivTrke=naziv;
        makeMeRun=mmr;
        stage=stg;
    }

    public void closeMe()
    {
        stage.close ();
    }

    public void dodajPaket() throws SQLException, IOException {

            String stmt = "INSERT INTO `Startni paket`(Cijena,TRKA_Naziv)" +
                    "VALUES (\""+cijena.getText ()+"\",\""+nazivTrke+"\");";

            makeMeRun.updateStatement (stmt);

            String stmtCount = "SELECT `ID` FROM `Startni paket`;";
            ResultSet rs=makeMeRun.executeStatement (stmtCount);
            Integer broj=0;
            while(rs.next ())
                broj=rs.getInt (1);


            String stmtSadrzajPaketa = "INSERT INTO `SADRZAJ PAKETA`(`Carape`,`Znojnica`,`Torba`,`Karte za muzej`,`Majica`," +
                    "`Energetski gel`,`Rucak`,`Bon za kupovinu`,`Vrecica za patike`,`Bandana`,`Pokloni Sponzora`,`STARTNI PAKET_ID`)" +
                    "VALUES(\""+carape.isSelected ()+"\",\""
                    +znojnica.isSelected ()+"\",\""
                    +torba.isSelected ()+"\",\""+
                    muzej.isSelected ()+"\",\""
                    +majica.isSelected ()+"\",\""
                    +gel.isSelected ()+"\",\""
                    +rucak.isSelected ()+"\",\""
                    +bon.isSelected ()+"\",\""
                    +vrecica.isSelected ()+"\",\""
                    +bandana.isSelected ()+"\",\""
                    +pokloni.isSelected ()+"\",\""
                    +broj.toString ()+"\")";

            makeMeRun.updateStatement (stmtSadrzajPaketa);

            FXMLLoader loader = new FXMLLoader (getClass ().getResource ("trka.fxml"));
            loader.load ();
            TrkaControl trkaControl=loader.getController ();
            String napomena = trkaControl.getPaket ();
            napomena+="\n Startni paket od "+cijena.toString ()+" KM";
            trkaControl.setPaket (napomena);

            closeMe ();
    }
}
