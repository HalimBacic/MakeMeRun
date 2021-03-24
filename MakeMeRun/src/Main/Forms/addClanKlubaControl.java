package Main.Forms;

import Main.Controller;
import Main.MakeMeRun;
import Osoba.ClanKluba;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class addClanKlubaControl {

    @FXML
    TextField nameField;
    @FXML
    TextField surnameField;
    @FXML
    TextArea uloga;

    @FXML
    TextField kontaktField;

    @FXML
    Button btnDodaj;
    @FXML
    Button btnOtkazi;

    static MakeMeRun makemerun;
    static Stage stg;
    static Controller ctrl;

    public static void setMe(MakeMeRun mmr, Stage stage, Controller control)
    {
        makemerun =mmr;
        stg=stage;
        ctrl=control;
    }

    public void addClan()
    {
        Integer num = ClanKluba.number+2;
        String name = "\""+nameField.getText()+"\"";
        String surname = "\""+surnameField.getText()+"\"";
        String ulogaString = "\""+uloga.getText()+"\"";
        String kontaktString = "\""+kontaktField.getText()+"\"";
        String stmtString = "INSERT INTO `CLAN KLUBA` (ID,Ime,Prezime,Kontakt,Uloga,KLUB_ID)\n" +
                "VALUES("+num.toString()+","+name+","+surname+","+kontaktString+","+ulogaString+",1);";
        try {
            makemerun.updateStatement(stmtString);
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greska");
            alert.setHeaderText("Dogodila se greska");
            alert.setContentText("Problem pri dodavanju clana kluba");

            alert.showAndWait();
        }
        ctrl.actClanoviKluba();

        nameField.setText("");
        surnameField.setText ("");
        uloga.setText ("");
        kontaktField.setText ("");
    }

    public void cancel()
    {
        stg.close();
    }
}
