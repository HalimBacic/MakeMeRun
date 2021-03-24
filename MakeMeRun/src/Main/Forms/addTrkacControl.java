package Main.Forms;

import Main.Controller;
import Main.MakeMeRun;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class addTrkacControl {

    @FXML
    private TextField ime;

    @FXML
    private TextField prezime;

    @FXML
    private TextField distanca;

    @FXML
    private TextField drzava;

    @FXML
    private TextField godine;

    @FXML
    private TextField kontakt;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnOtkazi;

    @FXML
    private CheckBox uplata;

    private String trka;
    private Integer broj=0;

    static MakeMeRun makeMeRun;
    static Controller control;
    static Stage stage;

    public static void setMe (MakeMeRun mmr , Controller ctrl , Stage stg) {
        makeMeRun = mmr;
        control = ctrl;
        stage = stg;
    }

    public void setTrka (String t)
    {
        trka=t;
    }

    public void closeMe()
    {
        stage.close();
    }

    public void dodajTrkaca() throws SQLException, IOException {
            String stmtBroj="SELECT COUNT(*) FROM `Trkac`;";
            ResultSet rs=makeMeRun.executeStatement (stmtBroj);
            while(rs.next ())
                broj = rs.getInt (1);

            broj+=100;
            Boolean isPaid;
            if(uplata.isSelected ())
                isPaid=true;
            else
                isPaid=false;


            String stmt="INSERT INTO `Trkac`(`Broj`,`Ime`,`Prezime`,`Distanca`,`Godine`,`Drzava`,`Uplata`,`Kontakt`,`TRKA_naziv`)\n" +
                    "VALUES (\""+broj.toString ()+"\",\""+ime.getText ()+"\",\""+prezime.getText ()+"\"," +
                    "\""+distanca.getText ()+"\",\""+godine.getText ()+"\",\""+drzava.getText ()+
                    "\",\""+isPaid.toString ()+"\",\""+kontakt.getText ()+"\",\""+trka+"\");";

            makeMeRun.updateStatement (stmt);

            if(isPaid) {
                obradiPriznanice();
            }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Dodavanje trkaca");
        alert.setHeaderText(null);
        alert.setContentText("Trkac uspjesno memorisan!");
        alert.showAndWait();

        ime.setText (""); prezime.setText(""); distanca.setText (""); godine.setText (""); drzava.setText ("");
        kontakt.setText ("");
    }

    public void obradiPriznanice() throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader (getClass ().getResource ("priznanica.fxml"));
        Parent root=loader.load ();
        Stage primaryStage = new Stage ();
        primaryStage.setTitle ("Pregledaj trku");
        primaryStage.setScene (new Scene (root , 287 , 287));
        PriznanicaControl.setMe (makeMeRun,primaryStage,control);
        PriznanicaControl prizControl = (PriznanicaControl) loader.getController ();
        prizControl.setBrojTrkaca (broj,addTrkacControl.stage);
        primaryStage.show ();
    }
}
