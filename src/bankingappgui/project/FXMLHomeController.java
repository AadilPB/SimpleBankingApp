/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingappgui.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aadil
 */
public class FXMLHomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private Label label;
    BankingHandler Bank = BankingHandler.getInstance(); 
    
    @FXML
    private void handleManagerAction(ActionEvent event) throws IOException {
      Node source = (Node) event.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();
    
        Parent managerLoginParent = FXMLLoader.load(getClass().getResource("FXMLManagerLogin.fxml"));
        Scene managerLoginScene = new Scene(managerLoginParent);
    
        currentStage.setScene(managerLoginScene);
        currentStage.show();
        
    }
    
    @FXML
    private void handleCustomerAction(ActionEvent event) throws IOException {
         Node source = (Node) event.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();
    
        Parent managerLoginParent = FXMLLoader.load(getClass().getResource("FXMLCustomerLogin.fxml"));
        Scene managerLoginScene = new Scene(managerLoginParent);
    
        currentStage.setScene(managerLoginScene);
        currentStage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Bank.initializeExistingCustomers();
    }    
    
}
