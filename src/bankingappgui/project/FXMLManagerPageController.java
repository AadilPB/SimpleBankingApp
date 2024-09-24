/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingappgui.project;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
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
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Aadil
 */
public class FXMLManagerPageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    BankingHandler Bank = BankingHandler.getInstance();
    
    
    
    @FXML
    private Button logOut;
    
    @FXML
    private Button addButton;
            
    @FXML
    private Button deleteButton;
    
    @FXML 
    private TextField addUsername; 
    
    @FXML
    private TextField deleteUsername; 
    
    @FXML
    private PasswordField addPassword; 
    
    @FXML
    private ListView<String> customerUsernameList;
    
    @FXML
    private ListView<String> customerBalanceList;
    
    @FXML
    private Label customerAddStatus; 
    
    @FXML
    private Label customerDeleteStatus; 
    
    
    
    @FXML
    private void handleLogOut(ActionEvent event) throws IOException{
        Bank.managerLogout();
        
         Node source = (Node) event.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();
    
        Parent managerLoginParent = FXMLLoader.load(getClass().getResource("FXMLHome.fxml"));
        Scene managerLoginScene = new Scene(managerLoginParent);
    
        currentStage.setScene(managerLoginScene);
        currentStage.show();
        
        
    }
    
    @FXML
    private void handleAddCustomer(ActionEvent event){
         String username = addUsername.getText(); 
        String password = addPassword.getText();
        if(username.equals("") || username.replaceAll("//s", "").equals("") || password.equals("") || password.replaceAll("//s", "").equals("")){
            customerAddStatus.setText("Please enter a character or symbol for your username and password!");
        }
        else{
            if(Bank.addCustomer(username, password)){
                customerAddStatus.setText("Customer Added!");
                addPassword.clear();
                addUsername.clear();
            }
            else{
                customerAddStatus.setText("Customer already exists!");
            }
        }
        updateCustomerLists(); 
    }
    
    @FXML 
    private void handleDeleteCustomer(ActionEvent event){
        String username = deleteUsername.getText(); 
        
        if(username.equals("")){
             customerDeleteStatus.setText("You have not inputted a valid username!");
        }
        
        else{
            if(Bank.deleteCustomer(username)){
                deleteUsername.clear();
                customerDeleteStatus.setText("Customer Deleted!");    
            }
            else{
                customerDeleteStatus.setText("Customer does not exist!");
            }
        }
        updateCustomerLists(); 
    }
    
    private void updateCustomerLists(){
        String[] customerUsernames = new String[Bank.getCustomers().size()];
        String[] customerBalances = new String[Bank.getCustomers().size()];
        customerUsernameList.getItems().clear();
        customerBalanceList.getItems().clear();
        double amount;
        DecimalFormat twoDecimalPlaces = new DecimalFormat("#.00");
        String roundedAmount;
        for(int i = 0; i < Bank.getCustomers().size(); i++){
            amount = Bank.getCustomers().get(i).getBalance();
            
            roundedAmount = twoDecimalPlaces.format(amount);
            customerUsernames[i] = Bank.getCustomers().get(i).getUsername();
            customerBalances[i] = roundedAmount;
            
        }
        
        customerUsernameList.getItems().addAll(customerUsernames); 
        customerBalanceList.getItems().addAll(customerBalances);
        
   
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        updateCustomerLists(); 
    }    
    
}
