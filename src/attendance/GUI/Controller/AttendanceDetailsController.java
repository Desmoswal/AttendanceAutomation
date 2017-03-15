/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Controller;

import attendance.GUI.Model.AttendanceModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Desmoswal
 */
public class AttendanceDetailsController implements Initializable
{

    @FXML
    private TableView<?> tblMissed;
    @FXML
    private TableColumn<?, ?> colDate;
    @FXML
    private TableColumn<?, ?> colTime;
    @FXML
    private TableColumn<?, ?> colClass;
    @FXML
    private TableColumn<?, ?> colTeacher;
    @FXML
    private Label tblTotal;
    @FXML
    private Label lblAttendance;
    @FXML
    private Label lblMissed;
    @FXML
    private ComboBox<?> cmbClass;
    @FXML
    private Label lblMissedTotal;
    
    private AttendanceModel model = new AttendanceModel();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        cmbClass.setButtonCell(new ListCell() {
            @Override
            protected void updateItem(Object item, boolean empty) {
            super.updateItem(item, empty); 
            if(empty || item==null){
                // styled like -fx-prompt-text-fill:
                setStyle("-fx-text-fill: white;");
            } else {
                setStyle("-fx-text-fill: white;");
                setText(item.toString());
            }
        }
        });
        
        if(model.getMissedTotal() != -1) {
            lblMissedTotal.setText(""+model.getMissedTotal());
        }
    }
    
}
