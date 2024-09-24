/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingappgui.project;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author Aadil
 */
public class BankingHandler {
    private static BankingHandler instance; 
    private final ArrayList<Customer> customers;
    private final Manager manager;
    private int currentLoggedInCustomer;
    
    
    private BankingHandler(){
        this.manager = Manager.getInstance("admin", "admin");
        customers = new ArrayList<>(); 
        
    }
    
    
    public static BankingHandler getInstance(){
        if(instance == null){
            instance = new BankingHandler(); 
        }
        return instance; 
    }
    
    public boolean managerLogin(String username, String password){
        manager.login(username, password);
        return manager.getLoginStatus(); 
    }
    
    public boolean customerLogin(String username, String password){
        
        for(int i = 0; i < this.customers.size(); i++){
            
            if(this.customers.get(i).getUsername().equals(username)){
            
            this.customers.get(i).login(username, password);
            if(this.customers.get(i).getLoginStatus()){
                this.currentLoggedInCustomer = i;
                 
                return true; 
 
            }
        }
        }
            return false; 
        
    }
    
    public void managerLogout(){
        this.manager.logout();
    }
    
    public boolean getManagerLoginStatus(){
        return this.manager.getLoginStatus();
    }
    
    
    
     public void customerLogout(){
        this.customers.get(this.currentLoggedInCustomer).logout();
        this.currentLoggedInCustomer = -1; 
    
        }
     
     
     public boolean addCustomer(String username, String password){
          return(manager.addCustomer(username, password, this.customers));   
     }
     
     public boolean deleteCustomer(String username){
         return(manager.deleteCustomer(username, customers)); 
     }
     
     
     public boolean customerWithdraw(double amount) throws IOException{
         return(this.customers.get(currentLoggedInCustomer).withdraw(amount));
     }
     
     public boolean customerDeposit(double amount) throws IOException{
         return(this.customers.get(currentLoggedInCustomer).deposit(amount)); 
     }
     
     public boolean customerPurchase(double amount) throws IOException{
         return(this.customers.get(currentLoggedInCustomer).purchase(amount));
     }
     
     public double getCustomerBalance(){
         return(this.customers.get(currentLoggedInCustomer).getBalance());
     }
     
   
     public ArrayList<Customer> getCustomers(){
         return this.customers; 
     }
     
     public Customer getCurrentCustomer(){
         return this.customers.get(currentLoggedInCustomer); 
     }
     
     
     
     public void initializeExistingCustomers(){
         String folderPath = "customersInformation/";
         
         File folder = new File(folderPath); 
         
         File[] files = folder.listFiles(); 
         
         if( files != null){
             for(File file : files){
                 if(file.isFile() && file.getName().endsWith(".txt")){
                     scanCustomerFile(file); 
                 }
             }
         }
     }
     
     public void scanCustomerFile(File file){
         boolean customerExists = false;
         try(Scanner reader = new Scanner(file)){
                String filedUsername = reader.nextLine().trim();
                String filedPassword = reader.nextLine().trim();
                String filedRole = reader.nextLine().trim();
                double filedBalance = Double.parseDouble(reader.nextLine().trim()); 
                
                for(int i = 0; i < this.customers.size(); i++){
                    if(this.customers.get(i).getUsername().equals(filedUsername)){
                        customerExists = true;
                    }
                    
                }
                if(!customerExists){
                Customer customer = new Customer(filedUsername, filedPassword, filedBalance);
                this.customers.add(customer); 
                }
                reader.close(); 
         } catch (Exception e) {
           
        }
     }
}
