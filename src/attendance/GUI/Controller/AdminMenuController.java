/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Desmoswal
 */
public class AdminMenuController implements Initializable
{

    @FXML
    private Button btnTodays;
    @FXML
    private Button btnOnline;
    @FXML
    private Button btnStatsStudent;
    @FXML
    private Button btnStatsClass;
    
    private MainViewController parentCon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }    
    
    public void setParentController(MainViewController pc) {
        this.parentCon = pc;
    }
    
    @FXML
    private void pressedMenuButton(ActionEvent event) {
        Button pressedButton = (Button) event.getSource();
        System.out.println("pressed button: "+event.getSource());
        switch(pressedButton.getId()) {
            case "btnTodays":
                try {
                    parentCon.openItem("/attendance/GUI/View/AdminToday.fxml");
                } catch(IOException e) {
                    System.out.println("FXML probably not found");
                    System.out.println(e);
                }
                break;
            case "btnStatsStudent":
                try {
                    parentCon.openItem("/attendance/GUI/View/AdminAttByStudent.fxml");
                } catch(IOException e) {
                    System.out.println("FXML probably not found");
                    System.out.println(e);
                }
                break;
            case "btnStatsClass":
                try {
                    parentCon.openItem("/attendance/GUI/View/AdminAttByClass.fxml");
                } catch(IOException e) {
                    System.out.println("FXML probably not found");
                    System.out.println(e);
                }
                break;
            case "btnOnline":
                try {
                    parentCon.openItem("/attendance/GUI/View/OnlineUsers.fxml");
                } catch(IOException e) {
                    System.out.println("FXML probably not found");
                    System.out.println(e);
                }
                break;
            case "btnLogOut":
                System.exit(0);
                break;
        }
        
    }
}
