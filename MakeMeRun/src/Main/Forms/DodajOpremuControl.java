package Main.Forms;

import Main.MakeMeRun;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DodajOpremuControl {

    @FXML
    private TextField imeField;

    @FXML
    private TextField tipField;

    @FXML
    private TextField kontaktField;

    @FXML
    private TextField cijenaField;

    @FXML
    private TextField kolicinaField;

    private static Stage stage;
    private static MakeMeRun makeMeRun;

    public static void setMe (MakeMeRun mmr , Stage stg) {
        makeMeRun = mmr;
        stage = stg;
    }

    public void dodajUBazu () {
        String idPoizvstmt = "SELECT COUNT(*) FROM `Proizvodjac opreme`;";
        Integer idProizv = 0;
        ResultSet rs = null;
        try {
            rs = makeMeRun.executeStatement (idPoizvstmt);
            while (rs.next ())
                idProizv = rs.getInt (1);
            idProizv++;
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greska");
            alert.setHeaderText("Dogodila se greska");
            alert.setContentText("Problem pri dodavanju proizvodjaca :57");

            alert.showAndWait();
        }


        String proizvStmt = "INSERT INTO `Proizvodjac opreme`(`Tip proizvoda`,`Kontakt`,`Ime`,`KLUB_ID`)\n" +
                "VALUES (\"" + tipField.getText () + "\",\"" + kontaktField.getText () +
                "\",\"" + imeField.getText () + "\",\"1\");";

        try {
            makeMeRun.updateStatement (proizvStmt);
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greska");
            alert.setHeaderText("Dogodila se greska");
            alert.setContentText("Problem pri dodavanju proizvodjaca opreme:73");
            alert.showAndWait();
        }

        Integer idOpreme = 0;
        String idOpremeStmt = "SELECT COUNT(*) FROM `Oprema`;";
        try {
            rs = makeMeRun.executeStatement (idOpremeStmt);
            while (rs.next ())
                idOpreme = rs.getInt (1);
            idOpreme++;
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greska");
            alert.setHeaderText("Dogodila se greska");
            alert.setContentText("Problem pri dodavanju opreme:89");

            alert.showAndWait();
        }

        try {
            LocalDateTime date=LocalDateTime.now ();
            String opremaStmt = "INSERT INTO `Oprema`(`Id`,`Tip`,`Cijena`,`Kolicina`)\n" +
                    "VALUES(\"" + idOpreme.toString () + "\",\"" + tipField.getText () + "\",\"+"
                    + cijenaField.getText () + "\",\"" + kolicinaField.getText () + "\");";

            if (makeMeRun.updateStatement (opremaStmt)) {

                String stmt = "INSERT INTO `Proizvodnja`(`OPREMA_Id`,`PROIZVODJACI OPREME_ID`) " +
                        "VALUES (\"" + idOpreme.toString () + "\",\"" + idProizv.toString () + "\");";

                makeMeRun.updateStatement (stmt);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greska");
            alert.setHeaderText("Dogodila se greska");
            alert.setContentText("Problem pri dodavanju opreme");

            alert.showAndWait();
        }
    }

    public void closeMe () {
        stage.close ();
    }
}
