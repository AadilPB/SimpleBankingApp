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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aadil
 */
public class FXMLCustomerLoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    BankingHandler Bank = BankingHandler.getInstance(); 
    
    @FXML
    private TextField customerUsername; 
    
    @FXML
    private PasswordField customerPassword; 
    
    @FXML
    private Label loginStatus;
    
    @FXML 
    private Button logIn; 
    
    @FXML
    private Button back; 
    
    @FXML
      private void handleLogIn(ActionEvent event) throws IOException {
        String username = customerUsername.getText(); 
        String password = customerPassword.getText(); 
        
        Bank.customerLogin(username, password);
        
        if(Bank.getCurrentCustomer().getLoginStatus()){
            Node source = (Node) event.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
    
            Parent managerLoginParent = FXMLLoader.load(getClass().getResource("FXMLCustomerPage.fxml"));
            Scene managerLoginScene = new Scene(managerLoginParent);
    
            currentStage.setScene(managerLoginScene);
            currentStage.show();
        }else{
           
            loginStatus.setText("Username or Password Wrong!");
            
        }
        
    }
    
    @FXML
    private void handleBack(ActionEvent event) throws IOException{
        Node source = (Node) event.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();
    
        Parent managerLoginParent = FXMLLoader.load(getClass().getResource("FXMLHome.fxml"));
        Scene managerLoginScene = new Scene(managerLoginParent);
    
        currentStage.setScene(managerLoginScene);
        currentStage.show();
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
