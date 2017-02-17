/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;

/**
 * FXML Controller class
 *
 * @author Kristof
 */
public class Admin_todaysController implements Initializable {

    @FXML
    private TableColumn<?, ?> colTime;
    @FXML
    private TableColumn<?, ?> colCourseName;
    @FXML
    private TableColumn<?, ?> colClass;
    @FXML
    private TableColumn<?, ?> colRoom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
