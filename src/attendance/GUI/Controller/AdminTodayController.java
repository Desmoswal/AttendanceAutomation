/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Desmoswal
 */
public class AdminTodayController implements Initializable
{

    @FXML
    private TableColumn<?, ?> colTime;
    @FXML
    private TableColumn<?, ?> colCourseName;
    @FXML
    private TableColumn<?, ?> colClass;
    @FXML
    private TableColumn<?, ?> colRoom;
    @FXML
    private TableView<?> tblToday;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    
}
