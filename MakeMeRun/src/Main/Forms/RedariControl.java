package Main.Forms;

import Main.Controller;
import Main.MakeMeRun;
import Osoba.Redar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RedariControl {


    @FXML
    private TableView<Redar> tableRedari;

    @FXML
    private TableColumn<Object, Object> jbmgCell;

    @FXML
    private TableColumn<Object, Object> imeCell;

    @FXML
    private TableColumn<Object, Object> prezimeCell;

    @FXML
    private TableColumn<Object, Object> ulogaCell;

    @FXML
    private TableColumn<Object, Object> brojtelefonaCell;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnDodaj;

    @FXML
    private TextField jmbgField;


    static Stage stage;
    static MakeMeRun makeMeRun;
    static Controller control;

    public static void setMe (Controller ctrl , MakeMeRun mmr , Stage s) {
        control = ctrl;
        makeMeRun = mmr;
        stage = s;
    }

    public void closeMe ()
    {
        stage.close ();
    }

    public void dodajRedara() throws IOException {
        FXMLLoader loader = new FXMLLoader (getClass ().getResource ("dodajRedara.fxml"));
        Parent root = loader.load ();
        Stage primaryStage = new Stage ();
        primaryStage.setTitle ("Dodaj redara");
        primaryStage.setScene (new Scene (root , 387 , 516));
        DodajRedaraControl.setMe (makeMeRun,control,primaryStage);
        primaryStage.show ();
    }

    public void ispuniTabelu()
    {
        tableRedari.getItems ().removeAll ();
        String stmt="SELECT * FROM `Redar`;";
        ResultSet rs;
        ArrayList<Redar> data = new ArrayList<> ();
        try {
            rs=makeMeRun.executeStatement (stmt);
            while(rs.next ())
            {
                String jbmg=rs.getString (1);
                String ime=rs.getString (2);
                String prezime=rs.getString (3);
                String uloga=rs.getString (4);
                String bt=rs.getString (5);
                data.add (new Redar (jbmg,ime,prezime,uloga,bt));
            }
            ObservableList<Redar> observableList= FXCollections.observableList (data);
            jbmgCell.setCellValueFactory (new PropertyValueFactory<> ("jmbg"));
            imeCell.setCellValueFactory (new PropertyValueFactory<> ("ime"));
            prezimeCell.setCellValueFactory (new PropertyValueFactory<> ("prezime"));
            ulogaCell.setCellValueFactory (new PropertyValueFactory<> ("uloga"));
            brojtelefonaCell.setCellValueFactory (new PropertyValueFactory<> ("brojTelefona"));
            tableRedari.setItems (observableList);
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }

    }

    public void brisiRedara() throws SQLException {
        String jbmg=jmbgField.getText ();

        String stmt="DELETE FROM `Redar` WHERE `JMBG`=\""+jbmg+"\";";

        makeMeRun.updateStatement (stmt);

        ispuniTabelu ();
    }
}
