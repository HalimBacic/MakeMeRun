package Main.Forms;

import Main.Controller;
import Main.MakeMeRun;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrkaControl {

    @FXML
    private TextField nazivTrke;

    @FXML
    private TextField datumTrke;

    @FXML
    private TextArea napomena;

    @FXML
    private TextArea trkackiPaketi;

    @FXML
    private TextField aims;

    @FXML
    private TextField distanca;

    @FXML
    private TextField brTrkaca;


    static MakeMeRun makeMeRun;
    static Controller control;
    static Stage stage;
    private String imeTrke;

    public static void setMe(MakeMeRun mmr,Controller ctrl,Stage stg)
    {
        makeMeRun=mmr;
        control=ctrl;
        stage=stg;
    }



    public void initialize(String naziv) throws SQLException {
        String stmt="SELECT * FROM `Trka` WHERE `Naziv`=\""+naziv+"\";";
        ResultSet rs=makeMeRun.executeStatement (stmt);
        imeTrke=naziv;
        while(rs.next ())
        {
            nazivTrke.setText (rs.getString (1));
            datumTrke.setText (rs.getString (3));
            napomena.setText (rs.getString (4));
            aims.setText (rs.getString (6));
            distanca.setText ("Distanca  "+rs.getString (2));
            ResultSet number = makeMeRun.executeStatement ("SELECT COUNT(*) FROM `Trkac` WHERE `TRKA_Naziv`=\""
                    +nazivTrke.getText ()+"\";");
            while(number.next ())
                brTrkaca.setText ("Broj trkaca  "+Integer.toString (number.getInt (1)));
        }

        String stmtStartniPaket="SELECT * FROM `Startni paket` WHERE `TRKA_Naziv`=\""+nazivTrke.getText ()+"\"";
        rs=makeMeRun.executeStatement (stmtStartniPaket);
        String paket="";
        while(rs.next ())
        {
            int cijena=rs.getInt (2);
            paket+="Startni paket od "+cijena+"KM\n";
            trkackiPaketi.setText (paket);
        }
    }

    public void closeMe()
    {
        stage.close ();
    }

    public void dodajTrkaca() throws IOException {
        FXMLLoader loader = new FXMLLoader (getClass ().getResource ("addTrkac.fxml"));
        Parent root = loader.load ();
        Stage primaryStage = new Stage ();
        primaryStage.setTitle ("Dodaj trkaca");
        addTrkacControl.setMe (makeMeRun,control,primaryStage);
        addTrkacControl trkacControl = (addTrkacControl) loader.getController ();
        trkacControl.setTrka (nazivTrke.getText ());
        primaryStage.setScene (new Scene (root , 363 , 469));
        primaryStage.show ();
    }

    public void pregledajTrkace() throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader (getClass ().getResource ("pregledTrkaca.fxml"));
        Parent root = loader.load ();
        Stage primaryStage = new Stage ();
        primaryStage.setTitle ("Pregled trkaca");
        PregledTrkacaControl.setMe(makeMeRun,primaryStage,imeTrke);
        PregledTrkacaControl pregled = (PregledTrkacaControl) loader.getController ();
        pregled.writeTable ();
        primaryStage.setScene (new Scene (root , 672 , 454));
        primaryStage.show ();
    }

    public void promocijaTrke() throws IOException {
        FXMLLoader loader = new FXMLLoader (getClass ().getResource ("promotori.fxml"));
        Parent root = loader.load ();
        Stage primaryStage = new Stage ();
        primaryStage.setTitle ("Promocija trke");
        primaryStage.setScene (new Scene (root , 945 , 587));
        PromotoriControl.setMe (makeMeRun,primaryStage,imeTrke);
        PromotoriControl promotoriControl = loader.getController ();
        promotoriControl.loadPromotori ();
        primaryStage.show ();
    }

    public void sponzori() throws IOException {
        FXMLLoader loader = new FXMLLoader (getClass ().getResource ("sponzori.fxml"));
        Parent root = loader.load ();
        Stage primaryStage = new Stage ();
        primaryStage.setTitle ("Promocija trke");
        primaryStage.setScene (new Scene (root , 730 , 412));
        SponzoriControl.setMe (makeMeRun,primaryStage);
        SponzoriControl sponzori = loader.getController ();
        sponzori.loadSponzori ();
        primaryStage.show ();
    }

    public void urediPaket() throws IOException {
        FXMLLoader loader = new FXMLLoader (getClass ().getResource ("paket.fxml"));
        Parent root = loader.load ();
        Stage primaryStage = new Stage ();
        primaryStage.setTitle ("Promocija trke");
        primaryStage.setScene (new Scene (root , 524 , 332));
        PaketControl.setMe (makeMeRun,primaryStage,nazivTrke.getText ());

        primaryStage.show ();
    }

    public String getPaket()
    {
        String npm=trkackiPaketi.getText ();
        return npm;
    }

    public void setPaket(String napomenaText)
    {
        trkackiPaketi.setText (napomenaText);
    }

}
