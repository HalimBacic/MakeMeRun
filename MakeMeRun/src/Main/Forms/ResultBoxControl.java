package Main.Forms;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ResultBoxControl {

    @FXML
    TextArea searchResult;

    @FXML
    Button btnOK;



    static Stage stage;
    static ResultSet rs;

    public static void setMe(Stage stg)
    {
        stage=stg;
    }


    public void closeMe()
    {
        stage.close();
    }

    public void tableContent (String legend,ResultSet rs,Integer elems) throws SQLException {
        String result=legend+"\n\n";
        Integer i=1;
        while(rs.next ())
        {
            while(i<=elems)
            {
                result+=rs.getObject (i++).toString ()+"    |    ";
            }
            result+="\n";
            i=1;
        }
        searchResult.setText (result);
    }
}
