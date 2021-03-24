package Main.Forms;

import Main.Controller;
import Main.MakeMeRun;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class PriznanicaControl {

    @FXML
    private TextField tip;

    @FXML
    private TextField cijena;

    @FXML
    private ChoiceBox<String> racun;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnPaket;

    static MakeMeRun makeMeRun;
    static Stage stage1;
    private Stage stage2;
    static Controller control;
    private Integer brojTrkaca;

    public static void setMe(MakeMeRun mmr,Stage stg,Controller ctrl)
    {
        makeMeRun=mmr;
        stage1=stg;
        control=ctrl;
    }

    public void setBrojTrkaca(Integer broj,Stage s) throws SQLException {
        brojTrkaca=broj;
        stage2 = s;
        String stmt = "SELECT * FROM `Racun`";
        ResultSet rs = makeMeRun.executeStatement (stmt);
        String racunBroj="";
        while(rs.next ())
        {
            racunBroj = rs.getString (1);
            racun.getItems ().add (racunBroj);
        }
    }

    public void dodajPriznanicu() throws SQLException {
        LocalDateTime date=LocalDateTime.now ();
        String stmt="INSERT INTO `PRIZNANICA`(`Tip`,`Cijena`,`Datum`,`RACUN_BrojRacuna`,`TRKAČ_Broj`)\n" +
                "VALUES(\""+tip.getText ()+"\",\""+cijena.getText ()+"\",\""+date.toString ()+"\",\""+
                racun.getSelectionModel ().getSelectedItem ().toString ()+"\",\""+brojTrkaca.toString ()+"\");";

        makeMeRun.updateStatement (stmt);
/*
        //TODO Raditi preko triggera - provjeriti
        String stmtRacun = ("UPDATE `Racun`\n" +
                "SET `Stanje racuna`=`Stanje racuna`+\""+cijena.getText ()+"\" WHERE `BrojRacuna`=" +
                "\""+racun.getSelectionModel ().getSelectedItem ().toString ()+"\";");

        makeMeRun.updateStatement (stmtRacun);
        */
        closeMe ();
    }

    public void closeMe()
    {
        stage1.close ();
        stage2.close();
    }
}
