package Main.Forms;

import Entity.AIMSCertifikat;
import Main.Controller;
import Main.MakeMeRun;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AimsViewControl {

    @FXML
    private TableView<AIMSCertifikat> aimsTable;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> trka;

    @FXML
    private TableColumn<?, ?> pocetak;

    @FXML
    private TableColumn<?, ?> kraj;

    @FXML
    private Button btnOk;


    private static MakeMeRun makeMeRun;
    private static Stage stage;
    private static Controller control;

    public static void setMe(Controller c,MakeMeRun mmr,Stage s)
    {
        makeMeRun=mmr;
        control=c;
        stage=s;
    }

    public void showAIMS()
    {
        String stmt = "SELECT * FROM `Aims certifikat`;";
        ArrayList<AIMSCertifikat> data = new ArrayList<> ();
        try {
            ResultSet rs = makeMeRun.executeStatement (stmt);
            while(rs.next ())
            {
                String broj = rs.getString (1);
                String certifikator = rs.getString (2);
                String pocetak=rs.getString (3);
                String kraj = rs.getString (4);
                String trka = rs.getString (5);
                String duzina = rs.getString (6);
                String naziv = rs.getString (7);
                data.add(new AIMSCertifikat (broj,certifikator,pocetak,kraj,trka,duzina,naziv));
            }

            ObservableList<AIMSCertifikat> observableList = FXCollections.observableList (data);
            id.setCellValueFactory (new PropertyValueFactory<> ("broj"));
            trka.setCellValueFactory (new PropertyValueFactory<> ("trka"));
            pocetak.setCellValueFactory (new PropertyValueFactory<> ("pocetak"));
            kraj.setCellValueFactory (new PropertyValueFactory<> ("kraj"));
            aimsTable.setItems (observableList);
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
    }

    public void closeMe()
    {
        stage.close ();
    }
}
