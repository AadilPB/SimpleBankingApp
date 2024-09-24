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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aadil
 */
public class FXMLCustomerPageController implements Initializable {

    
        BankingHandler Bank = BankingHandler.getInstance(); 
    
        @FXML
        private Label welcomeMessage; 
    
        @FXML
        private Label customerBalance;
        
        @FXML
        private Label customerUsername;
        
        @FXML 
        private Label customerLevel; 
        
        @FXML
        private TextField depositAmount;
        
        @FXML
        private Button deposit;
    
        @FXML
        private Label depositStatus; 
    
        @FXML 
        private TextField withdrawAmount;
        
        @FXML 
        private Button withdraw;
        
        @FXML 
        private Label withdrawStatus;
        
        @FXML
        private TextField purchaseAmount;
        
        @FXML 
        private Button purchase;
        
        @FXML
        private Label purchaseStatus;
        
    
        @FXML
        private void handleLogOut(ActionEvent event) throws IOException{
            Bank.customerLogout();
        
            Node source = (Node) event.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
    
            Parent managerLoginParent = FXMLLoader.load(getClass().getResource("FXMLHome.fxml"));
            Scene managerLoginScene = new Scene(managerLoginParent);
    
            currentStage.setScene(managerLoginScene);
            currentStage.show();
        
        
    }
    
    @FXML     
    private void handleDeposit(ActionEvent event) throws IOException{
        try {
        
            double amount = Double.parseDouble(depositAmount.getText());
            if(Bank.customerDeposit(amount)){
                depositAmount.clear();
                depositStatus.setText("Deposit Successful");
                setBalance();
                setLevel();
            }
        else{
          depositStatus.setText("Please enter a value above 0!");  
        }
    } catch (NumberFormatException e) {
        
        depositStatus.setText("Please enter a valid input.");
    }
    }
        
    @FXML
    private void handleWithdraw(ActionEvent event) throws IOException{
        try {
        double amount = Double.parseDouble(withdrawAmount.getText());
            if(Bank.customerWithdraw(amount)){
                withdrawAmount.clear();
                withdrawStatus.setText("Withdraw Successful");
                setBalance();
                setLevel();
            }else{
                if(amount < 0){
                    withdrawStatus.setText("Please input a value above 0!");
                }
                else{
                withdrawStatus.setText("You do not have enough in your account!");
                }
            }
    } catch (NumberFormatException e) {
        
        depositStatus.setText("Please enter a valid input.");
    }
    }
    
    @FXML
    private void handlePurchase(ActionEvent event) throws IOException{
          try {
            double amount = Double.parseDouble(purchaseAmount.getText());
            if(Bank.customerPurchase(amount)){
                purchaseAmount.clear();
                purchaseStatus.setText("Purchase Successful");
                setBalance();
                setLevel();
            }else{
                if(amount < 50){
                   purchaseStatus.setText("Your purchase must be $50 or more!"); 
                }
                else{
                   purchaseStatus.setText("Insufficient Funds!"); 
                }
                
            }
    } catch (NumberFormatException e) {
        
        purchaseStatus.setText("Please enter a valid input.");
    }
    }
    
    
    
    private void setMessage(){
        
        welcomeMessage.setText("Welcome " + Bank.getCurrentCustomer().getUsername());
    }
    
    private void setBalance(){
        double amount = Bank.getCustomerBalance();
        DecimalFormat twoDecimalPlaces = new DecimalFormat("#.00");
        String roundedAmount = twoDecimalPlaces.format(amount);
        
        customerBalance.setText("$" + roundedAmount); 
    }
    
    private void setLevel(){
        customerLevel.setText(Bank.getCurrentCustomer().getCustomerLevel());
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setMessage(); 
        setBalance();
        setLevel();
        customerUsername.setText(Bank.getCurrentCustomer().getUsername());
    }    
    
}
