package Main.Forms;

import Main.Controller;
import Main.MakeMeRun;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class KreirajTrkuControl {

    @FXML
    TextField naziv;
    @FXML
    TextField distanca;
    @FXML
    TextField datum;
    @FXML
    TextField aims;
    @FXML
    TextField napomena;
    @FXML
    TextField tip;

    public static Integer idArh;
    static Stage stage;
    static MakeMeRun makeMeRun;

    public static void setMe(Stage s,MakeMeRun mmr) throws SQLException {
        stage=s;
        makeMeRun=mmr;
        ResultSet rs = mmr.executeStatement ("SELECT COUNT(*) FROM `Arhiva`;");
        rs.next ();
        idArh=Integer.parseInt (String.valueOf (rs.getInt (1)))+1;
    }

    public void closeMe()
    {
        stage.close ();
    }

    public void addToDatabase() throws IOException {
        if(findAIMS (aims.getText ()))
        {
            napraviArhivu ();
            napraviTrku ();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("AIMS");
            alert.setHeaderText("Unos AIMS Certifikata");
            alert.setContentText("Prvo unesite detalje o AIMS sertifikatu!");

            alert.showAndWait();

            try {
            FXMLLoader loader = new FXMLLoader (getClass ().getResource ("aims.fxml"));
            Parent root = loader.load ();
            Stage primaryStage = new Stage ();
            primaryStage.setTitle ("AIMS");
            primaryStage.setScene (new Scene (root, 453, 503));
            Aimscontrol.setMe(aims.getText (),primaryStage,makeMeRun);
            primaryStage.show ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }

        FXMLLoader loader = new FXMLLoader (getClass ().getResource ("/Main/main.fxml"));
        loader.load ();
        Controller control = loader.getController ();
        control.inicijalizujPregledTrka ();
    }

    public void napraviArhivu()
    {
        String stmtArh = "INSERT INTO `Arhiva`(`Naziv trke`,`Datum`)\n" +
                "VALUES(\""+naziv.getText ()+"\",\""+datum.getText ()+"\");";

        try {
            makeMeRun.updateStatement (stmtArh);
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greska");
            alert.setHeaderText("Dogodila se greska");
            alert.setContentText("Problem pri arhiviranju!");

            alert.showAndWait();
        }
    }

    public void napraviTrku() {
        String stmt = "INSERT INTO `Trka`(`Naziv`,`Distanca`,`Datum`,`Napomena`,`KLUB_ID`,`AIMS Certifikat_broj`,`ARHIVA_ID`)\n" +
                    "VALUES (\""+naziv.getText ()+"\",\""+distanca.getText ()
                    +"\",\""+datum.getText ()+"\",\""+napomena.getText ()+"\",\""+1+"\",\""+aims.getText ()+"\",\""+(idArh).toString ()+"\");";
        try {
            makeMeRun.updateStatement (stmt);
            FXMLLoader loader = new FXMLLoader (getClass ().getResource ("/Main/main.fxml"));
            loader.load ();
            Controller control = loader.getController ();
            control.inicijalizujPregledTrka ();
            stage.close ();
        } catch (SQLException | IOException throwables) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greska");
            alert.setHeaderText("Dogodila se greska");
            alert.setContentText("Problem pri izradi trke!");

            alert.showAndWait();
        }
    }

    public boolean findAIMS(String id)
    {
        String stmt="SELECT * FROM `aims certifikat` WHERE Broj=\""+id+"\"";
        try {
            ResultSet rs=makeMeRun.executeStatement (stmt);
            return rs.next ();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greska");
            alert.setHeaderText("Dogodila se greska");
            alert.setContentText("Ne mogu pronaći AIMS certifikat");

            alert.showAndWait();
        }
        return false;
    }
}
