package Main.Forms;

import Main.MakeMeRun;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class DodajKontaktControl {

    @FXML
    private TextField imeKontaktaField;

    @FXML
    private TextField opisField;

    @FXML
    private TextField kontaktField;


    private static Stage stage;
    private static MakeMeRun makeMeRun;

    public static void setMe(Stage stg,MakeMeRun mmr)
    {
        stage=stg;
        makeMeRun=mmr;
    }

    public void closeMe() {
        stage.close();
    }

    public void dodajKontakt() throws SQLException {

            String stmt = "INSERT INTO `Kontakt`(`Ime kontakta`,`Opis`,`Kontakt`) " +
                    "VALUES (\""+imeKontaktaField.getText ()+"\",\""+opisField.getText ()+"\",\""+kontaktField.getText ()+"\");";

            makeMeRun.updateStatement (stmt);

            stage.close ();
    }

}