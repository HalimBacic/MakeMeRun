package Main.Forms;

import Main.MakeMeRun;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Aimscontrol {

    @FXML
    TextField certifikator;

    @FXML
    TextField pocetak;

    @FXML
    TextField kraj;

    @FXML
    TextField trka;

    @FXML
    TextField duzina;

    @FXML
    TextField nazivFajla;

    @FXML
    Button potvrdi;

    @FXML
    Button otkazi;


    private static MakeMeRun makeMeRun;
    private static Stage stg;
    private static String number;

    public static void setMe(String n,Stage s,MakeMeRun mmr)
    {
        number=n;
        stg=s;
        makeMeRun=mmr;
    }

    public void makeAIMS()
    {
        String stmt= "INSERT INTO `AIMS Certifikat`(`Broj`,`Certifikator`,`Pocetak`,`Kraj`,`Trka`,`Duzina`,`Naziv fajla`)\n" +
                "VALUES(\""+number+"\",\""+certifikator.getText ()+"\",\""+pocetak.getText ()
                +"\",\""+kraj.getText ()+"\",\""+trka.getText ()+"\",\""+duzina.getText ()+"\",\""+nazivFajla.getText ()+"\");";

        try {
            makeMeRun.updateStatement (stmt);
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greska");
            alert.setHeaderText("Dogodila se greska");
            alert.setContentText("Greska pri dodavanju AIMS Certifikata");

            alert.showAndWait();
        }

        stg.close();
    }

    public void closeMe()
    {
        stg.close ();
    }

}
