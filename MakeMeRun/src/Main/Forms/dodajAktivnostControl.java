package Main.Forms;

import Main.MakeMeRun;
import Osoba.Promotor;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class dodajAktivnostControl {

    @FXML
    private TextField datumAktivnost;

    @FXML
    private TextArea opisAktivnost;

    @FXML
    private Button dodajAktivnost;

    @FXML
    private Button dodajMedij;

    @FXML
    private TextField mazivMedij;

    @FXML
    private TextField adresaMedij;

    @FXML
    private TextField telefonMedij;

    @FXML
    private TextField saradnjaMedij;

    @FXML
    private TextField datumMedij;

    @FXML
    private Button izlaz;

    private static MakeMeRun makeMeRun;
    private static Stage stage;
    private Promotor promotor;

    public static void setMe(MakeMeRun mmr,Stage stg)
    {
        makeMeRun=mmr;
        stage=stg;
    }

    public void setPromotor(Promotor p)
    {
        promotor=p;
    }


    public void closeMe()
    {
        stage.close ();
    }

    public void dodajAktivnost() throws SQLException {
            String idstmt="SELECT COUNT(*) FROM `Aktivnost`;";
            ResultSet rs = makeMeRun.executeStatement (idstmt);
            Integer id=0;
            while(rs.next ())
                id=rs.getInt (1);
            id++;
            String stmtAktivnost ="INSERT INTO `AKTIVNOST`(`ID`,`Datum`,`Opis`) " +
                    "VALUES (\""+id.toString ()+"\",\""+datumAktivnost.getText ()+"\",\""+opisAktivnost.getText ()+"\");";
            makeMeRun.updateStatement (stmtAktivnost);

            String stmtPromovise = "INSERT INTO `Promovise`(`TRKA_Naziv`,`AKTIVNOST_ID`,`PROMOTOR TRKE_ID`)\n" +
                    "VALUES (\""+promotor.getImeTrke ()+"\",\""+id.toString ()+"\",\""+(promotor.getId ()).toString ()+"\");";
            makeMeRun.updateStatement (stmtPromovise);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Uspjesno dodano");
        alert.setHeaderText(null);
        alert.setContentText("Aktivnost uspjesno dodana!");

        alert.showAndWait();

    }

    public void dodajMedij() throws SQLException {
        String brojStmt="SELECT COUNT(*) FROM `Medijski saradnik`;";
        ResultSet rsBroj= makeMeRun.executeStatement (brojStmt);
        Integer broj=0;
        while(rsBroj.next ())
            broj=rsBroj.getInt (1);
        broj++;
        String stmtMedijskiSaradnik = "INSERT INTO `Medijski saradnik`(`ID`,`Ime`,`Broj telefona`,`Adresa`,`Tip saradnje`,`Datum`,`TRKA_Naziv`)\n" +
                "VALUES (\""+broj.toString ()+"\",\""+mazivMedij.getText ()+"\",\""+telefonMedij.getText ()
                +"\",\""+adresaMedij.getText ()+"\",\""+saradnjaMedij.getText ()+"\",\""+datumMedij.getText ()+"\",\""+promotor.getImeTrke ()+"\");";


        String stmtKontaktira ="INSERT INTO `Kontaktira`(`MEDIJSKI SARADNICI_ID`,`PROMOTOR TRKE_ID`)" +
                "VALUES(\""+broj.toString ()+"\",\""+promotor.getId ().toString ()+"\")";

        makeMeRun.updateStatement (stmtMedijskiSaradnik);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Uspjesno dodano");
        alert.setHeaderText(null);
        alert.setContentText("Medijski saradnik uspjesno dodan!");

        alert.showAndWait();
    }

}
