package Main.Forms;

import Main.Controller;
import Main.MakeMeRun;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.SQLException;

public class deleteClanKlubaControl {

    @FXML
    TextField id;

    @FXML
    Button confirm;


    static MakeMeRun makemerun;
    static Stage stage;
    static Controller control;

    public static void setMe(MakeMeRun mmr,Stage stg,Controller ctrl)
    {
        makemerun=mmr;
        stage=stg;
        control=ctrl;
    }

    public void deleteClanKluba()
    {
        String idstr=id.getText();
        Integer id = Integer.parseInt(idstr);
        String stmtstr="DELETE FROM `Clan Kluba` WHERE ID="+id.toString();
        try {
            makemerun.updateStatement(stmtstr);
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greska");
            alert.setHeaderText("Dogodila se greska");
            alert.setContentText("Problem pri brisanju clana");

            alert.showAndWait();
        }
        control.actClanoviKluba();
    }

}
