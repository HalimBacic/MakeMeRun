package Main.Forms;

import Entity.MedijskiSaradnik;
import Entity.Promocija;
import Main.MakeMeRun;
import Osoba.Promotor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PromotoriControl {

    @FXML
    private Button btnDodajProm;

    @FXML
    private SplitMenuButton btnPregledPromotora;

    @FXML
    private Button btnIzlaz;

    @FXML
    private Button medijskiSaradnici;

    @FXML
    private Button dodajAkrivnost;

    @FXML
    private Pane tablePane;

    @FXML
    private TableView<MedijskiSaradnik> medijskiSaradnik;

    @FXML
    private TableColumn<?, ?> imeMedSarCol;

    @FXML
    private TableColumn<?, ?> brTelMedSarCol;

    @FXML
    private TableColumn<?, ?> adrMedSarCol;

    @FXML
    private TableColumn<?, ?> tipSarMedCarCol;

    @FXML
    private TableColumn<?, ?> terminMedSarCol;

    @FXML
    private TableView<Promocija> tablePromotori;

    @FXML
    private TableColumn<?, ?> trkaPromCol;

    @FXML
    private TableColumn<?, ?> promImePromCol;

    @FXML
    private TableColumn<?, ?> promPrezimePromCol;


    @FXML
    private TableColumn<?, ?> aktivnostPromCol;

    @FXML
    private TableColumn<?, ?> terminPromCol;

    static MakeMeRun makeMeRun;
    static Stage stage;
    static String imeTrke;


    public static void setMe(MakeMeRun mmr,Stage stg,String it)
    {
        makeMeRun=mmr;
        stage=stg;
        imeTrke=it;
    }

    @FXML
    public void closeMe() {
        stage.close ();
    }

    public void dodajPromotora() throws IOException {
        FXMLLoader loader = new FXMLLoader (getClass ().getResource ("dodajPromotora.fxml"));
        Parent root = loader.load ();
        Stage primaryStage = new Stage ();
        primaryStage.setTitle ("Dodaj promotora");
        primaryStage.setScene (new Scene (root , 306 , 293));
        DodajPromotoraControl.setMe (makeMeRun,primaryStage,imeTrke);
        primaryStage.show ();
    }

    public void loadPromotori()
    {
        btnPregledPromotora.getItems ().clear ();

        ArrayList<String> data = new ArrayList<> ();
        try {
            ResultSet rs = makeMeRun.executeStatement ("SELECT * FROM `Promotor trke` WHERE `Ime trke`=\""+imeTrke+"\";");
            while (rs.next ())
                data.add (rs.getString (2)+" "+rs.getString (3));
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        for (String s : data) {
            MenuItem m = new MenuItem (s);
            m.setOnAction (new EventHandler<ActionEvent> () {
                @Override
                public void handle (ActionEvent actionEvent) {
                    try {
                        btnPregledPromotora.setText (m.getText ());
                        otvoriPromotora (m.getText ());
                    } catch (IOException e) {
                        e.printStackTrace ();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace ();
                    }
                }
            });
            btnPregledPromotora.getItems ().add (m);
        }
    }

    public void otvoriPromotora(String name) throws IOException, SQLException
    {
            medijskiSaradnik.setVisible (false);
            dodajAkrivnost.setDisable (false);

            String[] imeIPrezime= name.split (" ");

            String stmt="SELECT `Promovise`.`TRKA_naziv`,`Promotor trke`.`Ime`,`Promotor trke`.`Prezime`,`Aktivnost`.`Opis`,`Aktivnost`.`Datum`\n" +
                    "FROM `Promovise`,`Promotor trke`,`Aktivnost`\n" +
                    "WHERE `Promovise`.`AKTIVNOST_ID`=`Aktivnost`.`ID` && `Promovise`.`Promotor trke_ID`=`Promotor trke`.`ID` " +
                    "&& `Promotor trke`.`Ime`=\""+imeIPrezime[0]+"\" && `Promotor trke`.`Prezime`=\""+imeIPrezime[1]+
                    "\" &&  `Promotor trke`.`Ime trke`=\""+imeTrke+"\";";

            ArrayList<Promocija> data=new ArrayList<> ();
            ResultSet rs = makeMeRun.executeStatement (stmt);
            while(rs.next ())
            {
                String nazivTrke= rs.getString (1);
                String imePromotora=rs.getString (2);
                String prezimePromotora=rs.getString (3);
                String aktivnost=rs.getString (4);
                String datum = (rs.getDate (5)).toString ();
                Promocija promocija=new Promocija (nazivTrke,imePromotora,prezimePromotora,aktivnost,datum);
                data.add (promocija);
            }

            ObservableList<Promocija> observableList = FXCollections.observableList (data);
            trkaPromCol.setCellValueFactory (new PropertyValueFactory<> ("imeTrke"));
            promImePromCol.setCellValueFactory (new PropertyValueFactory<> ("imePromotora"));
            promPrezimePromCol.setCellValueFactory (new PropertyValueFactory<> ("prezimePromotora"));
            aktivnostPromCol.setCellValueFactory (new PropertyValueFactory<> ("aktivnost"));
            terminPromCol.setCellValueFactory (new PropertyValueFactory<> ("termin"));
            tablePromotori.setItems (observableList);
            tablePromotori.setVisible (true);
            medijskiSaradnik.setVisible (false);
    }

    public void otvoriMedijskogSaradnika() throws SQLException {

        dodajAkrivnost.setDisable (true);
        btnPregledPromotora.setText ("Pregled promotora");
        tablePromotori.setVisible (false);
        ArrayList<MedijskiSaradnik> data = new ArrayList<> ();
        String stmtMs = "SELECT * FROM `Medijski saradnik` WHERE `TRKA_Naziv`=\""+imeTrke+"\";";
        ResultSet rs = makeMeRun.executeStatement (stmtMs);
        while(rs.next())
        {
            String ime = rs.getString (2);
            String adresa = rs.getString (4);
            String kontakt = rs.getString (3);
            String tip=rs.getString (5);
            String termin = (rs.getDate(6)).toString ();
            MedijskiSaradnik ms=new MedijskiSaradnik (ime,adresa,kontakt,tip,termin,imeTrke);
            data.add (ms);
;       }
        ObservableList<MedijskiSaradnik> observableList = FXCollections.observableList (data);
        imeMedSarCol.setCellValueFactory (new PropertyValueFactory<> ("ime"));
        brTelMedSarCol.setCellValueFactory (new PropertyValueFactory<> ("kontakt"));
        adrMedSarCol.setCellValueFactory (new PropertyValueFactory<> ("adresa"));
        tipSarMedCarCol.setCellValueFactory (new PropertyValueFactory<> ("tipSaradnje"));
        terminMedSarCol.setCellValueFactory (new PropertyValueFactory<> ("termin"));
        medijskiSaradnik.setItems (observableList);
        medijskiSaradnik.setVisible (true);
    }

    public void dodajAktivnost() throws SQLException, IOException {

        String[] imeIprezime=btnPregledPromotora.getText ().split (" ");
        String stmt = "SELECT * FROM `Promotor trke` WHERE `Ime`=\""+imeIprezime[0]+"\" && `Prezime`=\""+imeIprezime[1]+"\";";
        ResultSet rsNumber = makeMeRun.executeStatement (stmt);
        Integer broj=0;
            while(rsNumber.next ())
                broj=rsNumber.getInt (1);

        Promotor promotor = new Promotor (broj,imeIprezime[0],imeIprezime[1],imeTrke);
        FXMLLoader loader=new FXMLLoader (getClass ().getResource ("dodajAktivnost.fxml"));
        Parent root = loader.load ();
        Stage primaryStage = new Stage ();
        primaryStage.setTitle ("Aktivnosti");
        primaryStage.setScene (new Scene (root , 817 , 516));
        dodajAktivnostControl.setMe (makeMeRun,primaryStage);
        dodajAktivnostControl aktivnostControl = loader.getController ();
        aktivnostControl.setPromotor (promotor);
        primaryStage.show ();
    }
}
