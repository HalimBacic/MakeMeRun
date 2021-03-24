package Main;

import Entity.BankovniRacun;
import Entity.Kontakt;
import Entity.Oprema;
import Entity.Ugovor;
import Main.Forms.*;
import Osoba.ClanKluba;
import javafx.application.Platform;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

//TODO Ispis startnih paketa i trkača u fajl

public class Controller {

    @FXML
    GridPane gridMenu;
    @FXML
    Button btnClanovi;
    @FXML
    Button btnKontakti;
    @FXML
    TextField searchField;
    @FXML
    Button btnIzlaz;
    @FXML
    HBox hbox;
    @FXML
    Button btnShow;
    @FXML
    HBox clanoviBox;
    @FXML
    HBox kontaktiBox;
    @FXML
    Pane tablePane;
    @FXML
    Pane paneClanovi;
    @FXML
    Pane paneTrka;
    @FXML
    TableView<ClanKluba> tableClanovi;
    @FXML
    TableColumn<Object, Object> id;
    @FXML
    TableColumn<Object,Object> ime;
    @FXML
    TableColumn<Object,Object> prezime;
    @FXML
    TableColumn<Object,Object> uloga;
    @FXML
    HBox trkaBox;
    @FXML
    Pane kontaktiPane;
    @FXML
    TableView<BankovniRacun> tableRacuni;
    @FXML
    TableColumn<String,String> bankaColumn;
    @FXML
    TableColumn<String, String> brojRacunaColumn;
    @FXML
    TableColumn<Object,Object> stanjeColumn;
    @FXML
    Pane paneRacun;

    ArrayList<Ugovor> lista = new ArrayList<> ();

    @FXML
    Button btnTrkaBox;
    @FXML
    Button btnAIMS;
    @FXML
    Button btnRedari;
    @FXML
    MenuButton pregledTrka;
    @FXML
    HBox boxRacuni;
    @FXML
    TextField searchKontaktField;
    @FXML
    TableView<Oprema> opremaTable;
    @FXML
    TableColumn<Object,Object> tipOpremaTable;
    @FXML
    TableColumn<Object,Object> kolicinaOpremaTable;
    @FXML
    TableColumn<Object,Object> cijenaOpremaTable;
    @FXML
    TableColumn<Object,Object> proizOpremaTable;
    @FXML
    TableView<Kontakt> TableKontakt;
    @FXML
    TableColumn<Object,Object> imeTableKontakt;
    @FXML
    TableColumn<Object,Object> opisTableKontakt;
    @FXML
    TableColumn<Object,Object> konTableKontakt;
    @FXML
    TableColumn<Object,Object> ugovoriColumn;

    @FXML
    TableView<Ugovor> ugovoriTable;

    @FXML
    Pane ugovorPane;

    @FXML
    TextArea ugovoriArea;

    @FXML
    MenuButton urediRacunSplitBtn;

    @FXML
    Button obrisiRacunBtn;

    @FXML
    Button urediRacunBtn;

    @FXML
    TextField racunField;

    @FXML
    TextField brisiKontaktField;

    static Connection conn;
    static MakeMeRun makeMeRun;

    public static void setConnection(MakeMeRun mmr) {
        makeMeRun=mmr;
        conn= mmr.getConn();
    }

    public void closeConnection() throws SQLException {
        conn.close();
    }

    public void exit()
    {
        try {
            closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Platform.exit();
        System.exit(0);
    }

    public void hideMenu()
    {
        gridMenu.setVisible(false);
        hbox.setVisible(true);
    }

    public void showMenu()
    {
        gridMenu.setVisible(true);
        paneClanovi.setVisible(false);
        paneTrka.setVisible (false);
        paneRacun.setVisible (false);
        kontaktiPane.setVisible (false);
        ugovorPane.setVisible(false);

        hbox.setVisible(false);
        clanoviBox.setVisible (false);
        kontaktiBox.setVisible(false);
        trkaBox.setVisible (false);
        boxRacuni.setVisible (false);
    }

    public void showContent(Pane pn,HBox box)
    {
            gridMenu.setVisible (false);
            pn.setVisible (true);
            hbox.setVisible (true);
            box.setVisible (true);
    }

    public void addClanKluba()
    {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Forms/addClanKluba.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Dodaj clana kluba");
            primaryStage.setScene(new Scene(root, 577, 350));
            addClanKlubaControl.setMe(makeMeRun,primaryStage,this);
            primaryStage.show();
            } catch (IOException e) {
               e.printStackTrace();
            }
    }

    public void deleteClanKluba()
    {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Forms/deleteClanKluba.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Obrisi clana kluba");
            primaryStage.setScene(new Scene(root, 315, 210));
            deleteClanKlubaControl.setMe(makeMeRun,primaryStage,this);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actClanoviKluba()
    {
        showContent (paneClanovi,clanoviBox);

        try {
            ResultSet rs = makeMeRun.executeStatement("SELECT * FROM `CLAN KLUBA`");
            ArrayList<ClanKluba> data = new ArrayList<>();
            while(rs.next()) {
                Integer id=rs.getInt("ID");
                data.add(new ClanKluba(id, rs.getString("Ime"), rs.getString("Prezime"),
                        rs.getString("Uloga")));
                ClanKluba.setNumber (ClanKluba.number++);
            }
            ObservableList<ClanKluba> dataTable = FXCollections.observableList(data);
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            ime.setCellValueFactory(new PropertyValueFactory<>("ime"));
            prezime.setCellValueFactory(new PropertyValueFactory<>("prezime"));
            uloga.setCellValueFactory(new PropertyValueFactory<>("uloga"));
            tableClanovi.setItems(dataTable);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void searchClanKluba()
    {
        String text = "\""+searchField.getText ()+"\"";
        String stmtstr = "SELECT * FROM `Clan Kluba` WHERE ID="+text+" OR Ime="+text+" OR Prezime="+text+";";
        String legend="ID       IME         PREZIME         KONTAKT         ULOGA";
        search(legend,stmtstr,5);
    }

    public void searchKontakt()
    {

        String stmtstr = "SELECT * FROM `Kontakt` WHERE `Ime kontakta` LIKE \"%"+searchKontaktField.getText ()+"%\";";
        String legend="ID       IME         OPIS           KONTAKT";
        search(legend, stmtstr,4);
    }

    public void search(String legend,String stmt,Integer elems)
    {
        try {
            ResultSet rs=makeMeRun.executeStatement(stmt);

            if(!rs.next ())
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Rezultat pretrage");
                alert.setHeaderText(null);
                alert.setContentText("Pretraga nije dala rezultat");
                alert.showAndWait();
            }
            else
            {
                rs=makeMeRun.executeStatement(stmt);
                try {
                    FXMLLoader loader = new FXMLLoader (getClass().getResource("Forms/resultBox.fxml"));
                    Parent root = loader.load ();
                    ResultBoxControl resultBox = loader.getController ();
                    resultBox.tableContent (legend,rs,elems);
                    Stage primaryStage = new Stage();
                    primaryStage.setTitle("Rezultat pretrage");
                    primaryStage.setScene(new Scene(root, 650, 351));
                    ResultBoxControl.setMe (primaryStage);
                    primaryStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void actKontakti() throws SQLException {
        showContent (kontaktiPane,kontaktiBox);
        popuniKontakte ();
    }

    public void kreirajTrku() //Iz menija
    {
            showContent (trkaBox,trkaBox);
            inicijalizujPregledTrka ();
    }

    public void kreirajTrkuForm()
    {
        try {
            FXMLLoader loader = new FXMLLoader (getClass ().getResource ("Forms/kreirajTrku.fxml"));
            Parent root = loader.load ();
            Stage primaryStage = new Stage ();
            primaryStage.setTitle ("Kreiraj trku");
            primaryStage.setScene (new Scene (root, 581, 320));
            KreirajTrkuControl.setMe(primaryStage,makeMeRun);
            primaryStage.show ();
        }catch(Throwable throwable)
        {
            throwable.printStackTrace ();
        }
    }


    public void backMe()
    {
            showMenu();
    }

    public void seeAIMS () {
        try {
            FXMLLoader loader = new FXMLLoader (getClass ().getResource ("Forms/aimsView.fxml"));
            Parent root = loader.load ();
            Stage primaryStage = new Stage ();
            primaryStage.setTitle ("Kreiraj trku");
            primaryStage.setScene (new Scene (root , 533 , 448));
            AimsViewControl.setMe (this , makeMeRun,primaryStage);
            AimsViewControl aimsControl = loader.getController ();
            aimsControl.showAIMS ();
            primaryStage.show ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    public void dodajRedaraForm()
    {
        try {
            FXMLLoader loader = new FXMLLoader (getClass ().getResource ("Forms/redari.fxml"));
            Parent root = loader.load ();
            Stage primaryStage = new Stage ();
            primaryStage.setTitle ("Kreiraj trku");
            primaryStage.setScene (new Scene (root , 796 , 382));
            RedariControl.setMe (this,makeMeRun,primaryStage);
            RedariControl redariControl = loader.getController ();
            redariControl.ispuniTabelu ();
            primaryStage.show ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    public void inicijalizujPregledTrka() {

        pregledTrka.getItems ().clear ();


        ArrayList<String> data = new ArrayList<> ();
        try {
            ResultSet rs = makeMeRun.executeStatement ("SELECT * FROM `Trka`;");
            while (rs.next ())
                data.add (rs.getString (1));
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        for (String s : data) {
            MenuItem m = new MenuItem (s);
            m.setOnAction (actionEvent -> {
                try {
                    otvoriTrku (m.getText ());
                } catch (IOException | SQLException e) {
                    e.printStackTrace ();
                }
            });
            pregledTrka.getItems ().add (m);
        }
    }

    public void otvoriTrku(String str) throws IOException, SQLException {
            FXMLLoader loader = new FXMLLoader (getClass ().getResource ("Forms/trka.fxml"));
            Parent root = loader.load ();
            Stage primaryStage = new Stage ();
            primaryStage.setTitle ("Pregledaj trku");
            primaryStage.setScene (new Scene (root , 809 , 547));
            TrkaControl trkaControl = loader.getController ();
            TrkaControl.setMe (makeMeRun,this,primaryStage);
            trkaControl.initialize (str);
            primaryStage.show ();
    }

    public void otvoriRacune() throws SQLException {

        showContent (paneRacun,boxRacuni);
        urediRacunSplitBtn.getItems ().clear ();
        urediRacunSplitBtn.setText ("Uredi racune");
        obrisiRacunBtn.setDisable (true);
        racunField.setDisable (true);
        racunField.setText ("");
        hideMenu ();



        String stmt = "SELECT * FROM `Racun`";
        ResultSet rs = makeMeRun.executeStatement (stmt);
        ArrayList<BankovniRacun> data = new ArrayList<> ();

        while(rs.next ())
        {
            BankovniRacun r = new BankovniRacun(rs.getString (2),rs.getString (1),rs.getInt (4));
            String broj = rs.getString (1);
            MenuItem m=new MenuItem (broj);
            m.setOnAction (new EventHandler<ActionEvent> () {
                @Override
                public void handle (ActionEvent actionEvent) {
                    try {
                        racunClick (m.getText ());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace ();
                    }
                }
            });
            urediRacunSplitBtn.getItems ().add (m);
            data.add (r);
        }
        ObservableList<BankovniRacun> observableList=FXCollections.observableList (data);
        brojRacunaColumn.setCellValueFactory (new PropertyValueFactory<> ("brojRacuna"));
        bankaColumn.setCellValueFactory (new PropertyValueFactory<> ("imeBanke"));
        stanjeColumn.setCellValueFactory (new PropertyValueFactory<> ("stanje"));
        tableRacuni.setItems (observableList);
    }

    public void dodajRacun() throws IOException {
        FXMLLoader loader = new FXMLLoader (getClass ().getResource ("Forms/dodajRacun.fxml"));
        Parent root = loader.load ();
        Stage primaryStage = new Stage ();
        primaryStage.setTitle ("Dodaj racun");
        primaryStage.setScene (new Scene (root , 448 , 351));
        DodajRacunControl.setMe (makeMeRun,primaryStage);
        primaryStage.show ();
    }

    public void prikaziOpremu() throws SQLException {

        paneTrka.setVisible (true);
        paneClanovi.setVisible (false);

        String stmtOprema = "SELECT * FROM `Oprema`;";
        ResultSet rsOprema = makeMeRun.executeStatement (stmtOprema);

        ArrayList<Oprema> data=new ArrayList<> ();

        while(rsOprema.next ())
        {
            int idOpreme=rsOprema.getInt (1);
            String stmtProizvodnja = "SELECT * FROM `Proizvodnja` WHERE `OPREMA_Id`=\""+ idOpreme +"\";";

            ResultSet rsProizvodnja = makeMeRun.executeStatement (stmtProizvodnja);

            while(rsProizvodnja.next ())
            {
                int brojProizvodjaca=rsProizvodnja.getInt (2);
                String stmt = "SELECT * FROM `Proizvodjac Opreme` WHERE `ID`=\""+ brojProizvodjaca +"\";";
                ResultSet rsProizvodjac= makeMeRun.executeStatement (stmt);
                while(rsProizvodjac.next ())
                {
                    Integer id = rsOprema.getInt (1);
                    String tip = rsOprema.getString (2);
                    Integer cijena=rsOprema.getInt (3);
                    Integer kolicina=rsOprema.getInt(4);
                    String imeProizvodjaca=rsProizvodjac.getString (4);
                    Oprema oprema=new Oprema (id,tip,cijena,kolicina,imeProizvodjaca);
                    data.add (oprema);
                }
            }
        }

        ObservableList<Oprema> lista = FXCollections.observableArrayList (data);
        tipOpremaTable.setCellValueFactory (new PropertyValueFactory<> ("tip"));
        cijenaOpremaTable.setCellValueFactory (new PropertyValueFactory<> ("cijena"));
        kolicinaOpremaTable.setCellValueFactory (new PropertyValueFactory<> ("kolicina"));
        proizOpremaTable.setCellValueFactory (new PropertyValueFactory<> ("proizvodjac"));
        opremaTable.setItems (lista);
        opremaTable.setVisible (true);
    }

    public void dodajOpremu() throws IOException {
        FXMLLoader loader = new FXMLLoader (getClass ().getResource ("Forms/dodajOpremu.fxml"));
        Parent root = loader.load ();
        Stage primaryStage = new Stage ();
        primaryStage.setTitle ("Dodaj opremu");
        primaryStage.setScene (new Scene (root , 541 , 303));
        DodajOpremuControl.setMe (makeMeRun,primaryStage);
        primaryStage.show ();
    }

    public void popuniKontakte() throws SQLException {
        String stmt="SELECT * FROM `Kontakt`";
        ResultSet rs = makeMeRun.executeStatement (stmt);
        ArrayList<Kontakt> data = new ArrayList<> ();

        while(rs.next ())
        {
            data.add(new Kontakt (rs.getString (2),rs.getString (3),rs.getString (4)));
        }

        ObservableList<Kontakt> observableList = FXCollections.observableList (data);
        imeTableKontakt.setCellValueFactory (new PropertyValueFactory<> ("ime"));
        opisTableKontakt.setCellValueFactory (new PropertyValueFactory<> ("opis"));
        konTableKontakt.setCellValueFactory (new PropertyValueFactory<> ("kontakt"));
        TableKontakt.setItems (observableList);
    }

    public void prikazUgovora() throws SQLException {
        hideMenu ();
        hbox.setVisible (true);
        ugovorPane.setVisible (true);

        String stmtIme="SELECT `ID`,`Ime` FROM `Sponzor`;";
        ResultSet rsSponzori = makeMeRun.executeStatement (stmtIme);


        while(rsSponzori.next ()) {
            String stmtPotpisuju = "SELECT `UGOVOR_Broj` FROM `Potpisuju` WHERE `SPONZORI_ID`=\"" + rsSponzori.getString (1) + "\";";
            ResultSet rsPotpisuju = makeMeRun.executeStatement (stmtPotpisuju);
            while (rsPotpisuju.next ()) {
                String stmtSadrzaj ="SELECT * FROM `UGOVOR` WHERE `Broj`=\""+rsPotpisuju.getString (1)+"\";";
                ResultSet rsUgovor=makeMeRun.executeStatement (stmtSadrzaj);
                while(rsUgovor.next ())
                    lista.add (new Ugovor (rsUgovor.getInt (1),rsSponzori.getString (2), rsUgovor.getString (2),rsUgovor.getInt (3)));
            }
        }
        ObservableList<Ugovor> observableList=FXCollections.observableList (lista);
        ugovoriColumn.setCellValueFactory (new PropertyValueFactory<> ("ime"));
        ugovoriTable.setItems (observableList);
    }

    public void findUgovori() {
        String ime=ugovoriTable.getSelectionModel ().getSelectedItem ().getIme ();
            StringBuilder ugovori= new StringBuilder ();
            for(Ugovor u : lista)
                if(u.getIme ().equals (ime))
                {
                    ugovori.append (u.getSadrzaj ()).append ("\n");
                }
            ugovoriArea.setText (ugovori.toString ());
    }

    public void racunClick(String broj) throws SQLException {
            racunField.setDisable (false);
            urediRacunBtn.setDisable (false);
            obrisiRacunBtn.setDisable (false);

            urediRacunSplitBtn.setText (broj);
    }

    public void obrisiRacun() throws SQLException {
        String broj=urediRacunSplitBtn.getText ();

        String stmt="DELETE FROM `Racun` WHERE `BrojRacuna`=\""+broj+"\"";

        makeMeRun.updateStatement (stmt);

        otvoriRacune ();
    }

    public void editujStanje() throws SQLException {
        String novoStanje=racunField.getText ();

        String stmt="UPDATE `Racun`\n" +
                "SET `Stanje racuna`=\""+novoStanje+"\" \n" +
                "WHERE `BrojRacuna`=\""+urediRacunSplitBtn.getText ()+"\";";

        makeMeRun.updateStatement (stmt);

        otvoriRacune ();
    }

    public void dodajKontakt() throws IOException {
            FXMLLoader loader = new FXMLLoader (getClass ().getResource ("Forms/dodajKontakt.fxml"));
            Parent root=loader.load ();
            Stage primaryStage = new Stage ();
            primaryStage.setTitle ("Dodaj racun");
            primaryStage.setScene (new Scene (root , 388 , 311));
            DodajKontaktControl.setMe (primaryStage,makeMeRun);
            primaryStage.show ();
    }

    public void brisiKontakt() throws SQLException {
            String brisiKontakt = brisiKontaktField.getText ();

            String stmt="DELETE FROM `Kontakt` WHERE `Ime kontakta`=\""+brisiKontakt+"\"";

            makeMeRun.updateStatement (stmt);

            popuniKontakte ();
    }

    public void stampajIzvode() throws IOException, SQLException {
        FileWriter fileWriter=new FileWriter (new File ("izvod_"+ LocalDateTime.now ()
                .format (DateTimeFormatter.BASIC_ISO_DATE)+".txt"),true);
        BufferedWriter writer=new BufferedWriter (fileWriter);

        writer.write ("\n\nIZVOD KLUBA NA DAN: "+LocalDateTime.now ()+"\n");
        String legenda="        RACUN            TIP              DATUM               STANJE\n\n";
        writer.write (legenda);

        String stmt="SELECT * FROM `Izvod`";
        ResultSet rs= makeMeRun.executeStatement (stmt);
        while(rs.next ())
        {
            String datum=rs.getDate (4).toString ();
            String toWrite="    "+rs.getString (2)+"    "+rs.getString (3)+"    "+
                    datum+"   "+rs.getInt (5);
            writer.write (toWrite+"\n");
        }

        writer.close ();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Izvod sacuvan");
        alert.setHeaderText(null);
        alert.setContentText("Izvod racuna je sacuvan na sistemu!");

        alert.showAndWait();
    }
}
