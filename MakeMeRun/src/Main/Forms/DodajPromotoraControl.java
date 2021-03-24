package Main.Forms;

import Main.MakeMeRun;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DodajPromotoraControl {

    @FXML
    private TextField ime;

    @FXML
    private TextField prezime;

    @FXML
    private TextField kontakt;

    @FXML
    private Button btnDodaj;


    static MakeMeRun makeMeRun;
    static Stage stage;
    static String imeTrke;

    public static void setMe(MakeMeRun mmr,Stage stg,String it)
    {
        makeMeRun=mmr;
        stage=stg;
        imeTrke=it;
    }

    @FXML
    void dodajPromotora() throws SQLException, IOException {

        String stmtNumber = "SELECT COUNT(*) FROM `Promotor trke`;";
        ResultSet rs = makeMeRun.executeStatement (stmtNumber);
        Integer broj=0;
        while(rs.next ())
            broj = rs.getInt (1);
        broj++;
        String stmt="INSERT INTO `Promotor trke`(`ID`,`Ime`,`Prezime`,`Ime trke`,`Kontakt`) VALUES (\""+broj.toString ()+"\",\""
                +ime.getText ()+"\",\""+prezime.getText ()+"\",\""+imeTrke+"\",\""+kontakt.getText ()+"\");";

        makeMeRun.updateStatement (stmt);

        FXMLLoader loader = new FXMLLoader (getClass ().getResource ("promotori.fxml"));
        Parent root = loader.load ();
        PromotoriControl promotoriControl= (PromotoriControl) loader.getController ();

        stage.close ();
        promotoriControl.loadPromotori ();
    }
}
