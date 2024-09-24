/**
 * Customer.java
 * 
 * 
 * @author Aadil
 * 
 */

package bankingappgui.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;





public class Customer extends AbstractUser {
    /** OVERVIEW: Customers are a mutable entity that stores the username, password,
     *          balance and membership level of a customer. The instances of this class
     *         can be modified to show changes in the balance and membership level as
     *         a customer makes transactions.
     *
     * AF(c) = A Customer c such that
     *       c.username = c.getUsername()
     *       c.password = c.getPassword()
     *       c.role = c.getRole()
     *       c.customerLevel = c.getCustomerLevel()
     *       c.balance = c.getBalance()
     *       c.level = c.getCustomerLevel
     *
     * The rep invariant is:
     * RI(c) = true if
     *     c.username != NULL and not empty
     *     c.password != NULL and not empty
     *     c.getRole == "customer"
     *     c.getLogInStatus == c.loggedIn
     *     c.getBalance >= 0
     * RI(c) = false otherwise
     *
     */
    //Instance variables
    private Level customerLevel; 
    private double balance; 
    
    //Constructor
    public Customer(String username, String password, double balance){ 
        if (username == null || password == null) {
        throw new IllegalArgumentException("Username and password cannot be null");
        }
    
        
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        
        this.username = username;
        this.password = password; 
        this.balance = balance; 
        this.role = "customer"; 
        
       if(this.balance < 10000){
            this.customerLevel = new Silver();  
        }else if(this.balance >= 20000){
            this.customerLevel = new Platinum(); 
        }else if(this.balance >= 10000 && this.balance < 20000){
            this.customerLevel = new Gold();
        }
    
    }
 
    public double getBalance(){return this.balance;} 
    
    public String getCustomerLevel(){return this.customerLevel.getLevel();}
    
    public boolean withdraw(double amount) throws IOException{
        //Modifies: this.balance, this.customerLevel
        //Effects: If the amount is non-negative and is not greater than the balance
        //         the balance is decreased by the amount, the customer level is updated
        //         and true is returned. Otherwise, nothing is modified and false is returned
        //Requires: The amount must be non-negative, of type double and not be greater than the balance
       
        
        if(amount > this.balance || amount < 0){
            return false;
        }else{
            this.balance -= amount;
             if(this.balance < 10000){
                this.customerLevel = new Silver();  
            }else if(this.balance >= 20000){
            this.customerLevel = new Platinum(); 
            }else if(this.balance >= 10000 && this.balance < 20000){
            this.customerLevel = new Gold();
            }
            this.updateStoredBalance();
            return true; 
        }
        
    }
    
    public boolean deposit(double amount) throws IOException{
        //Modifies: this.balance, this.customerLevel
        //Effects: If the given amount is non negative, increases the balance, 
        //         updates customerLevel and returns true. Otherwise, 
        //         nothing is updated and returns false
        //Requires: An amount of type double that is greater than zero
        //
        //
        if(amount > 0){
        this.balance = this.balance + amount;
        this.updateStoredBalance();
        if(this.balance < 10000){
            this.customerLevel = new Silver();  
        }else if(this.balance >= 20000){
            this.customerLevel = new Platinum(); 
        }else if(this.balance >= 10000 && this.balance < 20000){
            this.customerLevel = new Gold();
        }
        return true;
        }
        else{
            return false;
        }
    }
    
    public boolean purchase(double purchaseAmount) throws IOException{
        //Modifies: this.balance, this.customerLevel
        //Effects: If the given amount is greater than 50, and the total cost 
        //         is not greater than the balance, then a purchase is made,
        //         the balance is decreased, the customer level is updated 
        //         and true is returned. Otherwise, nothing changes and false
        //         is returned. 
        //Requires: An amount of type double that is greater than or equal to 50
        //
        //
        double totalCost = purchaseAmount + this.customerLevel.getFee(); 
        if(purchaseAmount < 50){
            return false;
        }
        
        if(totalCost > this.balance){
            return false;
        }
        
        this.balance -= totalCost;
       if(this.balance < 10000){
            this.customerLevel = new Silver();  
        }else if(this.balance >= 20000){
            this.customerLevel = new Platinum(); 
        }else if(this.balance >= 10000 && this.balance < 20000){
            this.customerLevel = new Gold();
        }
        this.updateStoredBalance();
        return true;
    }
    
    public void updateStoredBalance() throws FileNotFoundException, IOException{
        //Modifies: The balance information stored in the associated file
        //Effects: Updates the balance information stored in the file to show 
        //         the change in the users balance
        //Requires: The customer's balance, username and password intialized and
        //          the file containing the customer's information exists
        //@throws FileNotFoundException if the file containing the customer's information is not found.
        //@throws IOException if an I/O error occurs while reading or writing the file.
        //
        //
        File toUpdate = new File("customersInformation/" + username + ".txt"); 
        String oldInfo = ""; 
        
        FileWriter writer;
        try (BufferedReader reader = new BufferedReader (new FileReader(toUpdate))) {
            String line = reader.readLine();
            while(line != null)
            {
                oldInfo = oldInfo + line + System.lineSeparator();
                line = reader.readLine();
            }   String newInfo = oldInfo.replaceAll(oldInfo,username + "\n" + password + "\n" + "customer" + "\n" + this.balance) ; 
            writer = new FileWriter(toUpdate);
            writer.write(newInfo);
        }
        writer.close();
    } 
    
    @Override
    public void login(String username, String password) {
        //Modifies: this.loggedIn
        //Effects: If the inputted username and password are equal to the 
        //         username and password stored in the file, then loggedIn
        //         is set to true showing that the user is loggedIn. 
        //         Otherwise, nothing happens.
        //Requires: this.username and this.password are initialized and
        //          the file containing the customer's information exists
        
        
        String fileName = "customersInformation/" + username + ".txt";
        
        File file = new File(fileName);
        if(file.exists() && file.isFile()){
            try(Scanner reader = new Scanner(file)) { 
                
                String filedUsername = reader.nextLine().trim();
                String filedPassword = reader.nextLine().trim();
                
                
            if (filedUsername.equals(username) && filedPassword.equals(password)) {
                this.loggedIn = true;
            }
                           
                
            } catch (FileNotFoundException ex) {
                System.out.println("The file could not be read.");
            }
            
            
            
        }
        
    }
    
    public boolean RepOk(){
        //Effects: Returns true if rep invariant is true, otherwise returns false
        if(this.getUsername() != null && !this.getUsername().equals("")
                 && this.getPassword() != null && !this.getPassword().equals("")
                && this.getBalance() >= 0 && this.getRole().equals("customer")
                && this.getLoginStatus() == this.loggedIn ){
            return true;
        }else{
            return false; 
        }
    }
    
    
    @Override
    public String toString(){
        //Effects: Returns Abstraction Function as a string
        return("Username: " + this.getUsername() + "\nPassword: "
                + this.getPassword() +  "\nRole: " + this.getRole() +
                "\nBalance: " + this.getBalance() + "\nLevel: " + this.getCustomerLevel());
    }

}
    
    
    

   

