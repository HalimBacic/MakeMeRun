package Main.Forms;

import Main.MakeMeRun;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SponzoriControl {


    @FXML
    private TextField imeSponzoraField;

    @FXML
    private TextField cijenaField;

    @FXML
    private TextField adresaField;

    @FXML
    private TextField kontaktField;

    @FXML
    private SplitMenuButton pregledSponzoraBtn;

    @FXML
    private TextArea ugovorArea;

    @FXML
    private Button urediUgovorBtn;

    @FXML
    private Button potvrdiBtn;

    @FXML
    private Button brisiBtn;

    @FXML
    private Button izlaz;

    private static Stage stage;
    private static MakeMeRun makeMeRun;

    public static void setMe(MakeMeRun mmr,Stage stg)
    {
        makeMeRun=mmr;
        stage=stg;
    }

    @FXML
    void closeMe() {
        stage.close ();
    }

    @FXML
    public void aktivirajPolja()
    {
        pregledSponzoraBtn.setText ("Pregled sponzora");
        imeSponzoraField.setText ("");
        adresaField.setText ("");
        kontaktField.setText ("");
        ugovorArea.setText("");
        imeSponzoraField.setDisable (false);
        adresaField.setDisable (false);
        kontaktField.setDisable (false);
        ugovorArea.setDisable (false);
        cijenaField.setDisable (false);
        imeSponzoraField.setEditable (true);
        adresaField.setEditable (true);
        kontaktField.setEditable (true);
        ugovorArea.setEditable (true);
        cijenaField.setEditable (true);
    }

    @FXML
    public void dodajSponzora() throws SQLException {

        if(!imeSponzoraField.getText ().equals ("") && !imeSponzoraField.isDisabled ())
        {
            String brojStmt = "SELECT COUNT(*) FROM `Sponzor`;";
            Integer brojSponzora=0;
            ResultSet rs = makeMeRun.executeStatement (brojStmt);
            while(rs.next ())
                brojSponzora=rs.getInt (1);
            brojSponzora++;
            String stmt = "INSERT INTO `Sponzor`(`ID`,`Ime`,`Adresa`,`Kontakti`)\n" +
                    "VALUES(\""+brojSponzora.toString ()+"\",\""+imeSponzoraField.getText ()+"\"," +
                    "\""+adresaField.getText ()+"\",\""+kontaktField.getText ()+"\");";

            makeMeRun.updateStatement (stmt);

            String ugovorBroj ="SELECT COUNT(*) FROM `Ugovor`;";
            Integer brojUgovora=0;
            rs = makeMeRun.executeStatement (ugovorBroj);
            while(rs.next ())
                brojUgovora=rs.getInt (1);
            brojUgovora++;


            String stmtUgovor="INSERT INTO `Ugovor`(`Broj`,`Sadrzaj`,`Cijena`) " +
                    "VALUES(\""+brojUgovora.toString ()+"\",\""+ugovorArea.getText ()+"\",\""+cijenaField.getText ()+"\");";
            makeMeRun.updateStatement (stmtUgovor);

            String stmtPotpis = "INSERT INTO `Potpisuju`(`UGOVOR_Broj`,`SPONZORI_ID`,`PREDSJEDNIK KLUBA_ID`)\n" +
                    "VALUES (\""+brojUgovora.toString ()+"\",\""+brojSponzora.toString ()+"\",\"1\");";

            makeMeRun.updateStatement (stmtPotpis);
            imeSponzoraField.setText ("");
            adresaField.setText ("");
            kontaktField.setText ("");
            ugovorArea.setText("");
            imeSponzoraField.setDisable (true);
            adresaField.setDisable (true);
            kontaktField.setDisable (true);
            ugovorArea.setDisable (true);
            loadSponzori ();
        }
        else if(!ugovorArea.getText ().equals (""))
        {
            String stmtIdSponzor = "SELECT `ID` FROM `Sponzor` WHERE `Ime`=\""+imeSponzoraField.getText ()+"\";";
            ResultSet rs = makeMeRun.executeStatement (stmtIdSponzor);
            Integer brojSpnzora=0;
            while(rs.next ())
                brojSpnzora=rs.getInt (1);
            String brojUgovoraStmt="SELECT `UGOVOR_Broj` FROM `Potpisuju` WHERE `SPONZORI_ID`=\""+brojSpnzora.toString ()+"\";";
            rs= makeMeRun.executeStatement (brojUgovoraStmt);
            Integer brojUgovora=0;
            while(rs.next ())
                brojUgovora=rs.getInt (1);

            String stmt="UPDATE `Ugovor` SET `Sadrzaj`=\""+ugovorArea.getText ()+"\";";
            makeMeRun.updateStatement (stmt);
        }
        else
            closeMe ();

    }

    public void loadSponzori()
    {
        pregledSponzoraBtn.getItems ().clear ();

        ArrayList<String> data = new ArrayList<> ();
        try {
            ResultSet rs = makeMeRun.executeStatement ("SELECT * FROM `Sponzor`;");
            while (rs.next ())
                data.add (rs.getString (2));
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        for (String s : data) {
            MenuItem m = new MenuItem (s);
            m.setOnAction (new EventHandler<ActionEvent> () {
                @Override
                public void handle (ActionEvent actionEvent) {
                    try {
                        showSponzorContent(s);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace ();
                    }
                }
            });
            pregledSponzoraBtn.getItems ().add (m);
        }
    }

    public void showSponzorContent(String imeSponzora) throws SQLException {

        brisiBtn.setDisable (false);
        pregledSponzoraBtn.setText(imeSponzora);
        String stmt ="SELECT *  FROM `Sponzor` WHERE `Ime`=\""+imeSponzora+"\"";
        ResultSet rs = makeMeRun.executeStatement (stmt);
        while(rs.next ())
        {
            imeSponzoraField.setText (rs.getString (2));
            adresaField.setText (rs.getString (3));
            kontaktField.setText (rs.getString (4));
            Integer sponzorID=rs.getInt (1);
            String stmtPotpis ="SELECT * FROM `Potpisuju` WHERE `SPONZORI_ID`=\""+sponzorID.toString ()+"\"";
            ResultSet resultSet=makeMeRun.executeStatement (stmtPotpis);
            while(resultSet.next ())
            {
                Integer brojUgovora;
                brojUgovora=resultSet.getInt (1);
                String stmtUgovor = "SELECT `Sadrzaj` FROM `UGOVOR` WHERE `Broj`=\""+brojUgovora.toString ()+"\";";
                ResultSet ugovorSet=makeMeRun.executeStatement (stmtUgovor);
                String ugovor="";
                while(ugovorSet.next ())
                    ugovor = ugovorSet.getString (1);
                ugovorArea.setText (ugovor);
            }
        }

        urediUgovorBtn.setDisable (false);
    }

    @FXML
    void pregledSponzora() {

    }

    @FXML
    void urediUgovor() {
            ugovorArea.setEditable (true);
    }

    public void brisiSponzora() throws SQLException {
        String ime = imeSponzoraField.getText ();

        String idSpnzora = "SELECT `ID` FROM `SPONZOR` WHERE `Ime`=\""+ime+"\";";
        ResultSet rs = makeMeRun.executeStatement (idSpnzora);
        Integer brojSponzora=0;
        while(rs.next ())
            brojSponzora=rs.getInt (1);
        String deletePotpisuju ="DELETE FROM `Potpisuju` WHERE `SPONZORI_ID`=\""+brojSponzora.toString ()+"\";";
        makeMeRun.updateStatement (deletePotpisuju);
        String deleteSponzor="DELETE FROM `Sponzor` WHERE `Ime`=\""+imeSponzoraField.getText ()+"\";";
        makeMeRun.updateStatement (deleteSponzor);
    }

}
