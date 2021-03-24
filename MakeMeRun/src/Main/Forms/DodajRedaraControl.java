package Main.Forms;

import Main.Controller;
import Main.MakeMeRun;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class DodajRedaraControl {


    @FXML
    private TextField jmbg;

    @FXML
    private TextField ime;

    @FXML
    private TextField prezime;

    @FXML
    private TextField brojtel;

    @FXML
    private TextArea uloga;

    static Stage stage;
    static MakeMeRun makeMeRun;
    static Controller control;

    public static void setMe(MakeMeRun mmr,Controller ctrl,Stage stg)
    {
        makeMeRun=mmr;
        control=ctrl;
        stage=stg;
    }

    public void closeMe()
    {
        stage.close ();
    }

    public void dodajRedara() throws IOException {
        FXMLLoader loader = new FXMLLoader (getClass ().getResource ("redari.fxml"));
        loader.load ();
        String stmt = "INSERT INTO `Redar`(`JMBG`,`Ime`,`Prezime`,`Uloga`,`Broj telefona`)\n" +
                "VALUES (\""+jmbg.getText ()+"\",\""+ime.getText ()+"\",\""+prezime.getText ()+"\",\"" +
                ""+uloga.getText ()+"\",\""+brojtel.getText ()+"\");";

        try {
            makeMeRun.updateStatement (stmt);
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greska");
            alert.setHeaderText("Dogodila se greska");
            alert.setContentText("Problem pri dodavanju redara");

            alert.showAndWait();
        }

        RedariControl rdcontrol=(RedariControl) loader.getController ();
        rdcontrol.ispuniTabelu ();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Dodavanje redara");
        alert.setHeaderText(null);
        alert.setContentText("Redar dodan u bazu podataka");

        alert.showAndWait();

    }
}
