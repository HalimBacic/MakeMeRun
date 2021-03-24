package Main.Forms;

import Main.Controller;
import Main.MakeMeRun;
import Osoba.Trkac;
import com.opencsv.CSVReader;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.awt.*;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PregledTrkacaControl {


    @FXML
    private TableView<Trkac> tableTrkaci;

    @FXML
    private TableColumn<String, String> broj;

    @FXML
    private TableColumn<String, String> ime;

    @FXML
    private TableColumn<String, String> prezime;

    @FXML
    private TableColumn<String, String> vrijeme;

    @FXML
    private TableColumn<ImageView, ImageView> paket;

    @FXML
    private TextArea paketInfo;

    @FXML
    private TextField searchField;

    static MakeMeRun makeMeRun;
    static Stage stage;
    static String trka;

    public static void setMe(MakeMeRun mmr,Stage stg,String t)
    {
        makeMeRun=mmr;
        stage=stg;
        trka=t;
    }

    public void dodajUFajl() throws IOException, SQLException {
        FileWriter fileWriter=new FileWriter (new File("rezultati.csv"));
        CSVWriter writeCsv=new CSVWriter (fileWriter);
        String stmt = "SELECT * FROM `Trkac` WHERE `TRKA_Naziv`=\""+trka+"\"";
        ResultSet rs= makeMeRun.executeStatement (stmt);

        while(rs.next ())
        {
            String id=String.valueOf (rs.getInt (1));
            String trkac=rs.getString (2)+" "+rs.getString (3);
            String vrijeme=" - ";
            String []linija={id,trkac,vrijeme};
            System.out.println(linija[0]+linija[1]+linija[2]);
            writeCsv.writeNext (linija);
        }
        writeCsv.close ();
    }

    public void updateTable() throws IOException, CsvValidationException, SQLException {


        String stmtCuvanjeTrke = "INSERT INTO `Arhiva`(`Naziv trke`,`Datum`) VALUES(\""+trka+"\",\""+ LocalDate.now () +"\")";
        makeMeRun.updateStatement (stmtCuvanjeTrke);

        FileReader readFile = new FileReader (new File ("rezultati.csv"));
        CSVReader readCsv=new CSVReader (readFile);
        String []line;
        while((line=readCsv.readNext ())!=null)
        {
            String id = line[0];
            String vrijeme = line[2];
            String stmt="CALL `dodajVrijeme`(\""+vrijeme+"\",\""+id+"\");";
            makeMeRun.executeStatement (stmt);
            String stmtDodajTrkacaUArhivu="INSERT INTO `Rezultat`(`ID`,`Trkac`,`Vrijeme`)" +
                    "VALUES (\""+id+"\",\""+line[1]+"\""+vrijeme+"\");";
            makeMeRun.updateStatement (stmtDodajTrkacaUArhivu);

            String stmtNumber = "SELECT COUNT(*) FROM `ARHIVA`;";
            ResultSet rs=makeMeRun.executeStatement (stmtNumber);
            int brojArhive=0;
            while(rs.next ())
                brojArhive=rs.getInt (1);

            String stmtdodajCuvanje="INSERT INTO `Cuvanje`(`ARHIVA_ID`,`REZULTAT_ID`)" +
                    "VALUES(\""+brojArhive+"\",\""+id+"\");";
            makeMeRun.updateStatement (stmtdodajCuvanje);
        }


        writeTable ();
    }

    public void writeTable() throws SQLException {
            tableTrkaci.getItems ().removeAll ();
            String stmt = "SELECT * FROM `Trkac` WHERE `TRKA_Naziv`=\""+trka+"\"";
            ResultSet rs= makeMeRun.executeStatement (stmt);
            ArrayList<Trkac> data= new ArrayList <>();
            while(rs.next ())
            {
                Trkac trkac = new Trkac(rs.getInt (1),rs.getString (2),rs.getString (3)
                ,rs.getInt (4),rs.getInt (5),rs.getString (6),
                        rs.getString (10),rs.getString (8),rs.getString (9));
                data.add (trkac);
            }
            ObservableList<Trkac> observableList = FXCollections.observableList (data);
            ime.setCellValueFactory (new PropertyValueFactory<> ("ime"));
            prezime.setCellValueFactory (new PropertyValueFactory<> ("prezime"));
            broj.setCellValueFactory (new PropertyValueFactory<> ("id"));
            paket.setCellValueFactory (new PropertyValueFactory<> ("img"));
            vrijeme.setCellValueFactory (new PropertyValueFactory<> ("vrijemePrikaz"));
            tableTrkaci.setItems (observableList);
            tableTrkaci.addEventFilter (MouseEvent.MOUSE_CLICKED , new EventHandler<MouseEvent> () {
                @Override
                public void handle (MouseEvent mouseEvent) {
                    try {
                        viewTrkacInfo ();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace ();
                    }
                }
            });
            tableTrkaci.addEventFilter (MouseEvent.MOUSE_MOVED , new EventHandler<MouseEvent> () {
                @Override
                public void handle (MouseEvent mouseEvent) {
                    paketInfo.setVisible (false);
                }
            });
    }

    public void closeMe()
    {
        stage.close ();
    }

    /*

     */
    public void search()
    {

        String text = "\""+searchField.getText()+"\"";

        String stmtstr = "SELECT * FROM `Trkac` WHERE Broj="+text+" OR Ime="+text+" OR Prezime="+text+"" +
                "AND `TRKA_Naziv`=\""+trka.toString ()+"\";";
        try {
               FXMLLoader loader = new FXMLLoader(getClass ().getResource ("/Main/main.fxml"));
               loader.load ();
               Controller ctrl = loader.getController ();
               String legend="BROJ      IME         PREZIME         DIST    GOD     DRZ     UPLATA      KONTAKT";
               ctrl.search (legend,stmtstr,8);
           } catch (IOException e) {
               e.printStackTrace();
           }
    }

    public void viewTrkacInfo() throws SQLException {
        paketInfo.setLayoutY (MouseInfo.getPointerInfo ().getLocation ().getY ()-130);
        paketInfo.setLayoutX (MouseInfo.getPointerInfo ().getLocation ().getX ()-450);
        paketInfo.setText ("");

        String brojTrkaca = (tableTrkaci.getSelectionModel ().getSelectedItem ()).getId ().toString ();

        String stmt="SELECT * FROM `Trkac` WHERE `Broj`=\""+brojTrkaca+"\" && `TRKA_Naziv`=\""+trka+"\";";

        ResultSet rs = makeMeRun.executeStatement (stmt);
        String infoTrkac="";
        while(rs.next ())
        {
            String ime=rs.getString (2);
            String prezime=rs.getString (3);
            int godine = rs.getInt (5);
            String drz = rs.getString (6);
            String upl = rs.getString (7);
            String kon = rs.getString (8);
            infoTrkac=ime+"\n"+prezime+"\n"+godine+"\n"+drz+"\n"+upl+"\n"+kon;
        }
        paketInfo.setText (infoTrkac);
        paketInfo.setVisible (true);
    }
}
