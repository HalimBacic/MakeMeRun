package Main.Forms;

import Main.MakeMeRun;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class DodajRacunControl {

    @FXML
    private TextField imeBanke;

    @FXML
    private TextField brojRacuna;

    @FXML
    private TextField stanjeRacuna;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnOtkazi;

    static Stage stage;
    static MakeMeRun makeMeRun;

    public static void setMe(MakeMeRun mmr,Stage stg)
    {
        stage=stg;
        makeMeRun=mmr;
    }

    @FXML
    void closeMe() {
        stage.close();
    }

    @FXML
    void dodajRacun() throws SQLException {

        String stmt="INSERT INTO `Racun`(`BrojRacuna`,`Banka`,`KLUB_ID`,`Stanje racuna`)\n" +
                "VALUES (\""+brojRacuna.getText ()+"\",\""+imeBanke.getText ()+"\",\"1\",\""+stanjeRacuna.getText ()+"\");";

        makeMeRun.updateStatement (stmt);

        stage.close ();
    }

}